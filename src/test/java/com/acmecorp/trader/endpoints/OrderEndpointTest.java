package com.acmecorp.trader.endpoints;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.acmecorp.trader.TraderApplication;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = { TraderApplication.class })

public class OrderEndpointTest {
	// @Value("${local.server.port}")
	// private int serverPort = 8080;

	/*
	 * @Before public void init(){ RestAssured.port = serverPort; }
	 */
	@Test
	public void postmarketOrderHappyPath() {
		given().contentType("application/json").auth().basic("trader1", "password")
				.body("{\"symbol\":\"AAPL\",\"shares\":15,\"side\":\"BUY\",\"ordertype\":\"MARKET\"}").when().post("/order").then()
				.statusCode(HttpStatus.SC_OK)
				.body("symbol", equalTo("AAPL"), "shares", equalTo(15), "side", equalTo("BUY"), "orderType", equalTo("MARKET"), "traderId", equalTo("trader1"));
		// body("text", equalTo("abc"));

	}

	@Test
	public void postmarketOrderSadPath() {
		given().contentType("application/json").auth().basic("user", "password")
				.body("{\"symbol\":\"AAPL\",\"shares\":15,\"side\":\"BUY\"}").when().post("/order").then()
				.statusCode(HttpStatus.SC_UNAUTHORIZED);
		// body("text", equalTo("abc"));

	}
	
	@Test
	public void listOrderByTraderHappyPath() {
		given().accept(MediaType.APPLICATION_JSON_VALUE).auth().basic("trader1", "password").when().get("/orders/trader").then().statusCode(HttpStatus.SC_OK);// .
		// body("text", equalTo("abc"));
		
	}

	@Test
	public void listOrdersHappyPath() {
		given().accept(MediaType.APPLICATION_JSON_VALUE).auth().basic("admin", "password").when().get("/orders").then().statusCode(HttpStatus.SC_OK);// .
		// body("text", equalTo("abc"));

	}
	
	@Test
	public void postLimitOrderHappyPath() {
		given().contentType("application/json").auth().basic("trader1", "password")
				.body("{\"symbol\":\"AAPL\",\"shares\":15,\"side\":\"BUY\",\"ordertype\":\"LIMIT\"}").when().post("/order").then()
				.statusCode(HttpStatus.SC_OK)
				.body("symbol", equalTo("AAPL"), "shares", equalTo(15), "side", equalTo("BUY"), "orderType", equalTo("LIMIT"), "traderId", equalTo("trader1"));

	}
	
	@Test
	public void postlimitOrderSadPath() {
		given().contentType("application/json").auth().basic("user", "password")
				.body("{\"symbol\":\"AAPL\",\"shares\":15,\"side\":\"BUY\"}").when().post("/order").then()
				.statusCode(HttpStatus.SC_UNAUTHORIZED);
		// body("text", equalTo("abc"));

	}
	
}
