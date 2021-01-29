package dynamicJsoneConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;
import payloadFiles.AddbookPayload;;

public class DynamicJSONDemo {
	
	//@Test
	public static void main(String[]  rgs) {
	//public void addBook() {
		
		String addBookResponse= 
		RestAssured.baseURI = "http://216.10.245.166";
		given()
		.log().all()
			.header("Content-Type", "application/json")
			.body(AddbookPayload.AddBookPayLoadText())
			
		.when()
		.log().all()
			.post("/Library/Addbook.php")
			
		.then()
			.log().all()
				.assertThat().statusCode(200)
				.extract().response().asString();
		
		JsonPath js= new JsonPath(addBookResponse);
		String addedBookID = js.getString("ID");
		System.out.println(addedBookID);
	}

}

