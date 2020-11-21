package Pack1;

import io.restassured.RestAssured;
import payloadFiles.DummyResponse;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Basic2 {

	public static void main(String[] rgs) {

		// Get API
		/*
		 * String getPlaceresponse =
		 * 
		 * given() .log().all() .queryParam("key", "qaclick123") .queryParam("place_id",
		 * placeId)
		 * 
		 * .when() .get("/maps/api/place/get/json")
		 * 
		 * .then() .log().all()
		 * .assertThat().statusCode(200).extract().response().asString();
		 */
		// Using JsonPath object to parse the string and getting the attribute named
		// address value.

		JsonPath js1 = new JsonPath(DummyResponse.DummyResponseText());
		int count = js1.getInt("courses.size()"); // getting number of courses in the response
		System.out.println(count);

		// Print purchase amount.
		int p = js1.getInt("dashboard.purchaseAmount");
		System.out.println(p);

		// Print course titles and price.

		String CTitle1 = js1.get("courses[0].title");
		System.out.println(CTitle1);

		for (int i = 0; i < count; i++) {

			String CTs = js1.get("courses[" + i + "].title");

			System.out.println(CTs);
			System.out.println(js1.get("courses[" + i + "].price").toString());

		}

		// Print RPA course titles and price and no of copies sold by RPA.

		System.out.println("// Print RPA course titles and price and no of copies sold by RPA.");

		for (int i = 0; i < count; i++) {
			String CTs = js1.get("courses[" + i + "].title");

			if (CTs.equalsIgnoreCase("RPA")) {
				int CopyCount = js1.get("courses[" + i + "].copies");
				System.out.println(CTs);

				System.out.println(js1.get("courses[" + i + "].price").toString());
				System.out.println(CopyCount);
				break;
			}

		}

		System.out.println("// Print sum of courses and Validate.");
		int total = 0;
		for (int i = 0; i < count; i++) {
			int Cprice = js1.getInt("courses[" + i + "].price");
			int Ccopies = js1.getInt("courses[" + i + "].copies");

			int amount = Cprice * Ccopies;

			System.out.println(amount);
			total = total + amount;
		}
		System.out.println("Calculated total is = " + total);

		int givenPurchaseAmount = js1.getInt("dashboard.purchaseAmount");

		// Assert.assertEquals(total, givenPurchaseAmount);
		// OR
		
		if(total==givenPurchaseAmount) {
			System.out.println("total matched!!!");
		}

	}
}
