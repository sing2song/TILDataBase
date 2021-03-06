package sample3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

//clientID : CERXzODq_Ptd1HkbBZ7w
//key : 3BBPjQV_y0
public class Start {
	public static void main(String[] args) {
        String clientId = "CERXzODq_Ptd1HkbBZ7w"; 	//애플리케이션 클라이언트 아이디값"
        String clientSecret = "3BBPjQV_y0"; 		//애플리케이션 클라이언트 시크릿값"

        String text = null;
        try {
            text = URLEncoder.encode("Java", "UTF-8");
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
	"lastBuildDate": "Thu, 25 Feb 2021 11:22:47 +0900",
	"total": 14381,
	"start": 1,
	"display": 10,
	"items": [
		{
			"title": "<b>Java</b>?쓽 ?젙?꽍 湲곗큹?렪",
			"link": "http://book.naver.com/bookdb/book_detail.php?bid=16031391",
			"image": "https://bookthumb-phinf.pstatic.net/cover/160/313/16031391.jpg?type=m1&udate=20200108",
			"author": "?궓沅곸꽦",
			"price": "25000",
			"discount": "22500",
			"publisher": "?룄?슦異쒗뙋",
			"pubdate": "20191230",
			"isbn": "8994492046 9788994492049",
			"description": "援먯쑁?쁽?옣?뿉?꽌 ?뮘爾먯??뒗 ?븰?깮?뱾?쓣 ?쐞?빐 ?벐怨?, ?븰?깮?뱾?뿉寃? 吏곸젒 寃?利앸컺怨? ?샇?룊諛쏆? 梨?. 肄붾뵫?쓣 泥섏쓬 諛곗슦?뒗 ?궗?엺?룄 ?옄諛붾?? ?돺寃? 諛곗슱?닔 ?엳寃? ?룄??以??떎."
		},
		{
			"title": "?씠?럺?떚釉? ?옄諛? (Effective <b>Java</b>)","link": "http://book.naver.com/bookdb/book_detail.php?bid=14097515","image": "https://bookthumb-phinf.pstatic.net/cover/140/975/14097515.jpg?type=m1&udate=20181023","author": "議곗뒋?븘 釉붾줈?겕","price": "36000","discount": "32400","publisher": "?씤?궗?씠?듃","pubdate": "20181101","isbn": "8966262287 9788966262281","description": "?옄諛? ?뵆?옯?뤌 紐⑤쾾 ?궗濡? ?셿踰? 媛??씠?뱶 - <b>JAVA</b> 7, 8, 9 ???쓳?옄諛? 6 異쒖떆 吏곹썑 異쒓컙?맂 ?롮씠?럺?떚釉? ?옄諛? 2?뙋?? ?씠?썑濡? ?옄諛붾뒗 而ㅻ떎?? 蹂??솕瑜? 寃れ뿀?떎. 洹몃옒?꽌... ?룷?븿?븳 ???엯 異붾줎\n-\t@SAFEVARARGS ?븷?꼫?뀒?씠?뀡\n-\tTRY-WITH-RESOURCES 臾?\n-\tOPTIONAL T ?씤?꽣?럹?씠?뒪, <b>JAVA</b>.TIME, 而щ젆?뀡?쓽 ?렪?쓽 ?뙥?꽣由? 硫붿꽌?뱶 ?벑?쓽 ?깉濡쒖슫 ?씪?씠釉뚮윭由? 湲곕뒫"},{"title": "<b>Java</b>?쓽 ?젙?꽍 (理쒖떊 <b>Java</b> 8.0 ?룷?븿)","link": "http://book.naver.com/bookdb/book_detail.php?bid=10191151","image": "https://bookthumb-phinf.pstatic.net/cover/101/911/10191151.jpg?type=m1&udate=20190204","author": "?궓沅곸꽦","price": "30000","discount": "27000","publisher": "?룄?슦異쒗뙋","pubdate": "20160127","isbn": "8994492038 9788994492032","description": "?옄諛붿쓽 湲곗큹遺??꽣 ?떎?쟾?솢?슜源뚯? 紐⑤몢 ?떞?떎!?옄諛붿쓽 湲곗큹遺??꽣 媛앹젣吏??뼢媛쒕뀗?쓣 ?꽆?뼱 ?떎?쟾?솢?슜源뚯? ?닔濡앺븳??<b>JAVA</b>?쓽 ?젙?꽍??. ???옄?쓽 ?삤?옖 ?떎臾닿꼍?뿕怨? 媛뺤쓽?븳... ?뜑遺덉뼱 湲곗〈?쓽 寃쎈젰?옄?뱾?쓣 ?쐞?빐 ?옄諛? 理쒖떊湲곕뒫?씤 ?엺?떎?? ?뒪?듃由쇨낵 洹? 諛뽰쓽 ?옄諛붿쓽 理쒖떊踰꾩졏 <b>JAVA</b>8?쓽 ?깉濡쒖슫 湲곕뒫源뚯? ?옄?꽭?븯寃? ?꽕紐낇븯怨? ?엳?떎."},{"title": "?씠寃껋씠 ?옄諛붾떎 (?떊?슜沅뚯쓽 <b>Java</b> ?봽濡쒓렇?옒諛? ?젙蹂?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=8589375","image": "https://bookthumb-phinf.pstatic.net/cover/085/893/08589375.jpg?type=m1&udate=20200516","author": "?떊?슜沅?","price": "30000","discount": "27000","publisher": "?븳鍮쏅?몃뵒?뼱","pubdate": "20150105","isbn": "8968481474 9788968481475","description": "?롮씠寃껋씠 ?옄諛붾떎?륁? 15?뀈 ?씠?긽 ?옄諛? ?뼵?뼱瑜? 援먯쑁?빐?삩 ?옄諛? ?쟾臾멸컯?궗?쓽 ?끂?븯?슦瑜? ?븘?굦 ?뾾?씠 ?떞?븘?궦 ?옄諛? ?엯臾몄꽌?씠?떎. ?옄諛? ?엯臾몄옄瑜? 諛곕젮?븳 移쒖젅?븳 ?꽕紐낃낵 諛곕젮濡? 1?옣?뿉 ???씤?썝 ?꽕移? 諛⑸쾿?쓣 ?젣怨듯븯?뿬 ?돺寃? ?븰?뒿?솚寃쎌쓣 援ъ텞?븷 ?닔 ?엳?떎. ?삉?븳 以묎툒 媛쒕컻?옄濡? ?굹?븘媛?湲? ?쐞?븳 ?엺?떎?떇(14?옣), JAVAFX(17?옣), NIO(18... "},{"title": "First <b>Java</b>","link": "http://book.naver.com/bookdb/book_detail.php?bid=17936688","image": "https://bookthumb-phinf.pstatic.net/cover/179/366/17936688.jpg?type=m1&udate=20210209","author": "?쑀?쁺吏?|?씠?룄?뿰","price": "30000","discount": "27000","publisher": "?뿰?몢?뿉?뵒?뀡","pubdate": "20210129","isbn": "1188831739 9791188831739","description": "?쁽?옱 援??궡?뿉?꽌?뒗 怨듦났 諛? 湲곗뾽?쓽 ?떆?뒪?뀥?뱾?쓽 ??遺?遺꾨뱾?씠 ?옄諛? 湲곕컲?쓽 ?쎒 ?븷?뵆由ъ??씠?뀡 ?봽濡쒖젥?듃濡? 吏꾪뻾?씠 ?릺怨? ?엳?떎. ?듅?엳 ?뒪留덊듃 ?뵒諛붿씠?뒪媛? 蹂닿툒?씠 ?릺怨? 蹂댄렪?솕 ?릺硫댁꽌 ?븞?뱶濡쒖씠?뱶 ?슫?쁺泥댁젣 湲곕컲?쓽 ?뒪留덊듃 ?뵒諛붿씠?뒪?뿉 ???븳 愿??떖?씠 留롮븘吏?怨? ?씠 ?뵒諛붿씠?뒪?뿉?꽌 ?떎?뻾?씠 媛??뒫?븳 ?븷?뵆由ъ??씠?뀡?뿉 ???븳 ?슂援щ뱾?씠 留롮씠... "},{"title": "?쑄?꽦?슦?쓽 ?뿴?삁 <b>Java</b> ?봽濡쒓렇?옒諛?","link": "http://book.naver.com/bookdb/book_detail.php?bid=12236206","image": "https://bookthumb-phinf.pstatic.net/cover/122/362/12236206.jpg?type=m1&udate=20181129","author": "?쑄?꽦?슦","price": "30000","discount": "27000","publisher": "?삤?젋吏?誘몃뵒?뼱","pubdate": "20170705","isbn": "8996094072 9788996094074","description": "?롮쑄?꽦?슦?쓽 ?뿴?삁 <b>JAVA</b> ?봽濡쒓렇?옒諛띲?륁? 理쒖떊 ?궡?슜?쓣 諛뷀깢?쑝濡? ?깉濡?寃? 吏묓븘?맂 ?옄諛붿콉?씠?떎. ?옄諛? 8?씠 諛쒗몴?릺硫댁꽌 珥덈낫?옄?뱾?뿉寃? ?뼱?젮?슱 ?닔 ?엳?뒗 臾몃쾿?쟻 ?슂?냼媛? ?긽?떦?닔 ?룷?븿?씠 ?릺?뿀?쑝?굹 ???옄 ?듅?쑀?쓽 ?돺怨? 紐낇솗?븳 ?꽕紐낆쓣 ?넻?빐?꽌 ?뼱?졄吏? ?븡寃? ?빐?떦 ?궡?슜?뱾?쓣 ?꽕紐낇븳 梨낆씠?떎. 蹂몄꽌?쓽 ?꽕紐낆뿉?뒗 ?룆?옄?뱾?쓣 怨좊젮?븳 怨쇳븯吏?... "},{"title": "珥덈낫?옄瑜? ?쐞?븳 JavaScript 200?젣","link": "http://book.naver.com/bookdb/book_detail.php?bid=14602049","image": "https://bookthumb-phinf.pstatic.net/cover/146/020/14602049.jpg?type=m1&udate=20190918","author": "怨좎옱?룄|?끂吏??뿰","price": "25000","discount": "22500","publisher": "?젙蹂대Ц?솕?궗","pubdate": "20190310","isbn": "8956748241 9788956748245","description": "JAVASCRIPT濡? 臾댁뾿?씠?뱺 ?빐?궪 ?닔 ?엳?떎!\n\n諛? 鍮좊Ⅴ寃? 吏꾪솕?릺硫댁꽌 ?꽭怨꾩뿉?꽌 媛??옣 ?씤湲? ?엳?뒗 ?뼵?뼱媛? ?맂 ?옄諛붿뒪?겕由쏀듃瑜? ?꽕移섎??꽣 ?솢?슜源뚯? ?삁?젣蹂꾨줈 移쒖젅?븯寃? ?븞?궡?븯?뒗 梨낆씠?떎. 蹂몄꽌?뒗 珥? 5媛쒖쓽 ?뙆?듃(?엯臾?, 珥덇툒, 以묎툒, ?솢?슜, ?떎臾?)濡? 援ъ꽦?릺?뼱 ?엳?쑝硫? 200媛쒖쓽 ?삁?젣瑜? ?븰?뒿?븯硫댁꽌 ?옄諛붿뒪?겕由쏀듃瑜? ?씡?옄 ?닔 ?엳?떎.... "},{"title": "紐낇뭹 <b>JAVA</b> Programming (洹?濡? 諛곗슦?뒗 ?옄諛붽? ?븘?땲?씪, ?늿?쑝濡? 紐몄쑝濡? 諛곗슦?뒗 ?옄諛붽컯醫?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=13650995","image": "https://bookthumb-phinf.pstatic.net/cover/136/509/13650995.jpg?type=m1&udate=20200408","author": "?솴湲고깭|源??슚?닔","price": "33000","discount": "31350","publisher": "?깮?뒫異쒗뙋?궗","pubdate": "20180601","isbn": "897050947X 9788970509471","description": "?옄諛?(<b>JAVA</b>)?뒗 洹? ?씠?쟾 ?떆???뿉 ?엳?뿀?뜕 ?봽濡쒓렇?옒諛? ?뼵?뼱?뿉?꽌 ?븳 李⑥썝 吏꾪솕?맂 媛쒕뀗?쑝濡? 媛쒕컻?맂 媛??엳 ?쁺紐낆쟻... <b>JAVA</b> 8, 9?뿉?꽌 ?씤?꽣?럹?씠?뒪 ?젙?쓽媛? 諛붾?뚯뿀湲? ?븣臾몄뿉 5?옣 ?씤?꽣?럹?씠?뒪 遺?遺꾩쓣 ?닔?젙?븯???떎.\n3. 6.7?젅 WRAPPER ?겢?옒?뒪 遺?遺꾩쓣 媛깆떊?븯???떎. <b>JAVA</b> 9遺??꽣 ?깮?꽦?옄瑜? ?씠?슜?븯?뿬 WRAPPER 媛앹껜瑜? ?깮?꽦?븯?뒗 諛⑸쾿?씠... "},{"title": "OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests","link": "http://book.naver.com/bookdb/book_detail.php?bid=17941496","image": "https://bookthumb-phinf.pstatic.net/cover/179/414/17941496.jpg?type=m1&udate=20210204","author": "Scott Selikoff","price": "27090","discount": "","publisher": "Sybex","pubdate": "20210201","isbn": "1119696143 9781119696148","description": "NOTE: The OCP <b>Java</b> SE 11 Programmer I Exam 1Z0-815 and Programmer II Exam 1Z0-816 have been... Improve your preparation for the OCP <b>Java</b> SE 11 Developer exam with these comprehensive practice tests  OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests: Exam... "},{"title": "珥덈낫?옄瑜? ?쐞?븳 <b>Java</b> 200?젣","link": "http://book.naver.com/bookdb/book_detail.php?bid=13746617","image": "https://bookthumb-phinf.pstatic.net/cover/137/466/13746617.jpg?type=m1&udate=20180705","author": "議고슚??","price": "28000","discount": "25200","publisher": "?젙蹂대Ц?솕?궗","pubdate": "20180705","isbn": "8956747857 9788956747859","description": "?솢?슜?릺?뒗 <b>Java</b> ?삁?젣 200媛쒕?? ?뾼?꽑?븯?뿬 ?닔濡앺븯???뒿?땲?떎. 鍮뚮낫?뱶 李⑦듃 留뚮뱾湲?, 移대뱶 寃뚯엫 留뚮뱾湲?, ?씤?궗 愿?由?(HRM)?슜 ?뼱?뵆由ъ??씠?뀡 留뚮뱾湲?, 梨꾪똿 留뚮뱾湲? ?벑?쓽 ?삁?젣瑜? ?넻?빐 ?옄?뿰?뒪?읇寃? <b>Java</b> ?봽濡쒓렇?옒諛띿뿉 ?씡?닕?빐吏?怨? ?씎誘몃?? ?뒓?굜 ?닔 ?엳?룄濡? 吏묓븘?븯???쑝硫?, ?삁?젣瑜? ?븯?굹?븯?굹 ?떎?뒿?븯?떎 蹂대㈃ <b>Java</b> ?봽濡쒓렇?옒諛?... "
		}
	 ]
}
*/
