package com.example.brief_gestion_competitions_aftas.repository;

import com.example.brief_gestion_competitions_aftas.model.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Long> {
    Fish findByName(String name);
}
