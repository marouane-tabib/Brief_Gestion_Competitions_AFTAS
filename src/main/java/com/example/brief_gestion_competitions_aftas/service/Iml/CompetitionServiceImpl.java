package com.example.brief_gestion_competitions_aftas.service.Iml;

import com.example.brief_gestion_competitions_aftas.handlers.exception.OperationException;
import com.example.brief_gestion_competitions_aftas.handlers.exception.ResourceNotFoundException;
import com.example.brief_gestion_competitions_aftas.model.Competition;
import com.example.brief_gestion_competitions_aftas.model.Member;
import com.example.brief_gestion_competitions_aftas.model.Ranking;
import com.example.brief_gestion_competitions_aftas.repository.CompetitionRepository;
import com.example.brief_gestion_competitions_aftas.repository.RankingRepository;
import com.example.brief_gestion_competitions_aftas.service.CompetitionService;
import com.example.brief_gestion_competitions_aftas.service.MemberService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private RankingRepository rankingRepository;
    private final MemberService memberService;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, RankingRepository rankingRepository, MemberService memberService) {
        this.competitionRepository = competitionRepository;
        this.rankingRepository = rankingRepository;
        this.memberService = memberService;
    }
    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Competition id " + id + " not found"));
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public List<Competition> getCompetitionsByStatus(String status) {
        return null;
    }

    @Override
    public Competition addCompetition(Competition competition) {

        // here i want to check that Every day there can be only one competition
        Competition competition1 = competitionRepository.findByDate(competition.getDate());
        if (competition1 != null) {
            throw new OperationException("There is already a competition on " + competition.getDate());
        }

        // here i want to check that Competition start time must be before end time
        if (competition.getStartTime().isAfter(competition.getEndTime())) {
            throw new OperationException("Start time must be before end time");
        }

        // here i want to generate a unique code for the competition from that date and location  pattern: ims-22-12-23, ims is the third first letters of the location
        String code = generateCode(competition.getLocation(), competition.getDate());
        competition.setCode(code);

        return competitionRepository.save(competition);

    }

    public static String generateCode(String location, Date date) {
        String locationCode = location.substring(0, 3).toLowerCase();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yy-MM-dd");
        String formattedDate = dateFormatter.format(date);

        return locationCode + "-" + formattedDate;
    }

    public static String generateCode(String location, LocalDate date) {
        String locationCode = location.substring(0, 3).toLowerCase();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = date.format(dateFormatter);

        return locationCode + "-" + formattedDate;
    }

    @Override
    public Competition updateCompetition(Competition competition, Long id) {
        Competition existingCompetition = getCompetitionById(id);
        // check if date is changed so we can check if there is already a competition on that date
        System.out.println(competition.getDate());
        System.out.println(existingCompetition.getDate());
        if (!competition.getDate().equals(existingCompetition.getDate())) {
            Competition competition1 = competitionRepository.findByDate(competition.getDate());
            if (competition1 != null) {
                throw new OperationException("There is already a competition on " + competition.getDate());
            }
        }
        existingCompetition.setDate(competition.getDate());
        if (competition.getStartTime().isAfter(competition.getEndTime())) {
            throw new OperationException("Start time must be before end time");
        }
        existingCompetition.setStartTime(competition.getStartTime());
        existingCompetition.setEndTime(competition.getEndTime());
        existingCompetition.setLocation(competition.getLocation());
        if(competition.getLocation() != existingCompetition.getLocation() || competition.getDate() != existingCompetition.getDate()){
            String code = generateCode(competition.getLocation(), competition.getDate());
            existingCompetition.setCode(code);
        }

        existingCompetition.setAmount(competition.getAmount());
        return competitionRepository.save(existingCompetition);
    }

    @Override
    public void deleteCompetition(Long id) {
        getCompetitionById(id);
        competitionRepository.deleteById(id);
    }

    @Override
    public Ranking registerMemberForCompetition(Ranking ranking1) {
        Long competitionId = ranking1.getCompetition().getId();
        Long memberId = ranking1.getMember().getId();

        // here i want to check that the competition is exist
        Competition competition = getCompetitionById(competitionId);

        // here i want to check that the member is exist
        Member member = memberService.getMemberById(memberId);

        // here i want to check that the member is not already registered for the competition
        if(competition.getRanking().stream().anyMatch(ranking -> ranking.getMember().getId().equals(memberId))){
            throw new OperationException("Member id " + memberId + " is already registered for the competition");
        }

        // here i want to check that the competition is not started yet and are still have 24 hours before the start time
        if(competition.getStartTime().isBefore(competition.getStartTime().minusHours(24))){
            throw new OperationException("Competition id " + competitionId + " is closed for registration");
        }


        // here i want to register the member for the competition
        return rankingRepository.save(ranking1);
    }

//    @Override
//    public Ranking recordCompetitionResult(Ranking ranking, Long id) {
////        return rankingService.updateRankingScore(ranking, id);
//    }
//    @Override
//    public void recordCompetitionResult(Ranking ranking) {
//        Long competitionId = ranking.getCompetition().getId();
//        Long memberId = ranking.getMember().getId();
//        int result = ranking.getScore();
//        // here i want to check that the competition is exist
//        Competition competition = getCompetitionById(competitionId);
//        if(competition == null){
//            throw new RuntimeException("Competition id " + competitionId + " not found");
//        }
//        // here i want to check that the member is exist
//        Member member = memberService.getMemberById(memberId);
//        if(member == null){
//            throw new RuntimeException("Member id " + memberId + " not found");
//        }
//        // here i want to check that the member is registered for the competition
//        if(competition.getRanking().stream().noneMatch(ranking1 -> ranking1.getMember().getId().equals(memberId))){
//            throw new RuntimeException("Member id " + memberId + " is not registered for the competition");
//        }
//        // here i want to check that the competition is not passed 24 hours after the end time here type of EndTime is LocalTime
//        if(competition.getEndTime().isBefore(competition.getEndTime().plusHours(24))){
//            throw new RuntimeException("Competition id " + competitionId + " is closed for registration");
//        }
//        // here i want to register the result for the member
//        rankingService.addRanking(ranking);
//    }
}
