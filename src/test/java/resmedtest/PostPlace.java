package resmedtest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resmed.payLoad;
import resmed.resources;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

public class PostPlace {
	Properties prop=new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		//FileInputStream fis=new FileInputStream("C:\\Users\\sumanth\\Desktop\\RESTAssuredTest\\src\\main\\java\\TestFramework\\env.properties");
		//prop.load(fis);
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
	public void createPlaceAPI()
	{
		RestAssured.baseURI=prop.getProperty("HOST");
		given().
		
		queryParam("key",prop.getProperty("KEY")).
		body(payLoad.getPostData()).
		when().
		post(resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));
			

	}
}
