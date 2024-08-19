package tests;

import Builder.ShopBuilder;
import dto.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import support.SupportFunctions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

public class ShopTests {

    static final String BASE_URL = "https://webservice.toscacloud.com/api/v1/Shops";

    @Test
    public void createShop() throws JsonProcessingException {
        Shop newshop = ShopBuilder.createShop(9, "Tokyo", "JJc", "Japan");

        String jsonBody = SupportFunctions.convertJavaObjectToJson(newshop);
        System.out.println(jsonBody);
        ValidatableResponse body = given()
                .body(jsonBody)

                .contentType("application/json")
                .post(BASE_URL)
                .then()
                .statusCode(200);


    }



    @Test
    public void putShop() throws JsonProcessingException {
        Shop updatedShop = ShopBuilder.createShop(9, "Tokyo", "JJc", "Poland");
        String jsonBody = SupportFunctions.convertJavaObjectToJson(updatedShop);
        System.out.println(jsonBody);
        ValidatableResponse response = given()
                .body(jsonBody)
                .contentType(ContentType.JSON)
                .put(BASE_URL)
                .then()
                .statusCode(200)
                .body("country", equalTo("Poland"));
        response.log().all();
    }

    @Test

    public void getShop() {
        RestAssured.get(BASE_URL)
                .prettyPeek()
                .then()
                .statusCode(200);


    }

    @Test
    public void verifyShopInGet() {
        int shopId = 9;

        given()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .body("id", hasItem(shopId));
    }
}





