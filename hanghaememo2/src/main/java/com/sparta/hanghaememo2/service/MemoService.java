package com.sparta.hanghaememo2.service;


import com.sparta.hanghaememo2.dto.MemoRequestDto;
import com.sparta.hanghaememo2.entity.Memo;
import com.sparta.hanghaememo2.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto memoRequestDto) {
        Memo memo = new Memo(memoRequestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true) // 읽기 특화
    public List<Memo> listMemo() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Long updateMemo(Long id, MemoRequestDto memoRequestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("바보")
        );
        memo.updateMemo(memoRequestDto);
        return memo.getId();
    }

    @Transactional
    public Long delMemo(Long id) {
        memoRepository.deleteById(id);
        return id;
    }
}