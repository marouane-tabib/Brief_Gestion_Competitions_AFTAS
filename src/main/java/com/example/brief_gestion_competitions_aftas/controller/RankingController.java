package com.example.brief_gestion_competitions_aftas.controller;

import com.example.brief_gestion_competitions_aftas.dto.UpdateRequestRankingDTO;
import com.example.brief_gestion_competitions_aftas.handlers.response.ResponseMessage;
import com.example.brief_gestion_competitions_aftas.model.Ranking;
import com.example.brief_gestion_competitions_aftas.service.RankingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rankings")
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    // get ranking by id
    @GetMapping
    public ResponseEntity getRankingById(@PathVariable Long id) {
        Ranking ranking = rankingService.getRankingById(id);
        return ResponseMessage.ok(ranking,"Success");
    }

    // get ranking by member id and competition id
    @GetMapping("/{competitionId}/{memberId}")
    public ResponseEntity getRankingsByMemberIdAndCompetitionId(@PathVariable Long competitionId, @PathVariable Long memberId) {
        Ranking ranking = rankingService.getRankingsByMemberIdAndCompetitionId(competitionId, memberId);
        return ResponseMessage.ok(ranking,"Success");
    }

    // update ranking
    @PutMapping("/{id}")
    public ResponseEntity updateRanking(@Valid @RequestBody UpdateRequestRankingDTO updateRequestRankingDTO, @PathVariable Long id) {
        Ranking ranking1 = rankingService.updateRanking(updateRequestRankingDTO.toRanking(), id);
        return ResponseMessage.ok(ranking1,"Success");
    }

    // delete ranking
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRanking(@PathVariable Long id) {
        rankingService.deleteRanking(id);
        return ResponseMessage.ok(null,"Ranking deleted successfully");
    }

}
