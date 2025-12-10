package tests;

import apis.AuthApi;
import apis.BookingApi;
import config.RestAssuredConfig;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Epic("Booking API Tests")
@Feature("Update & Delete Booking")
public class BookingTests extends RestAssuredConfig {

    @Test(description = "Validate create, update and delete booking flow")
    @Severity(SeverityLevel.CRITICAL)
    @Story("End-to-end booking update flow")
    public void testUpdateAndDeleteBooking() {

        Allure.step("Generate token");
        String token = AuthApi.createToken();

        Allure.step("Create new booking");
        Response createResp = BookingApi.createBooking();
        int id = createResp.jsonPath().getInt("bookingid");

        Allure.step("Update booking using token");
        Response updateResp = BookingApi.updateBooking(id, token);

        Allure.step("Validate update status (200)");
        assert updateResp.statusCode() == 200;

        Allure.step("Delete booking");
        Response deleteResp = BookingApi.deleteBooking(id, token);

        Allure.step("Validate deletion status (201)");
        assert deleteResp.statusCode() == 201;
    }
}