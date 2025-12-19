package com.inventory.management;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProductResourceTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    public void startWireMock() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8089));
        wireMockServer.start();
    }

    @AfterEach
    public void stopWireMock() {
        wireMockServer.stop();
    }

    @Test
    public void testCreateAndGetProduct() {
        String location = given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Test Product\", \"description\":\"Desc\", \"price\":10.0, \"quantity\":5}")
                .when().post("/products")
                .then()
                .statusCode(201)
                .extract().header("Location");

        Long id = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));

        given()
                .when().get("/products/" + id)
                .then()
                .statusCode(200)
                .body("name", is("Test Product"));
    }

    @Test
    public void testUpdateProduct() {
        String location = given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Old\", \"description\":\"Old Desc\", \"price\":5.0, \"quantity\":1}")
                .when().post("/products")
                .then()
                .statusCode(201)
                .extract().header("Location");

        Long id = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));

        given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"New\", \"description\":\"New Desc\", \"price\":15.0, \"quantity\":10}")
                .when().put("/products/" + id)
                .then()
                .statusCode(200)
                .body("name", is("New"));
    }

    @Test
    public void testDeleteProduct() {
        String location = given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"To Delete\", \"description\":\"Desc\", \"price\":10.0, \"quantity\":5}")
                .when().post("/products")
                .then()
                .statusCode(201)
                .extract().header("Location");

        Long id = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));

        given()
                .when().delete("/products/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/products/" + id)
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetEnrichedSuccess() {
        String location = given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Test\", \"description\":\"Local Desc\", \"price\":10.0, \"quantity\":5}")
                .when().post("/products")
                .then()
                .statusCode(201)
                .extract().header("Location");

        Long id = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));

        wireMockServer.stubFor(WireMock.get("/products/" + id)
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":" + id + ", \"title\":\"Title\", \"description\":\"Ext Desc\", \"category\":\"Cat\", \"image\":\"img.jpg\"}")));

        given()
                .when().get("/products/" + id + "/enriched")
                .then()
                .statusCode(200)
                .body("description", is("Ext Desc"))
                .body("category", is("Cat"))
                .body("image", is("img.jpg"));
    }

    @Test
    public void testGetEnrichedFailureFallback() {
        String location = given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Test Fail\", \"description\":\"Local Desc\", \"price\":20.0, \"quantity\":10}")
                .when().post("/products")
                .then()
                .statusCode(201)
                .extract().header("Location");

        Long id = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));

        wireMockServer.stubFor(WireMock.get("/products/" + id)
                .willReturn(WireMock.aResponse().withStatus(500)));

        given()
                .when().get("/products/" + id + "/enriched")
                .then()
                .statusCode(200)
                .body("description", is("Local Desc"))
                .body("category", nullValue())
                .body("image", nullValue());
    }
}
