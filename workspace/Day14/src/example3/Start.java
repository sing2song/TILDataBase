package example3;

//네이버 검색 API 예제 - blog 검색
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Start {

	public static void main(String[] args) {
		String clientId = "CERXzODq_Ptd1HkbBZ7w"; //애플리케이션 클라이언트 아이디값"
		String clientSecret = "3BBPjQV_y0"; //애플리케이션 클라이언트 시크릿값"

		String text = null;
		try {
			text = URLEncoder.encode("그린팩토리", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text;    // json 결과
		//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

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
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl){
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
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
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
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
	        	  "title": "NHN?씠 ?씪?븯?뒗 27痢? 鍮뚮뵫 <b>洹몃┛?뙥?넗由?</b> ?뵒?옄?씤遺?",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6448365",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/064/483/06448365.jpg?type=m1&udate=20120111",
	        	  "author": "NHN",
	        	  "price": "21000",
	        	  "discount": "",
	        	  "publisher": "?떆?뱶?럹?씠?띁",
	        	  "pubdate": "20110107",
	        	  "isbn": "8993976295 9788993976298",
	        	  "description": "NHN?쓽 媛?移섎?? ?떞?? 遺꾨떦 ?떊?궗?삦 '<b>洹몃┛?뙥?넗由?</b>'?쓽 嫄댁꽕 ?엳?뒪?넗由щ턿. NHN?? 嫄댁텞臾쇱쓽 ?닔由? 紐⑺몴?? 怨쇱젙 洹몃━怨? 寃곌낵瑜? ?씠?빞湲곕줈 ?떞?븘 湲곗뾽?쓽 媛?移섎?? ??以묒뿉寃? ?쟾?떖?븯怨?, ?봽濡쒖젥?듃?쓽 吏꾪뻾諛⑸쾿?씠?굹 ?떆怨듦린?닠 諛? ?옄猷뚮?? 怨듭쑀?븯?뒗 湲곕줉?쑝濡쒖꽌?쓽 ?쓽誘멸퉴吏? ?깉寃쇰떎. 3?뇙?뿉?꽌?뒗 <b>洹몃┛?뙥?넗由?</b>?뿉?꽌 1.5?뀈媛? ?궡?븘?삩 NHN 吏곸썝?뱾?쓽... "
	          },
	          {
	        	  "title": "4李? ?궛?뾽?쁺紐? 二쇱슂 ?빑?떖湲곗닠 ?룞?뼢怨? ?뒪留덊듃?떆?떚 <b>?뙥?넗由?</b> ?궛?뾽 ?떎?깭遺꾩꽍",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=12270484",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/122/704/12270484.jpg?type=m1&udate=20170721",
	        	  "author": "R&D?젙蹂댁꽱?꽣",
	        	  "price": "350000",
	        	  "discount": "",
	        	  "publisher": "吏??떇?궛?뾽?젙蹂댁썝",
	        	  "pubdate": "20170719",
	        	  "isbn": "115862056X 9791158620561",
	        	  "description": "<b>?뙥?넗由?</b> 遺꾩빞?쓽 援??궡?쇅 理쒖떊 ?룞?뼢?쓣 遺꾩꽍?븯怨? 湲?濡쒕쾶 ?떆?옣 ?꽑?젏?쓣 ?쐞?븳 寃쎌웳?젰 媛뺥솕?뿉 ?룄???씠 ?릺怨좎옄 愿??젴湲곌??뱾?쓽 遺꾩꽍 ?젙蹂댁옄猷뚮?? ?젙由ы븯?뿬 ??4李? ?궛?뾽?쁺紐? 二쇱슂 ?빑?떖湲곗닠 ?룞?뼢怨? ?뒪留덊듃?떆?떚 <b>?뙥?넗由?</b>?궛?뾽... ?궛?뾽?쁺紐낆쓽 ?빑?떖?씤 ?뒪留덊듃?떆?떚?? ?뒪留덊듃 <b>?뙥?넗由?</b>?쓽 援??궡?쇅 異붿쭊 ?쁽?솴怨? 二쇱슂 ?궛?뾽 ?룞?뼢?쓣 ?닔濡앺븯???떎."
	          },
	          {
	        	  "title": "Sewing Factory ?냼?엵 <b>?뙥?넗由?</b> (諛섎뀈媛?) : 媛??쓣寃⑥슱?샇 [2011?뀈]",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6725369",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/067/253/06725369.jpg?type=m1&udate=20110830",
	        	  "author": "",
	        	  "price": "9800",
	        	  "discount": "",
	        	  "publisher": "?꽌?슱臾명솕?궗",
	        	  "pubdate": "20110901",
	        	  "isbn": "",
	        	  "description": "遺?濡? : 1. ?룄?듃?뙣?꽩 ?씪誘몃꽕?똿 ?썝?떒 DIY媛?諛?(諛섏젣?뭹)          2. ?샆, 紐⑥옄, ?뙆?슦移? ?벑 ?빖?뱶硫붿씠?뱶 ?옉?뭹 ?떎臾쇰낯 11媛?(梨낆냽"
	          },
	          {
	        	  "title": "?븯紐⑤땲 ?떎?겕 <b>?뙥?넗由?</b>",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6060216",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/060/602/06060216.jpg?type=m1&udate=20120729",
	        	  "author": "???떆 ?삤",
	        	  "price": "12000",
	        	  "discount": "",
	        	  "publisher": "?옉媛??젙?떊",
	        	  "pubdate": "20090830",
	        	  "isbn": "8972883522 9788972883524",
	        	  "description": "湲곗뼲怨? 吏꾩떎?뿉 媛??젮吏? ?븳 ?궓?옄?쓽 吏꾩쭨 ?씠?빞湲?!?븳 以묎뎅?씤 吏곷Ъ ?긽?씤?쓽 ?궣?쓣 <b>洹몃┛</b> ?냼?꽕?롰븯紐⑤땲 ?떎?겕 <b>?뙥?넗由?</b>??. ?쁺援??뿉?꽌 二쇰ぉ諛쏄퀬 ?엳?뒗 ?떊?삁 ???떆 ?삤?쓽 泥? ?옣?렪?냼?꽕濡?, 2005?뀈 ?쐶?듃釉뚮젅?뱶 ?긽怨? 肄붾㉫?쎇?뒪 ?긽?쓣 ?닔?긽?븯怨? 留⑤?而? ?긽 理쒖쥌 ?썑蹂댁뿉 ?삱?옄?떎. 20?꽭湲? ?쟾諛섏쓽 遺덉븞?븳 留먮젅?씠?떆?븘瑜? 諛곌꼍?쑝濡?, 以묎뎅 ?씠誘쇱옄 2?꽭... "
	          },
	          {
	        	  "title": "?뒪留덊듃<b>?뙥?넗由?</b> 援ъ텞 R&D?쟾?왂 諛? ?궛?뾽?떎?깭遺꾩꽍 (?궗臾쇱씤?꽣?꽬/?겢?씪?슦?뱶 而댄벂?똿/鍮낅뜲?씠?꽣)",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=10016685",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/100/166/10016685.jpg?type=m1&udate=20191227",
	        	  "author": "R&D?젙蹂댁꽱?꽣",
	        	  "price": "330000",
	        	  "discount": "",
	        	  "publisher": "吏??떇?궛?뾽?젙蹂댁썝",
	        	  "pubdate": "20151230",
	        	  "isbn": "115862011X 9791158620110",
	        	  "description": "?롮뒪留덊듃<b>?뙥?넗由?</b> 援ъ텞 R&D?쟾?왂 諛? ?궛?뾽?떎?깭遺꾩꽍?륁? ?뒪留덊듃<b>?뙥?넗由?</b> ?궛?뾽 遺꾩빞 ?썝泥쒓린?닠 媛쒕컻 ?솗蹂대?? ?넻?븳 寃쎌웳?젰 媛뺥솕?뿉 ?룄???씠 ?릺怨좎옄 愿??젴湲곌??뱾?쓽 ?젙蹂대?? 遺꾩꽍 ?젙由ы븳 梨낆씠?떎. ?뒪留덊듃<b>?뙥?넗由?</b> ?궛?뾽?뿉?꽌 ?겕寃? 二쇰ぉ諛쏄퀬 ?엳?뒗 ?궗臾쇱씤?꽣?꽬/?겢?씪?슦?뱶 而댄벂?똿/鍮낅뜲?씠?꽣 ?궛?뾽?쓽 湲곗닠 諛? ?떆?옣?쟾留앷낵 ?솢?슜?궗濡?... "
	          },
	          {
	        	  "title": "?궡媛? <b>洹몃┛</b> 諛깅쭔 ?냼?솚?닔 12 (?쇅?쟾)",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547598",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547598.jpg?type=m1&udate=20201203",
	        	  "author": "?슦由?",
	        	  "price": "2000",
	        	  "discount": "",
	        	  "publisher": "怨좊젞<b>?뙥?넗由?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164498940 9791164498949",
	        	  "description": "?궡媛? <b>洹몃┛</b> 洹몃┝?뱾?씠 ?냼?솚?닔媛? ?맂?떎硫?? \n?븘臾닿쾬?룄 ?뾾?뒗 吏먭씔?뿉?꽌 諛깅쭔 ?냼?솚?닔?뱾?쓽 二쇱씤?씠 ?릺湲곌퉴吏?. \n洹몃┝留? 洹몃━硫? 紐⑤몢 ?냼?솚 媛??뒫?븳 ?뒫?젰?옄?쓽 ?씠?빞湲?.\n?긽?긽?븯?뒗 寃껊뱾?? 紐⑤몢 ?냼?솚?씠 媛??뒫?븯?떎."
	          },
	          {
	        	  "title": "?궡媛? <b>洹몃┛</b> 諛깅쭔 ?냼?솚?닔 11",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547597",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547597.jpg?type=m1&udate=20201203",
	        	  "author": "?슦由?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "怨좊젞<b>?뙥?넗由?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164495860 9791164495863",
	        	  "description": "?궡媛? <b>洹몃┛</b> 洹몃┝?뱾?씠 ?냼?솚?닔媛? ?맂?떎硫?? \n?븘臾닿쾬?룄 ?뾾?뒗 吏먭씔?뿉?꽌 諛깅쭔 ?냼?솚?닔?뱾?쓽 二쇱씤?씠 ?릺湲곌퉴吏?. \n洹몃┝留? 洹몃━硫? 紐⑤몢 ?냼?솚 媛??뒫?븳 ?뒫?젰?옄?쓽 ?씠?빞湲?.\n?긽?긽?븯?뒗 寃껊뱾?? 紐⑤몢 ?냼?솚?씠 媛??뒫?븯?떎."
	          },
	          {
	        	  "title": "?궡媛? <b>洹몃┛</b> 諛깅쭔 ?냼?솚?닔 2",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547599",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547599.jpg?type=m1&udate=20201203",
	        	  "author": "?슦由?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "怨좊젞<b>?뙥?넗由?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164490761 9791164490769",
	        	  "description": "?궡媛? <b>洹몃┛</b> 洹몃┝?뱾?씠 ?냼?솚?닔媛? ?맂?떎硫?? \n?븘臾닿쾬?룄 ?뾾?뒗 吏먭씔?뿉?꽌 諛깅쭔 ?냼?솚?닔?뱾?쓽 二쇱씤?씠 ?릺湲곌퉴吏?. \n洹몃┝留? 洹몃━硫? 紐⑤몢 ?냼?솚 媛??뒫?븳 ?뒫?젰?옄?쓽 ?씠?빞湲?.\n?긽?긽?븯?뒗 寃껊뱾?? 紐⑤몢 ?냼?솚?씠 媛??뒫?븯?떎."
	          },
	          {
	        	  "title": "?궡媛? <b>洹몃┛</b> 諛깅쭔 ?냼?솚?닔 1",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547595",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547595.jpg?type=m1&udate=20201203",
	        	  "author": "?슦由?",
	        	  "price": "0",
	        	  "discount": "",
	        	  "publisher": "怨좊젞<b>?뙥?넗由?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164490656 9791164490653",
	        	  "description": "?궡媛? <b>洹몃┛</b> 洹몃┝?뱾?씠 ?냼?솚?닔媛? ?맂?떎硫?? \n?븘臾닿쾬?룄 ?뾾?뒗 吏먭씔?뿉?꽌 諛깅쭔 ?냼?솚?닔?뱾?쓽 二쇱씤?씠 ?릺湲곌퉴吏?. \n洹몃┝留? 洹몃━硫? 紐⑤몢 ?냼?솚 媛??뒫?븳 ?뒫?젰?옄?쓽 ?씠?빞湲?.\n?긽?긽?븯?뒗 寃껊뱾?? 紐⑤몢 ?냼?솚?씠 媛??뒫?븯?떎."
	          },
	          {
	        	  "title": "?궡媛? <b>洹몃┛</b> 諛깅쭔 ?냼?솚?닔 10",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547596",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547596.jpg?type=m1&udate=20201203",
	        	  "author": "?슦由?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "怨좊젞<b>?뙥?넗由?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164494996 9791164494996",
	        	  "description": "?궡媛? <b>洹몃┛</b> 洹몃┝?뱾?씠 ?냼?솚?닔媛? ?맂?떎硫?? \n?븘臾닿쾬?룄 ?뾾?뒗 吏먭씔?뿉?꽌 諛깅쭔 ?냼?솚?닔?뱾?쓽 二쇱씤?씠 ?릺湲곌퉴吏?. \n洹몃┝留? 洹몃━硫? 紐⑤몢 ?냼?솚 媛??뒫?븳 ?뒫?젰?옄?쓽 ?씠?빞湲?.\n?긽?긽?븯?뒗 寃껊뱾?? 紐⑤몢 ?냼?솚?씠 媛??뒫?븯?떎."
	          }
	          ]
	        		  
}
*/
