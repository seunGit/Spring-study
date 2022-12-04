package com.sparta.myselectshopbeta.naver.service;

import com.sparta.myselectshopbeta.naver.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NaverApiService {

    public List<ItemDto> searchItems(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "IZSY5wGHzoKCjo6XKQwH");
        headers.add("X-Naver-Client-Secret", "2kmD6dH8_B");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query , HttpMethod.GET, requestEntity, String.class);

        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        log.info("NAVER API Status Code : " + status);

        String response = responseEntity.getBody();

        return fromJSONtoItems(response);
    }

    public List<ItemDto> fromJSONtoItems(String response) { // fromJSONtoItems 메서드를 만들어서 dto로 변환.(JSONObject와 JSONArray를 사용하여 반환.)

        JSONObject rjson = new JSONObject(response);        // 위의 String값을 JSONObject에 넣어줘서 JSONObject rjson 변수에 넣어준다.
        JSONArray items  = rjson.getJSONArray("items"); // getJSONArray 메서드를 사용하여 key : items 부분을 꺼내서 JSONArray items에 넣어준다.
        List<ItemDto> itemDtoList = new ArrayList<>();        // ItemDto 리스트에 넣어준 다음

        for (int i=0; i<items.length(); i++) {              // 위의 openapi에 display=15 즉 15개를 설정했기 때문에 15개의 형식으로 들어온다. items의 길이만큼 for문을 돌면서
            JSONObject itemJson = items.getJSONObject(i);   //
            ItemDto itemDto = new ItemDto(itemJson);        // ItemDto로 변환
            itemDtoList.add(itemDto);                       // 리스트 형식으로 반환.
        }

        return itemDtoList;
    }
}