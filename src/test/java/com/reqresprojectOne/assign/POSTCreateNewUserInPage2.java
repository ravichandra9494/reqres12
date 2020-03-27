package com.reqresprojectOne.assign;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.reqresprojectOne.assign.utilities.Resources;
import com.reqresprojectOne.assign.utilities.Utilities;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
/**
 * 
 * @author Ravi
 *
 */
public class POSTCreateNewUserInPage2
{
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws Throwable {
		FileInputStream fis=new FileInputStream(".//Properties//env.properties");
		prop.load(fis);
		fis.close();
	}
	@Test
	public void createUser() throws Throwable {
		
		RestAssured.baseURI=prop.getProperty("baseuri");
		String pageNum = prop.getProperty("page");
		String body = Utilities.getStringFromPath("./File/PostData.json");
		/* given()--all input details*/
		Response resp = RestAssured.given()
				.body(body).header("Content-Type","application/json").queryParam("page",pageNum)
				/* when()--submit the api endpoint*/
		.when().post(Resources.getResourceName())
		/* then()--validate the response*/
		.then().assertThat().statusCode(201)
		.extract().response();
		
		
		JsonPath path = Utilities.rawToJson(resp);
		String strActual = path.getString("id");
		/*print the id from response on console*/
		System.out.println(strActual);
		
	}


}
