package com.sparta.myselectshopbeta.naver.controller;


import com.sparta.myselectshopbeta.naver.dto.ItemDto;
import com.sparta.myselectshopbeta.naver.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                                     //
@RequestMapping("/api")                          // 들어오는 요청들을 api로 받는다.
@RequiredArgsConstructor                            // 네이버 api서비스와 연결
public class NaverApiController {

    private final NaverApiService naverApiService;

    @GetMapping("/search")                       // 서치라는 에이피 아이로 요청이 들어오면
    public List<ItemDto> searchItems(@RequestParam String query)  {
        return naverApiService.searchItems(query);
    }
}