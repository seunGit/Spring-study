package com.sparta.hanghaememo2.controller;

import com.sparta.hanghaememo2.dto.MemoRequestDto;
import com.sparta.hanghaememo2.entity.Memo;
import com.sparta.hanghaememo2.repository.MemoRepository;
import com.sparta.hanghaememo2.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @PostMapping
    public Memo createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        return memoService.createMemo(memoRequestDto);
    }

    @GetMapping
    public List<Memo> listMemo() {
        return memoService.listMemo();
    }

    @PutMapping
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        return memoService.updateMemo(id, memoRequestDto);
    }

    @DeleteMapping
    public Long delMemo(@PathVariable Long id) {
        return memoService.delMemo(id);
    }


}