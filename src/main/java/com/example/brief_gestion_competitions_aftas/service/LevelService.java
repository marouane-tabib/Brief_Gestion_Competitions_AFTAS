package com.example.brief_gestion_competitions_aftas.service;

import com.example.brief_gestion_competitions_aftas.model.Level;

import java.util.List;

public interface LevelService {
    Level getLevelById(Long id);
    List<Level> getAllLevels();
    Level addLevel(Level level);
    Level updateLevel(Level level, Long id);
    void deleteLevel(Long id);
}
