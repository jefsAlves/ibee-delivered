package com.example.food.api;

import static io.restassured.RestAssured.given;

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

import com.example.food.model.entities.Kitchen;
import com.example.food.model.repository.KitchenRepository;
import com.example.food.util.DatabaseCleaner;
import com.example.food.util.MessageUtilTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "/application-test.properties")
public class KitchenIT {

	@LocalServerPort
	private Integer serverPort;
	
	@Autowired
	private DatabaseCleaner databaseCleaner; 
	
	@Autowired
	private KitchenRepository kitchenRepository; 

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = serverPort;
		RestAssured.basePath = MessageUtilTests.PATH_RESOURCE_KITCHEN;
		databaseCleaner.clearTables();
		createDataKitchen();
	}

	@Test
	public void whenFindKitchenById_ShouldReturnSucess() {
		given()
			.pathParam(MessageUtilTests.NAME_VARIABLE_KITCHEN, MessageUtilTests.ID_KITCHEN_VALID)
			.accept(ContentType.JSON)
		.when()
			.get(MessageUtilTests.PATH_VARIABLE_KITCHEN)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void whenFindKitchenById_IdIsInvalid_ShouldReturnFail() {
		given()
			.pathParam(MessageUtilTests.NAME_VARIABLE_KITCHEN, MessageUtilTests.ID_KITCHEN_INVALID)
			.accept(ContentType.JSON)
		.when()
			.get(MessageUtilTests.PATH_VARIABLE_KITCHEN)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	

	private void createDataKitchen() {
		Kitchen kitchenFirst = new Kitchen();
		kitchenFirst.setName(MessageUtilTests.KITCHEN_NAME_VALUE);
		kitchenRepository.save(kitchenFirst);
	}
}
