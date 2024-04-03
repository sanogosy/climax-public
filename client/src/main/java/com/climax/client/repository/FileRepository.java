package com.climax.client.repository;

import com.climax.client.domain.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<Fichier, Long> {

    @Query(value = "SELECT * FROM fichier WHERE titre = ?1", nativeQuery = true)
    Optional<Fichier> findByTitre(String name);
}
