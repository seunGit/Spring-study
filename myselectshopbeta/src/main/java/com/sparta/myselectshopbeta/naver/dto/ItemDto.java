package com.sparta.myselectshopbeta.naver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int lprice;                                     // Dto변수에 아래 설정한 키값을 넣어준다.

    public ItemDto(JSONObject itemJson) {                   // JSONObject를 파라미터값으로 받은 다음
        this.title = itemJson.getString("title");       // 설정한 키값을 가져온다. (Json 형식의 items안에 있는 키값)
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }
}