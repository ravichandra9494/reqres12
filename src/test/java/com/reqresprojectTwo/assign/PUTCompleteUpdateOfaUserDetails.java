package com.reqresprojectTwo.assign;

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
public class PUTCompleteUpdateOfaUserDetails
{
Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws Throwable {
		
		FileInputStream fis=new FileInputStream(".//Properties//env.properties");
		prop.load(fis);
		fis.close();
	}
	@Test
	public void totalUpdateUser() throws Throwable {
		
		RestAssured.baseURI=prop.getProperty("baseuri");
		String body = Utilities.getStringFromPath("./File/PutData.json");
		/* given()--all input details*/
		Response resp = RestAssured.given()
				.body(body).contentType(ContentType.JSON).log().all()
				/* when()--submit the api endpoint*/
		.when().put(Resources.getResourceName())
       /* then()--validate the response*/
		.then().assertThat().statusCode(200)
		.extract().response();
		/*converting response from raw type to json type*/
        JsonPath path = Utilities.rawToJson(resp);
		String strId = path.getString("id");
		String strMail = path.getString("email");
		String first_Name = path.getString("first_name");
		String last_Name = path.getString("last_name");
		/*print the response on console*/
		System.out.println(strId+" "+strMail+" "+first_Name+" "+last_Name);
		}
}
