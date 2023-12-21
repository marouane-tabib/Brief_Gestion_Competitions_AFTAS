package com.example.brief_gestion_competitions_aftas.controller;

import com.example.brief_gestion_competitions_aftas.dto.CompetitionRequestDTO;
import com.example.brief_gestion_competitions_aftas.dto.RegisterMemberRequest;
import com.example.brief_gestion_competitions_aftas.handlers.response.ResponseMessage;
import com.example.brief_gestion_competitions_aftas.model.Competition;
import com.example.brief_gestion_competitions_aftas.model.Ranking;
import com.example.brief_gestion_competitions_aftas.service.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {

    private CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getCompetitionById(@PathVariable Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        if(competition == null) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            return ResponseMessage.ok(competition, "Success");
        }
    }

    @GetMapping
    public ResponseEntity getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        if(competitions.isEmpty()) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            return ResponseMessage.ok(competitions, "Success");
        }
    }

    @PostMapping
    public ResponseEntity addCompetition(@Valid @RequestBody CompetitionRequestDTO competitionRequestDTO) {
        Competition competition1 = competitionService.addCompetition(competitionRequestDTO.toCompetition());
        if(competition1 == null) {
            return ResponseMessage.badRequest("Competition not created");
        }else {
            return ResponseMessage.created(competition1, "Competition created successfully");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCompetition(@RequestBody CompetitionRequestDTO competitionRequestDTO, @PathVariable Long id) {
        System.out.println(competitionRequestDTO);
        Competition competition1 = competitionService.updateCompetition(competitionRequestDTO.toCompetition(), id);
        if(competition1 == null) {
            return ResponseMessage.badRequest("Competition not updated");
        }else {
            return ResponseMessage.created(competition1, "Competition updated successfully");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
    }

    // register member for competition
    @PostMapping("/register-member")
    public ResponseEntity registerMemberForCompetition(@Valid @RequestBody RegisterMemberRequest registerMemberRequest) {
        Ranking ranking = competitionService.registerMemberForCompetition(registerMemberRequest.toRanking());
        return ResponseMessage.ok(ranking,"Member registered successfully");
    }
}
