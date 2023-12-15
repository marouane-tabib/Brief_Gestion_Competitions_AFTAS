package com.example.brief_gestion_competitions_aftas.repository;

import com.example.brief_gestion_competitions_aftas.model.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Hunting findByCompetitionIdAndMemberIdAndFishId(Long competitionId, Long memberId, Long fishId);
    List<Hunting> findByCompetitionId(Long competitionId);
    List<Hunting> findByCompetitionIdAndMemberId(Long competitionId, Long memberId);
}
