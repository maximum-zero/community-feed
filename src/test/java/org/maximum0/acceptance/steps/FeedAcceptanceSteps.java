package org.maximum0.acceptance.steps;

import io.restassured.RestAssured;
import java.util.List;
import org.maximum0.post.application.dto.CreatePostRequestDto;
import org.maximum0.post.ui.dto.GetPostContentResponseDto;
import org.springframework.http.MediaType;

public class FeedAcceptanceSteps {
    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("data", Long.class);
    }

    public static List<GetPostContentResponseDto> requestFeed(String token) {
        return RestAssured
                .given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("data", GetPostContentResponseDto.class);
    }

    public static Integer requestFeedCode(String token) {
        return RestAssured
                .given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .get("code");
    }

}
