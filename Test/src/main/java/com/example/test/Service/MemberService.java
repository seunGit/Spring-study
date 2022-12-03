package com.example.test.Service;

import com.example.test.Dto.MemberResponseDto;
import com.example.test.Repositoty.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public MemberResponseDto findMember(Long id) {
        Member member = (Member) memberRepository.findById(id).orElseThrow(
                () -> new RuntimeException("회원 상세 조회 실패")
        );
        return new MemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMember() {
       return memberRepository.findAllByOrderByModifiedAtDesc();


    }


}