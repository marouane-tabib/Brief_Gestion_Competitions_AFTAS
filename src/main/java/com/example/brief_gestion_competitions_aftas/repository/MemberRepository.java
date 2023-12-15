package com.example.brief_gestion_competitions_aftas.repository;

import com.example.brief_gestion_competitions_aftas.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    // search by name or membership number or family name
    @Query(value =
            "SELECT * FROM member WHERE membership_number = :searchTerm " +
            "OR name LIKE %:searchTerm% OR family_name LIKE %:searchTerm%", nativeQuery = true)
    List<Member> findByMembershipNumberOrNameOrFamilyName(@Param("searchTerm") String searchTerm);
}
