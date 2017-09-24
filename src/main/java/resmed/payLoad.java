package resmed;

public class payLoad {

	
	public static String getPostData()
	{
	
		String b ="{"+
				  "\"location\": {"+
				    "\"lat\": -33.8669710,"+
				    "\"lng\": 151.1958750"+
				  "},"+
				  "\"accuracy\": 50,"+
				  "\"name\": \"Test Post\","+
				  "\"phone_number\": \"(02) 9374 4000\","+
				  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				  "\"types\": [\"store\"],"+
				  "\"website\": \"http://www.google.com.au/\","+
				  "\"language\": \"en-AU\""+
				"}";
		
		
		return b;
	}
}
