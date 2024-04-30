import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class APIclass {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    public void test1() {
        String s = "{\"id\":11}";

        given().
                contentType("application/json").
                body(s).
                when().
                post("https://petstore.swagger.io/v2/pet").
                then().
                statusCode(200).
                body("id", equalTo(11));

    }

    @org.junit.jupiter.api.Test
    public void testGet() {
        RestAssured
                .get("https://petstore.swagger.io/v2/pet/11")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", equalTo(11));
    }

    @org.junit.jupiter.api.Test
    public void testGetNotFoundPet() {
        RestAssured
                .get("https://petstore.swagger.io/v2/pet/11")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @org.junit.jupiter.api.Test
    public void testPut() {
        String s = "{\"id\":11}";
        given().
                contentType("application/json").
                body(s).
                when().
                put("https://petstore.swagger.io/v2/pet").
                then().
                statusCode(200).
                body("id", equalTo(11));
    }

    @org.junit.jupiter.api.Test
    public void testPutRequest() {

        String requestBody = "{\"id\": 11, \"name\": \"Masik\"}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("Masik"))
                .extract()
                .response();
    }

    @Test
    public void testDeleteRequestError() {

        Response response;
        response = given()
                .when()
                .delete("https://petstore.swagger.io/v2/pet/15")
                .then()
                .statusCode(404)
                .extract()
                .response();
    }
}