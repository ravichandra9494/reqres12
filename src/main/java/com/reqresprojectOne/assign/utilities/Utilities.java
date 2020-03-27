package com.reqresprojectOne.assign.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utilities {
	
	public static String getStringFromPath(String file_path) throws Throwable
	{
		return new String(Files.readAllBytes(Paths.get(file_path)));
	}

	public static JsonPath rawToJson(Response resp) 
	{
		return new JsonPath(resp.asString());
		
	}
}
