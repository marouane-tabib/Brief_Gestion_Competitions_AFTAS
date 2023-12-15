package com.example.brief_gestion_competitions_aftas.repository;

import com.example.brief_gestion_competitions_aftas.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition findByDate(LocalDate date);
}
