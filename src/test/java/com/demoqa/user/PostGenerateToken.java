package com.demoqa.user;

import com.demoqa.base.Base;
import com.demoqa.utilities.ConfigurationReader;
import com.demoqa.utilities.Environment;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostGenerateToken extends Base {
    @DisplayName("GenerateToken_USER_1")
    @Test
    public void authorizedUser() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", Environment.FIRST_USER_NAME);
        requestBody.put("password", Environment.FIRST_USER_PASSWORD);

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/GenerateToken").prettyPeek()
                .then().statusCode(200).contentType("application/json; charset=utf-8").extract().jsonPath();


        jsonPath.getString("token");

    }

}
