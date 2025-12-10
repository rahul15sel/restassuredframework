package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class RestAssuredConfig {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        RestAssured.filters(new AllureRestAssured());   // Allure logs request + response
    }
}
