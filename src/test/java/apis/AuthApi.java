package apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthApi {

    public static String createToken() {

        String requestBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/auth")
                        .then()
                        .statusCode(200)
                        .extract().response();

        return response.jsonPath().getString("token");
    }
}
