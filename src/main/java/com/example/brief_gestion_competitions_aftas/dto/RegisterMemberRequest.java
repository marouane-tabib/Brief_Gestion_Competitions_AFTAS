package com.example.brief_gestion_competitions_aftas.dto;

import com.example.brief_gestion_competitions_aftas.model.Competition;
import com.example.brief_gestion_competitions_aftas.model.Member;
import com.example.brief_gestion_competitions_aftas.model.Ranking;

public record RegisterMemberRequest(
        Long competitionId,
        Long memberId
) {
    public Ranking toRanking() {
        return Ranking.builder()
                .competition(Competition.builder().id(competitionId).build())
                .member(Member.builder().id(memberId).build())
                .build();
    }
}
