package resmedtest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resmed.payLoad;
import resmed.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostAndDelete {
	
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		ResourceBundle mybundle = ResourceBundle.getBundle("env");
		Enumeration<String> bundleKeys = mybundle.getKeys();
        while (bundleKeys.hasMoreElements()) {
        String key = (String)bundleKeys.nextElement();
        String value = mybundle.getString(key);
        prop.put(key, value);
        }
		
		//prop.get("HOST");
	}

	@Test
	public void AddandDeletePlace()
	{
		
		//Task 1- Grab the response
		RestAssured.baseURI= prop.getProperty("HOST");
		Response res=given().
		
		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.getPostData()).
		when().
		post(resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		extract().response();
		// Task 2- Grab the Place ID from response
		
		String responseString=res.asString();
		System.out.println(responseString);
		JsonPath js= new JsonPath(responseString);
		String placeid=js.get("place_id");
		System.out.println(placeid);
		
		
		//Task 3 place this place id in the Delete request
		given().
		queryParam("key","AIzaSyC_cwM2pwQlVl6Mg4NWksJwhHTD2cz6W9I").
		
		body("{"+
  "\"place_id\": \""+placeid+"\""+
"}").
		when().
		post(resources.placeDeleteData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
		
		
	}
}
