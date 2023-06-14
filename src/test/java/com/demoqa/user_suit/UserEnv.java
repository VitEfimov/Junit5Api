package com.demoqa.user_suit;

import com.demoqa.base.Base;
import com.demoqa.utilities.Environment;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserEnv extends Base {

    String userName = Environment.FIRST_USER_NAME;
    String userPassword = Environment.FIRST_USER_PASSWORD;
    String userID = Environment.FIRST_USERID;
    String userToken = Environment.FIRST_USER_TOKEN;

//    @DisplayName("GenerateToken_USER")
//    @Test
//    @Order(1)
//    public void test1() {
//        Map<String, Object> requestBody = new LinkedHashMap<>();
//        requestBody.put("userName", userName);
//        requestBody.put("password", userPassword);
//
//        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when().post("/GenerateToken").prettyPeek()
//                .then().statusCode(200).contentType("application/json; charset=utf-8").extract().jsonPath();
//        userToken = jsonPath.getString("token");
//        System.out.println("userToken = " + userToken);
//
//    }

    @DisplayName("Authorized_USER")
    @Test
    @Order(2)
    public void test2() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", userPassword);

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .auth().oauth2(userToken)
                .when().post("/Authorized").prettyPeek()
                .then().statusCode(200).contentType("application/json; charset=utf-8").extract().jsonPath();

    }

    @DisplayName("GET_USER")
    @Test
    @Order(3)
    public void test3() {

//                System.out.println("userToken = " + userToken);
//        System.out.println("userID = " + userID);
        given().accept(ContentType.JSON)
//                .auth().basic(userName,userPassword)
                .auth().oauth2(userToken)
                .pathParam("UUID", userID)
                .when().get("/User/{UUID}").prettyPeek()
                .then().statusCode(200);


    }

}
