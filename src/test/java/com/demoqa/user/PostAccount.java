package com.demoqa.user;

import com.demoqa.base.Base;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PostAccount extends Base {

    @Disabled("This TC is ignored\n")
    @DisplayName("POST_USER_1")
    @Test
    public void postUser() {

//        Map<String, Object> requestBody = new LinkedHashMap<>();
//        requestBody.put("userName", "James Bond");
//        requestBody.put("password", "PasswordJB$5");

//        Map<String, Object> requestBody = new LinkedHashMap<>();
//        requestBody.put("userName", "Luke Skywalker");
//        requestBody.put("password", "PasswordLS$5");

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", "Tony Stark");
        requestBody.put("password", "PasswordTS$5");


        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/User").prettyPeek()
                .then().statusCode(201).contentType("application/json; charset=utf-8").extract().jsonPath();

        String userID = jsonPath.getString("userID");
        System.out.println("userID = " + userID);
    }
}
