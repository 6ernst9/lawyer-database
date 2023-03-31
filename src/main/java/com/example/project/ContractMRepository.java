package com.example.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractMRepository extends JpaRepository<Contract_m, Long > {

    @Query("SELECT c FROM Contract_m c WHERE c.functie LIKE :functie% ORDER BY c.salarBaza DESC, c.functie ASC")
    List<Contract_m> findByFunctie(@Param("functie") String functie);

    @Query("SELECT cm FROM Contract_m cm WHERE cm.comision IN ( SELECT cm2.comision FROM Contract_m cm2 WHERE NOT cm.idAngajat.idP = cm2.idAngajat.idP)")
    List<Contract_m> findPairContracts();

    @Query("SELECT cm FROM Contract_m cm WHERE cm.comision = :comision")
    List<Contract_m> findContractsWithSameComision(@Param("comision") Double comision);
}
