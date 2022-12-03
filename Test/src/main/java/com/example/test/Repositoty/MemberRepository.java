package com.example.test.Repositoty;

import com.example.test.Dto.MemberResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
