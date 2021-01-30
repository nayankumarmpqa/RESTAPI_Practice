package payloadFiles;

public class AddbookPayload {
	
	
	public static String AddBookPayLoadText(String isbn, String aisle) {
		/*String AddBookPayLoadString = "{\r\n" + 
				"\"name\":\"Learn RESTAPI Automation with Java\",\r\n" + 
				"\"isbn\":\"nk12\",\r\n" + 
				"\"aisle\":\"227\",\r\n" + 
				"\"author\":\"Nayan Kumar\"\r\n" + 
				"}";*/
	//	return AddBookPayLoadString ;
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
	}

}
