package com.example.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RataRepository extends JpaRepository<Rata, RataId> {
    @Query("SELECT MAX(r.id_r) FROM Rata r WHERE r.id_cj.id_cj = :id")
    Optional<Long> getLastById(Long id);
}
