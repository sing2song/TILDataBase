package example2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
{
	"message":
	{
		"@type":"response",
		"@service":"naverservice.nmt.proxy",
		"@version":"1.0.0",
		"result":
		{
			"srcLangType":"ko",
			"tarLangType":"en",
			"translatedText":"Hello. How are you doing today?",
			"engineType":"N2MT",
			"pivot":null
		}
	}
}  
*/
public class Parser {
	public static String Parsing(String jsonData) {
		try {
			JSONParser jsonParse = new JSONParser();
		
			//JSONParse에 json데이터를 넣어 파싱한 다음 JSONObject로 변환한다. 
			JSONObject jsonObj = (JSONObject) jsonParse.parse(jsonData); 
		

			JSONObject message = (JSONObject)jsonObj.get("message");
			
			JSONObject result = (JSONObject)message.get("result");
			
			String retmessage = (String)result.get("translatedText");
			
			return retmessage;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return "";
		}		
	}	
}
