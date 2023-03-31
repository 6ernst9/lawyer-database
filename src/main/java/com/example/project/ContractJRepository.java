package com.example.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContractJRepository extends JpaRepository< Contract_j, Long> {

    @Query("SELECT c FROM Contract_j c WHERE c.id_cj = :id")
    Optional<Contract_j> findById(Long id);


    @Query("SELECT c FROM Contract_j c WHERE c.data BETWEEN :startDate AND :endDate AND c.onorar BETWEEN :minOnorar " +
            "AND :maxOnorar ORDER BY c.data ASC")
    List<Contract_j> findByDateRangeAndOnorarRange(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate,
                                                  @Param("minOnorar") int minOnorar,
                                                  @Param("maxOnorar") int maxOnorar);

    @Query("SELECT cj FROM Contract_j cj WHERE cj.onorar > ( SELECT SUM(rt.suma)  FROM Rata rt WHERE rt.id_cj.id_cj = cj.id_cj) OR cj.id_cj NOT IN (SELECT rt2.id_cj.id_cj FROM Rata rt2)")
    Optional<List<Contract_j>> findByNotPaid();


    @Query("SELECT cj1 , cj2 FROM Contract_j cj1 JOIN Contract_j cj2 ON (cj1.idAvocat.idP = cj2.idAvocat.idP) " +
            "WHERE NOT cj1.idClient.idP = cj2.idClient.idP AND cj1.id_cj < cj2.id_cj")
    List<Object[]> findPairContractsByAvocatId();

}
