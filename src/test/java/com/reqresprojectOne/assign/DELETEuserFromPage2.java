package com.reqresprojectOne.assign;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.reqresprojectOne.assign.utilities.Resources;
import com.reqresprojectOne.assign.utilities.Utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * 
 * @author Ravi
 *
 */
public class DELETEuserFromPage2
{
Properties prop=new Properties();
	@BeforeTest
	public void getData() throws Throwable {
		
		FileInputStream fis=new FileInputStream(".//Properties//env.properties");
		prop.load(fis);
		fis.close();
	}
	@Test
	public void deleteUser() throws Throwable {
		
		RestAssured.baseURI=prop.getProperty("baseuri");
		String pageNum = prop.getProperty("page");
		String delId = prop.getProperty("delete");
		/* given()--all input details*/
		Response resp = RestAssured.given()
				.queryParam("page",pageNum).and().queryParam("id",delId).log().all()
				/* when()--submit the api endpoint*/
		.when().delete(Resources.getResourceName())
		/* then()--validate the response*/
		.then().assertThat().statusCode(204)
		.extract().response();
		/*print the response on console*/
       resp.prettyPrint();
	}


}
