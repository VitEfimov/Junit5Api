package com.demoqa.user_suit;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class NewUser {

    String userURL = "https://demoqa.com/Account/v1";

    String userName = "Jimm Bzjeck";
    String userPassword = "PasswordJI7&";
    static String userID;
    static String userToken;

    @DisplayName("POST_USER")
    @Test
    @Order(1)
    public void test1() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", userPassword);

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(userURL+"/User").prettyPeek()
                .then().statusCode(201).contentType("application/json; charset=utf-8").extract().jsonPath();

        userID = jsonPath.getString("userID");
        System.out.println("userID = " + userID);
        String actualName = jsonPath.getString("username");
        Assertions.assertEquals(userName,actualName);

    }

    @DisplayName("GenerateToken_USER")
    @Test
    @Order(2)
    public void test2() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", userPassword);

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(userURL+"/GenerateToken").prettyPeek()
                .then().statusCode(200).contentType("application/json; charset=utf-8").extract().jsonPath();
        userToken = jsonPath.getString("token");
        System.out.println("userToken = " + userToken);
    }

    @DisplayName("Authorized_USER")
    @Test
    @Order(3)
    public void test3() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", userPassword);

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(userURL+"/Authorized").prettyPeek()
                .then().statusCode(200).contentType("application/json; charset=utf-8").extract().jsonPath();

    }

    @DisplayName("POST_USER - User exists!")
    @Test
    @Order(4)
    public void test4() {

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("userName", userName);
        requestBody.put("password", userPassword);

        JsonPath jsonPath = given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(userURL+"/User").prettyPeek()
                .then().statusCode(406).contentType("application/json; charset=utf-8").extract().jsonPath();

        String expectedMessage = "User exists!";
        String actualMessage = jsonPath.getString("message");
        Assertions.assertEquals(expectedMessage,actualMessage);
    }

    @DisplayName("GET_USER")
    @Test
    @Order(5)
    public void test5() {
//        System.out.println("userToken = " + userToken);
//        System.out.println("userID = " + userID);

        given().accept(ContentType.JSON)
                .auth().oauth2(userToken)
                .pathParam("UUID", userID)
                .when().get(userURL+"/User/{UUID}").prettyPeek()
                .then().statusCode(200);
    }


    @DisplayName("DELETE_USER")
    @Test
    @Order(8)
    public void test8() {
        given()
                .auth().oauth2(userToken)
                .pathParam("UUID", userID)
                .when().delete(userURL+"/User/{UUID}").prettyPeek()
                .then().statusCode(204);
    }
}
