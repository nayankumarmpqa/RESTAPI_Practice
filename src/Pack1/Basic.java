package Pack1;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import payloadFiles.AddPlacePayload;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
public class Basic {

	public static void main(String[]  rgs) {
		// Validate Add place API is working fine
		/*
		 * given - all input details
		 * when -  submit the API - resource , http method
		 * then - validate the response
		 */
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	String response = 			//getting response from below code into a string
		given()
		.log().all() // to Log the request in the console
				.queryParam("key", "qaclick123")
				.header("Content-Type","application/json")
				.body(AddPlacePayload.AddPlacePayLoadText())
		
		.when()
				.post("maps/api/place/add/json")
		.then()
		.log().all() // to Log the response in the console
				.assertThat() //below mentioning the required validations
					.statusCode(200)
					.body("scope", equalTo("APP"))
					.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println("I got the place_id as = " + placeId);
		System.out.println();
		System.out.println();
		
		//Update Place
		String newAddress = "new address with Summer walk, USA"; 
		given()
		.log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"")
		.when()
			.put("/maps/api/place/update/json")
		
		.then()
		//.log().all()
			.assertThat() //below mentioning the required validations
				.statusCode(200)
				.body("msg",equalTo("Address successfully updated"));
		
		//Get Place
		String getPlaceresponse = 
		
		given()
		.log().all()
		.queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		
		.when()
		.get("/maps/api/place/get/json")
		
		.then()
		.log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		// Using JsonPath object to parse the string and getting the attribute named address value.
		JsonPath js1 = new JsonPath(getPlaceresponse);
		String actualAddress = js1.getString("address");
		System.out.println("Actual address is now = " + actualAddress);
		//Now use testNG jars, assertion to compare the newAddress and actualAddress
		Assert.assertEquals(actualAddress, newAddress);
		
	}
}
