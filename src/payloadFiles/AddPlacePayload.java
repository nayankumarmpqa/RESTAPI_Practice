package payloadFiles;

public class AddPlacePayload {

	
	public static String AddPlacePayLoad() {
		
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -87.383494,\r\n" + 
				"    \"lng\": 87.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Nayan house\",\r\n" + 
				"  \"phone_number\": \"(+91) 86997260\",\r\n" + 
				"  \"address\": \"29, Model town, NY\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://myaddress.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}";
	}
}
