package example3;

//³×ÀÌ¹ö °Ë»ö API ¿¹Á¦ - blog °Ë»ö
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Start {

	public static void main(String[] args) {
		String clientId = "CERXzODq_Ptd1HkbBZ7w"; //¾ÖÇÃ¸®ÄÉÀÌ¼Ç Å¬¶óÀÌ¾ğÆ® ¾ÆÀÌµğ°ª"
		String clientSecret = "3BBPjQV_y0"; //¾ÖÇÃ¸®ÄÉÀÌ¼Ç Å¬¶óÀÌ¾ğÆ® ½ÃÅ©¸´°ª"

		String text = null;
		try {
			text = URLEncoder.encode("±×¸°ÆÑÅä¸®", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("°Ë»ö¾î ÀÎÄÚµù ½ÇÆĞ",e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text;    // json °á°ú
		//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml °á°ú

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL,requestHeaders);

		System.out.println(responseBody);
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders){
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // Á¤»ó È£Ãâ
				return readBody(con.getInputStream());
			} else { // ¿¡·¯ ¹ß»ı
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API ¿äÃ»°ú ÀÀ´ä ½ÇÆĞ", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl){
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URLÀÌ Àß¸øµÇ¾ú½À´Ï´Ù. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("¿¬°áÀÌ ½ÇÆĞÇß½À´Ï´Ù. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body){
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API ÀÀ´äÀ» ÀĞ´Âµ¥ ½ÇÆĞÇß½À´Ï´Ù.", e);
		}
	}
}


/*
{
	"lastBuildDate": "Thu, 25 Feb 2021 15:13:39 +0900",
	"total": 190,
	"start": 1,
	"display": 10,
	"items": [
	          {
	        	  "title": "NHN?´ ?¼?•˜?Š” 27ì¸? ë¹Œë”© <b>ê·¸ë¦°?Œ©?† ë¦?</b> ?””??¸ë¶?",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6448365",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/064/483/06448365.jpg?type=m1&udate=20120111",
	        	  "author": "NHN",
	        	  "price": "21000",
	        	  "discount": "",
	        	  "publisher": "?‹œ?“œ?˜?´?¼",
	        	  "pubdate": "20110107",
	        	  "isbn": "8993976295 9788993976298",
	        	  "description": "NHN?˜ ê°?ì¹˜ë?? ?‹´?? ë¶„ë‹¹ ?‹ ?‚¬?˜¥ '<b>ê·¸ë¦°?Œ©?† ë¦?</b>'?˜ ê±´ì„¤ ?ˆ?Š¤?† ë¦¬ë¶. NHN?? ê±´ì¶•ë¬¼ì˜ ?ˆ˜ë¦? ëª©í‘œ?? ê³¼ì • ê·¸ë¦¬ê³? ê²°ê³¼ë¥? ?´?•¼ê¸°ë¡œ ?‹´?•„ ê¸°ì—…?˜ ê°?ì¹˜ë?? ??ì¤‘ì—ê²? ? „?‹¬?•˜ê³?, ?”„ë¡œì ?Š¸?˜ ì§„í–‰ë°©ë²•?´?‚˜ ?‹œê³µê¸°?ˆ  ë°? ?ë£Œë?? ê³µìœ ?•˜?Š” ê¸°ë¡?œ¼ë¡œì„œ?˜ ?˜ë¯¸ê¹Œì§? ?ƒˆê²¼ë‹¤. 3?‡„?—?„œ?Š” <b>ê·¸ë¦°?Œ©?† ë¦?</b>?—?„œ 1.5?…„ê°? ?‚´?•„?˜¨ NHN ì§ì›?“¤?˜... "
	          },
	          {
	        	  "title": "4ì°? ?‚°?—…?˜ëª? ì£¼ìš” ?•µ?‹¬ê¸°ìˆ  ?™?–¥ê³? ?Š¤ë§ˆíŠ¸?‹œ?‹° <b>?Œ©?† ë¦?</b> ?‚°?—… ?‹¤?ƒœë¶„ì„",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=12270484",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/122/704/12270484.jpg?type=m1&udate=20170721",
	        	  "author": "R&D? •ë³´ì„¼?„°",
	        	  "price": "350000",
	        	  "discount": "",
	        	  "publisher": "ì§??‹?‚°?—…? •ë³´ì›",
	        	  "pubdate": "20170719",
	        	  "isbn": "115862056X 9791158620561",
	        	  "description": "<b>?Œ©?† ë¦?</b> ë¶„ì•¼?˜ êµ??‚´?™¸ ìµœì‹  ?™?–¥?„ ë¶„ì„?•˜ê³? ê¸?ë¡œë²Œ ?‹œ?¥ ?„ ? ?„ ?œ„?•œ ê²½ìŸ? ¥ ê°•í™”?— ?„???´ ?˜ê³ ì ê´?? ¨ê¸°ê??“¤?˜ ë¶„ì„ ? •ë³´ìë£Œë?? ? •ë¦¬í•˜?—¬ ??4ì°? ?‚°?—…?˜ëª? ì£¼ìš” ?•µ?‹¬ê¸°ìˆ  ?™?–¥ê³? ?Š¤ë§ˆíŠ¸?‹œ?‹° <b>?Œ©?† ë¦?</b>?‚°?—…... ?‚°?—…?˜ëª…ì˜ ?•µ?‹¬?¸ ?Š¤ë§ˆíŠ¸?‹œ?‹°?? ?Š¤ë§ˆíŠ¸ <b>?Œ©?† ë¦?</b>?˜ êµ??‚´?™¸ ì¶”ì§„ ?˜„?™©ê³? ì£¼ìš” ?‚°?—… ?™?–¥?„ ?ˆ˜ë¡í•˜???‹¤."
	          },
	          {
	        	  "title": "Sewing Factory ?†Œ?‰ <b>?Œ©?† ë¦?</b> (ë°˜ë…„ê°?) : ê°??„ê²¨ìš¸?˜¸ [2011?…„]",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6725369",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/067/253/06725369.jpg?type=m1&udate=20110830",
	        	  "author": "",
	        	  "price": "9800",
	        	  "discount": "",
	        	  "publisher": "?„œ?š¸ë¬¸í™”?‚¬",
	        	  "pubdate": "20110901",
	        	  "isbn": "",
	        	  "description": "ë¶?ë¡? : 1. ?„?Š¸?Œ¨?„´ ?¼ë¯¸ë„¤?Œ… ?›?‹¨ DIYê°?ë°?(ë°˜ì œ?’ˆ)          2. ?˜·, ëª¨ì, ?ŒŒ?š°ì¹? ?“± ?•¸?“œë©”ì´?“œ ?‘?’ˆ ?‹¤ë¬¼ë³¸ 11ê°?(ì±…ì†"
	          },
	          {
	        	  "title": "?•˜ëª¨ë‹ˆ ?‹¤?¬ <b>?Œ©?† ë¦?</b>",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6060216",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/060/602/06060216.jpg?type=m1&udate=20120729",
	        	  "author": "???‹œ ?˜¤",
	        	  "price": "12000",
	        	  "discount": "",
	        	  "publisher": "?‘ê°?? •?‹ ",
	        	  "pubdate": "20090830",
	        	  "isbn": "8972883522 9788972883524",
	        	  "description": "ê¸°ì–µê³? ì§„ì‹¤?— ê°?? ¤ì§? ?•œ ?‚¨??˜ ì§„ì§œ ?´?•¼ê¸?!?•œ ì¤‘êµ­?¸ ì§ë¬¼ ?ƒ?¸?˜ ?‚¶?„ <b>ê·¸ë¦°</b> ?†Œ?„¤?í•˜ëª¨ë‹ˆ ?‹¤?¬ <b>?Œ©?† ë¦?</b>??. ?˜êµ??—?„œ ì£¼ëª©ë°›ê³  ?ˆ?Š” ?‹ ?˜ˆ ???‹œ ?˜¤?˜ ì²? ?¥?¸?†Œ?„¤ë¡?, 2005?…„ ?œ˜?Š¸ë¸Œë ˆ?“œ ?ƒê³? ì½”ë¨¼?›°?Š¤ ?ƒ?„ ?ˆ˜?ƒ?•˜ê³? ë§¨ë?ì»? ?ƒ ìµœì¢… ?›„ë³´ì— ?˜¬??‹¤. 20?„¸ê¸? ? „ë°˜ì˜ ë¶ˆì•ˆ?•œ ë§ë ˆ?´?‹œ?•„ë¥? ë°°ê²½?œ¼ë¡?, ì¤‘êµ­ ?´ë¯¼ì 2?„¸... "
	          },
	          {
	        	  "title": "?Š¤ë§ˆíŠ¸<b>?Œ©?† ë¦?</b> êµ¬ì¶• R&D? „?µ ë°? ?‚°?—…?‹¤?ƒœë¶„ì„ (?‚¬ë¬¼ì¸?„°?„·/?´?¼?š°?“œ ì»´í“¨?Œ…/ë¹…ë°?´?„°)",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=10016685",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/100/166/10016685.jpg?type=m1&udate=20191227",
	        	  "author": "R&D? •ë³´ì„¼?„°",
	        	  "price": "330000",
	        	  "discount": "",
	        	  "publisher": "ì§??‹?‚°?—…? •ë³´ì›",
	        	  "pubdate": "20151230",
	        	  "isbn": "115862011X 9791158620110",
	        	  "description": "?ìŠ¤ë§ˆíŠ¸<b>?Œ©?† ë¦?</b> êµ¬ì¶• R&D? „?µ ë°? ?‚°?—…?‹¤?ƒœë¶„ì„?ì? ?Š¤ë§ˆíŠ¸<b>?Œ©?† ë¦?</b> ?‚°?—… ë¶„ì•¼ ?›ì²œê¸°?ˆ  ê°œë°œ ?™•ë³´ë?? ?†µ?•œ ê²½ìŸ? ¥ ê°•í™”?— ?„???´ ?˜ê³ ì ê´?? ¨ê¸°ê??“¤?˜ ? •ë³´ë?? ë¶„ì„ ? •ë¦¬í•œ ì±…ì´?‹¤. ?Š¤ë§ˆíŠ¸<b>?Œ©?† ë¦?</b> ?‚°?—…?—?„œ ?¬ê²? ì£¼ëª©ë°›ê³  ?ˆ?Š” ?‚¬ë¬¼ì¸?„°?„·/?´?¼?š°?“œ ì»´í“¨?Œ…/ë¹…ë°?´?„° ?‚°?—…?˜ ê¸°ìˆ  ë°? ?‹œ?¥? „ë§ê³¼ ?™œ?š©?‚¬ë¡?... "
	          },
	          {
	        	  "title": "?‚´ê°? <b>ê·¸ë¦°</b> ë°±ë§Œ ?†Œ?™˜?ˆ˜ 12 (?™¸? „)",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547598",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547598.jpg?type=m1&udate=20201203",
	        	  "author": "?š°ë¦?",
	        	  "price": "2000",
	        	  "discount": "",
	        	  "publisher": "ê³ ë ˜<b>?Œ©?† ë¦?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164498940 9791164498949",
	        	  "description": "?‚´ê°? <b>ê·¸ë¦°</b> ê·¸ë¦¼?“¤?´ ?†Œ?™˜?ˆ˜ê°? ?œ?‹¤ë©?? \n?•„ë¬´ê²ƒ?„ ?—†?Š” ì§ê¾¼?—?„œ ë°±ë§Œ ?†Œ?™˜?ˆ˜?“¤?˜ ì£¼ì¸?´ ?˜ê¸°ê¹Œì§?. \nê·¸ë¦¼ë§? ê·¸ë¦¬ë©? ëª¨ë‘ ?†Œ?™˜ ê°??Š¥?•œ ?Š¥? ¥??˜ ?´?•¼ê¸?.\n?ƒ?ƒ?•˜?Š” ê²ƒë“¤?? ëª¨ë‘ ?†Œ?™˜?´ ê°??Š¥?•˜?‹¤."
	          },
	          {
	        	  "title": "?‚´ê°? <b>ê·¸ë¦°</b> ë°±ë§Œ ?†Œ?™˜?ˆ˜ 11",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547597",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547597.jpg?type=m1&udate=20201203",
	        	  "author": "?š°ë¦?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "ê³ ë ˜<b>?Œ©?† ë¦?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164495860 9791164495863",
	        	  "description": "?‚´ê°? <b>ê·¸ë¦°</b> ê·¸ë¦¼?“¤?´ ?†Œ?™˜?ˆ˜ê°? ?œ?‹¤ë©?? \n?•„ë¬´ê²ƒ?„ ?—†?Š” ì§ê¾¼?—?„œ ë°±ë§Œ ?†Œ?™˜?ˆ˜?“¤?˜ ì£¼ì¸?´ ?˜ê¸°ê¹Œì§?. \nê·¸ë¦¼ë§? ê·¸ë¦¬ë©? ëª¨ë‘ ?†Œ?™˜ ê°??Š¥?•œ ?Š¥? ¥??˜ ?´?•¼ê¸?.\n?ƒ?ƒ?•˜?Š” ê²ƒë“¤?? ëª¨ë‘ ?†Œ?™˜?´ ê°??Š¥?•˜?‹¤."
	          },
	          {
	        	  "title": "?‚´ê°? <b>ê·¸ë¦°</b> ë°±ë§Œ ?†Œ?™˜?ˆ˜ 2",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547599",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547599.jpg?type=m1&udate=20201203",
	        	  "author": "?š°ë¦?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "ê³ ë ˜<b>?Œ©?† ë¦?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164490761 9791164490769",
	        	  "description": "?‚´ê°? <b>ê·¸ë¦°</b> ê·¸ë¦¼?“¤?´ ?†Œ?™˜?ˆ˜ê°? ?œ?‹¤ë©?? \n?•„ë¬´ê²ƒ?„ ?—†?Š” ì§ê¾¼?—?„œ ë°±ë§Œ ?†Œ?™˜?ˆ˜?“¤?˜ ì£¼ì¸?´ ?˜ê¸°ê¹Œì§?. \nê·¸ë¦¼ë§? ê·¸ë¦¬ë©? ëª¨ë‘ ?†Œ?™˜ ê°??Š¥?•œ ?Š¥? ¥??˜ ?´?•¼ê¸?.\n?ƒ?ƒ?•˜?Š” ê²ƒë“¤?? ëª¨ë‘ ?†Œ?™˜?´ ê°??Š¥?•˜?‹¤."
	          },
	          {
	        	  "title": "?‚´ê°? <b>ê·¸ë¦°</b> ë°±ë§Œ ?†Œ?™˜?ˆ˜ 1",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547595",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547595.jpg?type=m1&udate=20201203",
	        	  "author": "?š°ë¦?",
	        	  "price": "0",
	        	  "discount": "",
	        	  "publisher": "ê³ ë ˜<b>?Œ©?† ë¦?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164490656 9791164490653",
	        	  "description": "?‚´ê°? <b>ê·¸ë¦°</b> ê·¸ë¦¼?“¤?´ ?†Œ?™˜?ˆ˜ê°? ?œ?‹¤ë©?? \n?•„ë¬´ê²ƒ?„ ?—†?Š” ì§ê¾¼?—?„œ ë°±ë§Œ ?†Œ?™˜?ˆ˜?“¤?˜ ì£¼ì¸?´ ?˜ê¸°ê¹Œì§?. \nê·¸ë¦¼ë§? ê·¸ë¦¬ë©? ëª¨ë‘ ?†Œ?™˜ ê°??Š¥?•œ ?Š¥? ¥??˜ ?´?•¼ê¸?.\n?ƒ?ƒ?•˜?Š” ê²ƒë“¤?? ëª¨ë‘ ?†Œ?™˜?´ ê°??Š¥?•˜?‹¤."
	          },
	          {
	        	  "title": "?‚´ê°? <b>ê·¸ë¦°</b> ë°±ë§Œ ?†Œ?™˜?ˆ˜ 10",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547596",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547596.jpg?type=m1&udate=20201203",
	        	  "author": "?š°ë¦?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "ê³ ë ˜<b>?Œ©?† ë¦?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164494996 9791164494996",
	        	  "description": "?‚´ê°? <b>ê·¸ë¦°</b> ê·¸ë¦¼?“¤?´ ?†Œ?™˜?ˆ˜ê°? ?œ?‹¤ë©?? \n?•„ë¬´ê²ƒ?„ ?—†?Š” ì§ê¾¼?—?„œ ë°±ë§Œ ?†Œ?™˜?ˆ˜?“¤?˜ ì£¼ì¸?´ ?˜ê¸°ê¹Œì§?. \nê·¸ë¦¼ë§? ê·¸ë¦¬ë©? ëª¨ë‘ ?†Œ?™˜ ê°??Š¥?•œ ?Š¥? ¥??˜ ?´?•¼ê¸?.\n?ƒ?ƒ?•˜?Š” ê²ƒë“¤?? ëª¨ë‘ ?†Œ?™˜?´ ê°??Š¥?•˜?‹¤."
	          }
	          ]
	        		  
}
*/
