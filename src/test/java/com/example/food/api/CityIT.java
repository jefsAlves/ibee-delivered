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

import com.example.food.model.entities.City;
import com.example.food.model.repository.CityRepository;
import com.example.food.util.DatabaseCleaner;
import com.example.food.util.MessageUtilTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "/application-test.properties")
public class CityIT {
	
	@LocalServerPort
	private Integer serverPort;
	
	@Autowired
	private DatabaseCleaner dataBaseCleaner;
	
	@Autowired
	private CityRepository cityRepository;
	
	@BeforeEach
	public void setUp() {
		RestAssured.basePath = MessageUtilTests.PATH_RESOURCE_CITY;
		RestAssured.port = serverPort;
		dataBaseCleaner.clearTables();
		createCity();
	}

	@Test
	public void whenRequest_ByIdIsValid_ShouldReturnSucess() {
		given()
			.pathParam("cityId", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{cityId}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	private void createCity() {
		City city = new City();
		city.setId(1L);
		city.setName("San Francisco");
		cityRepository.save(city);
	}

}
