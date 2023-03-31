package com.example.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersoanaRepository extends JpaRepository<Persoana, Long> {

    @Query("SELECT p FROM Persoana p WHERE p.idP = :id")
    Optional<Persoana> findById(Long id);

    @Query("SELECT p, cj.onorar FROM Persoana p JOIN Contract_j cj ON (cj.idAvocat.idP = p.idP) WHERE cj.onorar >= ALL (SELECT cj2.onorar FROM Contract_j cj2)")
    List<Object[]> findPersoaneWithHighestOnorar();

    @Query("SELECT ps, cm.data, cm.functie FROM Contract_m cm JOIN Persoana ps ON (ps.idP = cm.idAngajat.idP) WHERE NOT cm.functie = :functie AND cm.data BETWEEN :startDate AND :endDate")
    List<Object[]> findPersoaneHiredInAndNotAvocati(@Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate,
                                                    @Param("functie") String functie);

    @Query("SELECT ps , get_avg_salary_by_idp(ps.idP, :startDate, :endDate) FROM Contract_m cm JOIN Persoana ps ON ( ps.idP = cm.idAngajat.idP ) JOIN Contract_j cj ON (cm.idAngajat.idP = cj.idAvocat.idP) " +
            "WHERE cm.functie = 'avocat' AND (cj.data BETWEEN :startDate AND :endDate) GROUP BY ps.idP, ps.nume, ps.adresa, ps.email, ps.telefon ")
    List<Object[]> findAvgForYear(@Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);

    @Query(nativeQuery = true, value = "select get_avg_salary_by_idp(:idp, :startDate, :endDate)")
    Double  get_avg_salary_by_idp(@Param("idp")Long idp,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate);
}
