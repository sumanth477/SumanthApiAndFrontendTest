package resmedtest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetPlace {
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
public void getPlaceAPI()
{
		// TODO Auto-generated method stub

		//BaseURL or Host
		RestAssured.baseURI=prop.getProperty("HOST");
		
		given().
		       param("location","-33.738528,150.950528").
		       param("radius","500").
		       queryParam("key",prop.getProperty("KEY")).
		       param("name","ResMed").
		       when().
		       get("/maps/api/place/nearbysearch/json").
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		       body("results[0].name",equalTo("ResMed")).and().
		       body("results[0].place_id", equalTo("ChIJayMvSquYEmsRnjb5Cq3zRQc")).and().
		       header("Server","pablo");
		        
		      
	
}

}
