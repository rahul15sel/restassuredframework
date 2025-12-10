package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}

