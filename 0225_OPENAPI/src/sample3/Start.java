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
        String clientId = "CERXzODq_Ptd1HkbBZ7w"; 	//¾ÖÇÃ¸®ÄÉÀÌ¼Ç Å¬¶óÀÌ¾ğÆ® ¾ÆÀÌµğ°ª"
        String clientSecret = "3BBPjQV_y0"; 		//¾ÖÇÃ¸®ÄÉÀÌ¼Ç Å¬¶óÀÌ¾ğÆ® ½ÃÅ©¸´°ª"

        String text = null;
        try {
            text = URLEncoder.encode("Java", "UTF-8");
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
	"lastBuildDate": "Thu, 25 Feb 2021 11:22:47 +0900",
	"total": 14381,
	"start": 1,
	"display": 10,
	"items": [
		{
			"title": "<b>Java</b>?˜ ? •?„ ê¸°ì´ˆ?¸",
			"link": "http://book.naver.com/bookdb/book_detail.php?bid=16031391",
			"image": "https://bookthumb-phinf.pstatic.net/cover/160/313/16031391.jpg?type=m1&udate=20200108",
			"author": "?‚¨ê¶ì„±",
			"price": "25000",
			"discount": "22500",
			"publisher": "?„?š°ì¶œíŒ",
			"pubdate": "20191230",
			"isbn": "8994492046 9788994492049",
			"description": "êµìœ¡?˜„?¥?—?„œ ?’¤ì³ì??Š” ?•™?ƒ?“¤?„ ?œ„?•´ ?“°ê³?, ?•™?ƒ?“¤?—ê²? ì§ì ‘ ê²?ì¦ë°›ê³? ?˜¸?‰ë°›ì? ì±?. ì½”ë”©?„ ì²˜ìŒ ë°°ìš°?Š” ?‚¬?Œ?„ ?ë°”ë?? ?‰½ê²? ë°°ìš¸?ˆ˜ ?ˆê²? ?„??ì¤??‹¤."
		},
		{
			"title": "?´?™?‹°ë¸? ?ë°? (Effective <b>Java</b>)","link": "http://book.naver.com/bookdb/book_detail.php?bid=14097515","image": "https://bookthumb-phinf.pstatic.net/cover/140/975/14097515.jpg?type=m1&udate=20181023","author": "ì¡°ìŠˆ?•„ ë¸”ë¡œ?¬","price": "36000","discount": "32400","publisher": "?¸?‚¬?´?Š¸","pubdate": "20181101","isbn": "8966262287 9788966262281","description": "?ë°? ?”Œ?«?¼ ëª¨ë²” ?‚¬ë¡? ?™„ë²? ê°??´?“œ - <b>JAVA</b> 7, 8, 9 ???‘?ë°? 6 ì¶œì‹œ ì§í›„ ì¶œê°„?œ ?ì´?™?‹°ë¸? ?ë°? 2?Œ?? ?´?›„ë¡? ?ë°”ëŠ” ì»¤ë‹¤?? ë³??™”ë¥? ê²ªì—ˆ?‹¤. ê·¸ë˜?„œ... ?¬?•¨?•œ ???… ì¶”ë¡ \n-\t@SAFEVARARGS ?• ?„ˆ?…Œ?´?…˜\n-\tTRY-WITH-RESOURCES ë¬?\n-\tOPTIONAL T ?¸?„°?˜?´?Š¤, <b>JAVA</b>.TIME, ì»¬ë ‰?…˜?˜ ?¸?˜ ?Œ©?„°ë¦? ë©”ì„œ?“œ ?“±?˜ ?ƒˆë¡œìš´ ?¼?´ë¸ŒëŸ¬ë¦? ê¸°ëŠ¥"},{"title": "<b>Java</b>?˜ ? •?„ (ìµœì‹  <b>Java</b> 8.0 ?¬?•¨)","link": "http://book.naver.com/bookdb/book_detail.php?bid=10191151","image": "https://bookthumb-phinf.pstatic.net/cover/101/911/10191151.jpg?type=m1&udate=20190204","author": "?‚¨ê¶ì„±","price": "30000","discount": "27000","publisher": "?„?š°ì¶œíŒ","pubdate": "20160127","isbn": "8994492038 9788994492032","description": "?ë°”ì˜ ê¸°ì´ˆë¶??„° ?‹¤? „?™œ?š©ê¹Œì? ëª¨ë‘ ?‹´?‹¤!?ë°”ì˜ ê¸°ì´ˆë¶??„° ê°ì œì§??–¥ê°œë…?„ ?„˜?–´ ?‹¤? „?™œ?š©ê¹Œì? ?ˆ˜ë¡í•œ??<b>JAVA</b>?˜ ? •?„??. ????˜ ?˜¤?œ ?‹¤ë¬´ê²½?—˜ê³? ê°•ì˜?•œ... ?”ë¶ˆì–´ ê¸°ì¡´?˜ ê²½ë ¥??“¤?„ ?œ„?•´ ?ë°? ìµœì‹ ê¸°ëŠ¥?¸ ?Œ?‹¤?? ?Š¤?Š¸ë¦¼ê³¼ ê·? ë°–ì˜ ?ë°”ì˜ ìµœì‹ ë²„ì ¼ <b>JAVA</b>8?˜ ?ƒˆë¡œìš´ ê¸°ëŠ¥ê¹Œì? ??„¸?•˜ê²? ?„¤ëª…í•˜ê³? ?ˆ?‹¤."},{"title": "?´ê²ƒì´ ?ë°”ë‹¤ (?‹ ?š©ê¶Œì˜ <b>Java</b> ?”„ë¡œê·¸?˜ë°? ? •ë³?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=8589375","image": "https://bookthumb-phinf.pstatic.net/cover/085/893/08589375.jpg?type=m1&udate=20200516","author": "?‹ ?š©ê¶?","price": "30000","discount": "27000","publisher": "?•œë¹›ë?¸ë””?–´","pubdate": "20150105","isbn": "8968481474 9788968481475","description": "?ì´ê²ƒì´ ?ë°”ë‹¤?ì? 15?…„ ?´?ƒ ?ë°? ?–¸?–´ë¥? êµìœ¡?•´?˜¨ ?ë°? ? „ë¬¸ê°•?‚¬?˜ ?…¸?•˜?š°ë¥? ?•„?‚Œ ?—†?´ ?‹´?•„?‚¸ ?ë°? ?…ë¬¸ì„œ?´?‹¤. ?ë°? ?…ë¬¸ìë¥? ë°°ë ¤?•œ ì¹œì ˆ?•œ ?„¤ëª…ê³¼ ë°°ë ¤ë¡? 1?¥?— ???¸?› ?„¤ì¹? ë°©ë²•?„ ? œê³µí•˜?—¬ ?‰½ê²? ?•™?Šµ?™˜ê²½ì„ êµ¬ì¶•?•  ?ˆ˜ ?ˆ?‹¤. ?˜?•œ ì¤‘ê¸‰ ê°œë°œ?ë¡? ?‚˜?•„ê°?ê¸? ?œ„?•œ ?Œ?‹¤?‹(14?¥), JAVAFX(17?¥), NIO(18... "},{"title": "First <b>Java</b>","link": "http://book.naver.com/bookdb/book_detail.php?bid=17936688","image": "https://bookthumb-phinf.pstatic.net/cover/179/366/17936688.jpg?type=m1&udate=20210209","author": "?œ ?˜ì§?|?´?„?—°","price": "30000","discount": "27000","publisher": "?—°?‘?—?””?…˜","pubdate": "20210129","isbn": "1188831739 9791188831739","description": "?˜„?¬ êµ??‚´?—?„œ?Š” ê³µê³µ ë°? ê¸°ì—…?˜ ?‹œ?Š¤?…œ?“¤?˜ ??ë¶?ë¶„ë“¤?´ ?ë°? ê¸°ë°˜?˜ ?›¹ ?• ?”Œë¦¬ì??´?…˜ ?”„ë¡œì ?Š¸ë¡? ì§„í–‰?´ ?˜ê³? ?ˆ?‹¤. ?Š¹?ˆ ?Š¤ë§ˆíŠ¸ ?””ë°”ì´?Š¤ê°? ë³´ê¸‰?´ ?˜ê³? ë³´í¸?™” ?˜ë©´ì„œ ?•ˆ?“œë¡œì´?“œ ?š´?˜ì²´ì œ ê¸°ë°˜?˜ ?Š¤ë§ˆíŠ¸ ?””ë°”ì´?Š¤?— ???•œ ê´??‹¬?´ ë§ì•„ì§?ê³? ?´ ?””ë°”ì´?Š¤?—?„œ ?‹¤?–‰?´ ê°??Š¥?•œ ?• ?”Œë¦¬ì??´?…˜?— ???•œ ?š”êµ¬ë“¤?´ ë§ì´... "},{"title": "?œ¤?„±?š°?˜ ?—´?˜ˆ <b>Java</b> ?”„ë¡œê·¸?˜ë°?","link": "http://book.naver.com/bookdb/book_detail.php?bid=12236206","image": "https://bookthumb-phinf.pstatic.net/cover/122/362/12236206.jpg?type=m1&udate=20181129","author": "?œ¤?„±?š°","price": "30000","discount": "27000","publisher": "?˜¤? Œì§?ë¯¸ë””?–´","pubdate": "20170705","isbn": "8996094072 9788996094074","description": "?ìœ¤?„±?š°?˜ ?—´?˜ˆ <b>JAVA</b> ?”„ë¡œê·¸?˜ë°ã?ì? ìµœì‹  ?‚´?š©?„ ë°”íƒ•?œ¼ë¡? ?ƒˆë¡?ê²? ì§‘í•„?œ ?ë°”ì±…?´?‹¤. ?ë°? 8?´ ë°œí‘œ?˜ë©´ì„œ ì´ˆë³´??“¤?—ê²? ?–´? ¤?š¸ ?ˆ˜ ?ˆ?Š” ë¬¸ë²•?  ?š”?†Œê°? ?ƒ?‹¹?ˆ˜ ?¬?•¨?´ ?˜?—ˆ?œ¼?‚˜ ??? ?Š¹?œ ?˜ ?‰½ê³? ëª…í™•?•œ ?„¤ëª…ì„ ?†µ?•´?„œ ?–´? µì§? ?•Šê²? ?•´?‹¹ ?‚´?š©?“¤?„ ?„¤ëª…í•œ ì±…ì´?‹¤. ë³¸ì„œ?˜ ?„¤ëª…ì—?Š” ?…??“¤?„ ê³ ë ¤?•œ ê³¼í•˜ì§?... "},{"title": "ì´ˆë³´?ë¥? ?œ„?•œ JavaScript 200? œ","link": "http://book.naver.com/bookdb/book_detail.php?bid=14602049","image": "https://bookthumb-phinf.pstatic.net/cover/146/020/14602049.jpg?type=m1&udate=20190918","author": "ê³ ì¬?„|?…¸ì§??—°","price": "25000","discount": "22500","publisher": "? •ë³´ë¬¸?™”?‚¬","pubdate": "20190310","isbn": "8956748241 9788956748245","description": "JAVASCRIPTë¡? ë¬´ì—‡?´?“  ?•´?‚¼ ?ˆ˜ ?ˆ?‹¤!\n\në°? ë¹ ë¥´ê²? ì§„í™”?˜ë©´ì„œ ?„¸ê³„ì—?„œ ê°??¥ ?¸ê¸? ?ˆ?Š” ?–¸?–´ê°? ?œ ?ë°”ìŠ¤?¬ë¦½íŠ¸ë¥? ?„¤ì¹˜ë??„° ?™œ?š©ê¹Œì? ?˜ˆ? œë³„ë¡œ ì¹œì ˆ?•˜ê²? ?•ˆ?‚´?•˜?Š” ì±…ì´?‹¤. ë³¸ì„œ?Š” ì´? 5ê°œì˜ ?ŒŒ?Š¸(?…ë¬?, ì´ˆê¸‰, ì¤‘ê¸‰, ?™œ?š©, ?‹¤ë¬?)ë¡? êµ¬ì„±?˜?–´ ?ˆ?œ¼ë©? 200ê°œì˜ ?˜ˆ? œë¥? ?•™?Šµ?•˜ë©´ì„œ ?ë°”ìŠ¤?¬ë¦½íŠ¸ë¥? ?µ? ?ˆ˜ ?ˆ?‹¤.... "},{"title": "ëª…í’ˆ <b>JAVA</b> Programming (ê·?ë¡? ë°°ìš°?Š” ?ë°”ê? ?•„?‹ˆ?¼, ?ˆˆ?œ¼ë¡? ëª¸ìœ¼ë¡? ë°°ìš°?Š” ?ë°”ê°•ì¢?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=13650995","image": "https://bookthumb-phinf.pstatic.net/cover/136/509/13650995.jpg?type=m1&udate=20200408","author": "?™©ê¸°íƒœ|ê¹??š¨?ˆ˜","price": "33000","discount": "31350","publisher": "?ƒ?Š¥ì¶œíŒ?‚¬","pubdate": "20180601","isbn": "897050947X 9788970509471","description": "?ë°?(<b>JAVA</b>)?Š” ê·? ?´? „ ?‹œ???— ?ˆ?—ˆ?˜ ?”„ë¡œê·¸?˜ë°? ?–¸?–´?—?„œ ?•œ ì°¨ì› ì§„í™”?œ ê°œë…?œ¼ë¡? ê°œë°œ?œ ê°??ˆ ?˜ëª…ì ... <b>JAVA</b> 8, 9?—?„œ ?¸?„°?˜?´?Š¤ ? •?˜ê°? ë°”ë?Œì—ˆê¸? ?•Œë¬¸ì— 5?¥ ?¸?„°?˜?´?Š¤ ë¶?ë¶„ì„ ?ˆ˜? •?•˜???‹¤.\n3. 6.7? ˆ WRAPPER ?´?˜?Š¤ ë¶?ë¶„ì„ ê°±ì‹ ?•˜???‹¤. <b>JAVA</b> 9ë¶??„° ?ƒ?„±?ë¥? ?´?š©?•˜?—¬ WRAPPER ê°ì²´ë¥? ?ƒ?„±?•˜?Š” ë°©ë²•?´... "},{"title": "OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests","link": "http://book.naver.com/bookdb/book_detail.php?bid=17941496","image": "https://bookthumb-phinf.pstatic.net/cover/179/414/17941496.jpg?type=m1&udate=20210204","author": "Scott Selikoff","price": "27090","discount": "","publisher": "Sybex","pubdate": "20210201","isbn": "1119696143 9781119696148","description": "NOTE: The OCP <b>Java</b> SE 11 Programmer I Exam 1Z0-815 and Programmer II Exam 1Z0-816 have been... Improve your preparation for the OCP <b>Java</b> SE 11 Developer exam with these comprehensive practice tests  OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests: Exam... "},{"title": "ì´ˆë³´?ë¥? ?œ„?•œ <b>Java</b> 200? œ","link": "http://book.naver.com/bookdb/book_detail.php?bid=13746617","image": "https://bookthumb-phinf.pstatic.net/cover/137/466/13746617.jpg?type=m1&udate=20180705","author": "ì¡°íš¨??","price": "28000","discount": "25200","publisher": "? •ë³´ë¬¸?™”?‚¬","pubdate": "20180705","isbn": "8956747857 9788956747859","description": "?™œ?š©?˜?Š” <b>Java</b> ?˜ˆ? œ 200ê°œë?? ?—„?„ ?•˜?—¬ ?ˆ˜ë¡í•˜???Šµ?‹ˆ?‹¤. ë¹Œë³´?“œ ì°¨íŠ¸ ë§Œë“¤ê¸?, ì¹´ë“œ ê²Œì„ ë§Œë“¤ê¸?, ?¸?‚¬ ê´?ë¦?(HRM)?š© ?–´?”Œë¦¬ì??´?…˜ ë§Œë“¤ê¸?, ì±„íŒ… ë§Œë“¤ê¸? ?“±?˜ ?˜ˆ? œë¥? ?†µ?•´ ??—°?Š¤?Ÿ½ê²? <b>Java</b> ?”„ë¡œê·¸?˜ë°ì— ?µ?ˆ™?•´ì§?ê³? ?¥ë¯¸ë?? ?Š?‚„ ?ˆ˜ ?ˆ?„ë¡? ì§‘í•„?•˜???œ¼ë©?, ?˜ˆ? œë¥? ?•˜?‚˜?•˜?‚˜ ?‹¤?Šµ?•˜?‹¤ ë³´ë©´ <b>Java</b> ?”„ë¡œê·¸?˜ë°?... "
		}
	 ]
}
*/
