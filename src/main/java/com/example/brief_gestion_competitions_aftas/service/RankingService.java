package com.example.brief_gestion_competitions_aftas.service;

import com.example.brief_gestion_competitions_aftas.model.Ranking;

import java.util.List;

public interface RankingService {
    Ranking getRankingsByMemberIdAndCompetitionId(Long competitionId, Long memberId);
    Ranking getRankingById(Long id);
    Ranking updateRanking(Ranking ranking, Long id);
    Ranking updateRankingScore(Ranking ranking, Long id);
    void deleteRanking(Long id);
}
