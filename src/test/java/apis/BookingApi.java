package apis;

import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingApi {

    public static Response createBooking() {

        String requestBody = "{\n" +
                "    \"firstname\" : \"Rahul\",\n" +
                "    \"lastname\" : \"Sharma\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-11-01\",\n" +
                "        \"checkout\" : \"2025-11-03\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        return
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .when()
                        .post("/booking");
    }


    public static Response updateBooking(int id, String token) {

        String requestBody = "{\n" +
                "  \"firstname\" : \"Rahul\",\n" +
                "  \"lastname\" : \"Sharma\",\n" +
                "  \"totalprice\" : 111,\n" +
                "  \"depositpaid\" : true,\n" +
                "  \"bookingdates\" : {\n" +
                "    \"checkin\" : \"2025-11-01\",\n" +
                "    \"checkout\" : \"2025-11-09\"\n" +
                "  },\n" +
                "  \"additionalneeds\" : \"Lunch\"\n" +
                "}";
        System.out.println("*** REQUEST BODY *****"+requestBody);
        System.out.println("Updating booking with ID = " + id);
        System.out.println("Using token: " + token);
        Response resp = given()
                .log().all()
                .header("Accept", "application/json")     // FORCE override
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .config(RestAssured.config()
                        .headerConfig(HeaderConfig.headerConfig()
                                .overwriteHeadersWithName("Accept"))) // PREVENT RA FROM ADDING MORE
                .body(requestBody)
                .when()
                .put("/booking/" + id)
                .then()
                .log().all()
                .extract().response();


        System.out.println("**** printing response"+resp);
        return resp;
    }


    public static Response deleteBooking(int id, String token) {
        System.out.println("Deleting booking with ID = " + id);
        return
                given()
                        .contentType(ContentType.JSON)
                        .header("Cookie", "token=" + token)   // IMPORTANT FIX
                        .when()
                        .delete("/booking/" + id);
    }

}
