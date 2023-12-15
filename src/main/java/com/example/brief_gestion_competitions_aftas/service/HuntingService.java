package com.example.brief_gestion_competitions_aftas.service;

import com.example.brief_gestion_competitions_aftas.model.Hunting;

import java.util.List;

public interface HuntingService {
    Hunting getHuntingById(Long id);
    Hunting addHuntingResult(Hunting hunting);
    List<Hunting> getHuntingsByCompetition(Long competitionId);
    List<Hunting> getHuntingsByCompetitionAndMember(Long competitionId, Long memberId);
    Hunting updateHunting(Hunting hunting, Long id);
    void deleteHunting(Long id);
}
