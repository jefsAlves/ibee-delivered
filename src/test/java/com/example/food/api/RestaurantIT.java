package com.example.food.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.food.model.entities.Restaurant;
import com.example.food.model.repository.RestaurantRepository;
import com.example.food.util.DatabaseCleaner;
import com.example.food.util.MessageUtilTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "/application-test.properties")
public class RestaurantIT {

	@LocalServerPort
	private Integer serverPort;

	@Autowired
	private DatabaseCleaner dataBaseCleaner;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = serverPort;
		RestAssured.basePath = MessageUtilTests.PATH_RESOUCE_RESTAURANT;
		dataBaseCleaner.clearTables();
		createDataRestaurant();
	}
	
	@Test
	public void shouldReturnStatus200_WhenGetRestaurant() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()                                                                                                                                                                 
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void validingRequestPathParam_WhenStatusCodeAndBodyIsValid_ShouldReturnSucess() {
		given()
			.pathParam(MessageUtilTests.NAME_VARIABLE_RESTAURANT, MessageUtilTests.ID_RESTAURANT_VALID)
			.accept(ContentType.JSON)
		.when()
			.get(MessageUtilTests.PATH_VARIABLE_RESTAURANT)
		.then()
			.statusCode(HttpStatus.OK.value())
			.body(MessageUtilTests.RESTAURANT_NAME_KEY, equalTo(MessageUtilTests.RESTAURANT_NAME_VALUE));
	}
	
	@Test
	public void validingRequestQueryParam_WhenParamIsValid_ShoulReturnSucess() {
		given()
			.queryParam("findByName", MessageUtilTests.RESTAURANT_NAME_VALUE)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void validingRequestPathParam_WhenIdNotExist_ShouldRetunError() {
		given()
			.pathParam(MessageUtilTests.NAME_VARIABLE_RESTAURANT, MessageUtilTests.ID_RESTAURANT_VALID)
			.accept(ContentType.JSON)
		.when()
			.get(MessageUtilTests.PATH_VARIABLE_RESTAURANT)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void whenValidBodyRestaurant_ShouldReturnSucess() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(2));
	}

	private void createDataRestaurant() {
		Restaurant restaurantFirst = new Restaurant();
		restaurantFirst.setName(MessageUtilTests.RESTAURANT_NAME_VALUE);
		restaurantFirst.setFreigthRate(new BigDecimal(5.12));
		restaurantRepository.save(restaurantFirst);
	}

}
