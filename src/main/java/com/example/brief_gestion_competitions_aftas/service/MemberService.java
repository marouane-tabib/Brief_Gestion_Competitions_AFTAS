package com.example.brief_gestion_competitions_aftas.service;

import com.example.brief_gestion_competitions_aftas.model.Member;

import java.util.List;

public interface MemberService {
    Member getMemberById(Long id);
    List<Member> getAllMembers();
    Member addMember(Member member);
    List<Member> findByNameOrMembershipNumberOrFamilyName(String searchTerm);
    Member updateMember(Member member, Long id);
    void deleteMember(Long id);
}
