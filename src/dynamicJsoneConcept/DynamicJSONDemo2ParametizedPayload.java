package dynamicJsoneConcept;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;
import payloadFiles.AddbookPayload;;

public class DynamicJSONDemo2ParametizedPayload {

	@Test(dataProvider = "getBookData")
	public void addBook(String isbn, String aisle) {

		// public static void main(String[] rgs) {

		RestAssured.baseURI = "http://216.10.245.166";
		String addBookResponse = given()
				.log()
				.all()
					.header("Content-Type", "application/json")
				// .body(AddbookPayload.AddBookPayLoadText()) //payload without params
				.body(AddbookPayload.AddBookPayLoadText(isbn, aisle))
				// above payload with 2 string params that will update the AddBookPayLoadText()
				// method with the params
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
	}

	@DataProvider(name = "getBookData")

	public Object[][] getBookData() {

		return new Object[][] { { "nayanisbn-1", "nkaisle-1" }, { "nayanisbn-2", "nkaisle-2" } };

	}

}
