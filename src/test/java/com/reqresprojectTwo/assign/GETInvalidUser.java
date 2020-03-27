package com.reqresprojectTwo.assign;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.reqresprojectOne.assign.utilities.Resources;

import io.restassured.RestAssured;
import io.restassured.response.Response;
/**
 * 
 * @author Ravi
 *
 */
public class GETInvalidUser 
{
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws Throwable 
	{
		FileInputStream fis=new FileInputStream(".//Properties//env.properties");
		prop.load(fis);
		fis.close();
	}
		@Test
		public void invalidUser()
		{
			RestAssured.baseURI=prop.getProperty("baseuri");
            Resources res=new Resources();
			String resName = res.getResourceName();
			/* given()--all input details*/
			Response resp = RestAssured.given()
					/* when()--submit the api endpoint*/
			.when().get(resName+"/13")
			/* then()--validate the response*/
			.then().assertThat().statusCode(404)
			.extract().response();
			/*print the response on console*/
			System.out.println(resp.asString());
		}
	}
