package com.example.brief_gestion_competitions_aftas.dto;


import com.example.brief_gestion_competitions_aftas.model.Ranking;

public record RecordResultDTO(
        Long id,
        int score
) {
    public Ranking toRanking() {
        return Ranking.builder()
                .id(id)
                .score(score)
                .build();
    }
}
