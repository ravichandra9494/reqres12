package com.reqresprojectOne.assign;

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
public class GETSingleUserFromPage2 
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
	public void singleUser()
	{
		
		RestAssured.baseURI=prop.getProperty("baseuri");
		String pageNum = prop.getProperty("page");
		Resources res=new Resources();
		String resName = res.getResourceName();
		/* given()--all input details*/
		Response resp = RestAssured.given().queryParam("page",pageNum ).and().queryParam("id",7)
				/* when()--submit the api endpoint*/
		.when().get(resName)
		/* then()--validate the response*/
		.then().assertThat().statusCode(200)
		.extract().response();
		/*print the response on console*/
		System.out.println(resp.prettyPrint());
		
	}
	

}
