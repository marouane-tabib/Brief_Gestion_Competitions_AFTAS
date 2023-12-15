package com.example.brief_gestion_competitions_aftas.service;

import com.example.brief_gestion_competitions_aftas.model.Competition;
import com.example.brief_gestion_competitions_aftas.model.Ranking;

import java.util.List;

public interface CompetitionService {
    Competition getCompetitionById(Long id);
    List<Competition> getAllCompetitions();
    List<Competition> getCompetitionsByStatus(String status);
    Competition addCompetition(Competition competition);
    Competition updateCompetition(Competition competition, Long id);
    void deleteCompetition(Long id);
    Ranking registerMemberForCompetition(Ranking ranking);
}
