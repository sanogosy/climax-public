package com.climax.fileprocess.repository;

import com.climax.fileprocess.domain.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileProcessRepository extends JpaRepository<Fichier, Long> {
}
