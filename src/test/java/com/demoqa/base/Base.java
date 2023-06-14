package com.demoqa.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class Base {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://demoqa.com/Account/v1";
    }
}
