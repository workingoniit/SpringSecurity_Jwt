package com.example.loginDemo.repository;

import com.example.loginDemo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member save(Member member);

    Optional<Member> findById(Integer id);

    Optional<Member> findByEmail(String email);

    List<Member> findAll();
}