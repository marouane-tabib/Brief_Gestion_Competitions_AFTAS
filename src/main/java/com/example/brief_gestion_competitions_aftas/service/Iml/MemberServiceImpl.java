package com.example.brief_gestion_competitions_aftas.service.Iml;

import com.example.brief_gestion_competitions_aftas.model.Member;
import com.example.brief_gestion_competitions_aftas.repository.MemberRepository;
import com.example.brief_gestion_competitions_aftas.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService
{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member id " + id + " not found"));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member addMember(Member member) {
        // add access date and it's date of today
        member.setAccessDate(java.time.LocalDate.now());
        // add identity number do UUId
        member.setIdentityNumber(java.util.UUID.randomUUID().toString());
        // add membership number integer and must be unique
        member.setMembershipNumber((int) (memberRepository.count() + 1));
        return memberRepository.save(member);
    }

    @Override
    public List<Member> findByNameOrMembershipNumberOrFamilyName(String searchTerm) {
        return memberRepository.findByMembershipNumberOrNameOrFamilyName(searchTerm);
    }

    @Override
    public Member updateMember(Member member, Long id) {
        Member existingMember = getMemberById(id);
        existingMember.setName(member.getName());
        existingMember.setFamilyName(member.getFamilyName());
        existingMember.setNationality(member.getNationality());
        existingMember.setIdentityDocumentType(member.getIdentityDocumentType());
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
