package dynamicJsoneConcept;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import payloadFiles.AddbookPayload;;

public class StaticJsonPayloadFileDemo {

	
	@Test
	public void addBook() throws IOException {

		// public static void main(String[] rgs) {

		RestAssured.baseURI = "http://216.10.245.166";
		String addBookResponse = given()
				.log()
				.all()
					.header("Content-Type", "application/json")
				.body(GenerateStringFromResource("C:\\Users\\nkumar\\Desktop\\AddbookDetails.json")) 
				//above payload without GenerateStringFromResource
				.when()
					.log()
					.all()	
					.post("/Library/Addbook.php")

				.then()
				// .log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath jsr = new JsonPath(addBookResponse);
		String addedBookID = jsr.getString("ID");
		System.out.println("Added book ID from the API response is = " + addedBookID);
		
		
		
	
		//============================Delete added book code below====================
		
		given()
		.log()
		.all()
		.header("Content-Type", "application/json")
		.body("{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+addedBookID+"\"\r\n" + 
				" \r\n" + 
				"}") 
		//above payload without GenerateStringFromResource
		
		.when()
			.log()
			.all()	
			.post("/Library/DeleteBook.php")

		.then()
		.log().all()
		.assertThat().statusCode(200).extract().response().asString();

		}
	

		
	//This method is to get and static eternal jason file into the program
	public static String GenerateStringFromResource(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	

	
	
}

