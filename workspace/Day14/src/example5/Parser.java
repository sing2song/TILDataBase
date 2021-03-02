package example5;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
{
"lastBuildDate": "Thu, 25 Feb 2021 11:22:47 +0900",
"total": 14381,
"start": 1,
"display": 10,
"items": [
	{
		"title": "<b>Java</b>? ? ? κΈ°μ΄?Έ",
		"link": "http://book.naver.com/bookdb/book_detail.php?bid=16031391",
		"image": "https://bookthumb-phinf.pstatic.net/cover/160/313/16031391.jpg?type=m1&udate=20200108",
		"author": "?¨κΆμ±",
		"price": "25000",
		"discount": "22500",
		"publisher": "??°μΆν",
		"pubdate": "20191230",
		"isbn": "8994492046 9788994492049",
		"description": "κ΅μ‘??₯?? ?€μ³μ?? ???€? ??΄ ?°κ³?, ???€?κ²? μ§μ  κ²?μ¦λ°κ³? ?Έ?λ°μ? μ±?. μ½λ©? μ²μ λ°°μ°? ?¬?? ?λ°λ?? ?½κ²? λ°°μΈ? ?κ²? ???μ€??€."
		},
		{
		"title": "?΄??°λΈ? ?λ°? (Effective <b>Java</b>)","link": "http://book.naver.com/bookdb/book_detail.php?bid=14097515","image": "https://bookthumb-phinf.pstatic.net/cover/140/975/14097515.jpg?type=m1&udate=20181023","author": "μ‘°μ? λΈλ‘?¬","price": "36000","discount": "32400","publisher": "?Έ?¬?΄?Έ","pubdate": "20181101","isbn": "8966262287 9788966262281","description": "?λ°? ??«?Ό λͺ¨λ² ?¬λ‘? ?λ²? κ°??΄? - <b>JAVA</b> 7, 8, 9 ????λ°? 6 μΆμ μ§ν μΆκ°? ?μ΄??°λΈ? ?λ°? 2??? ?΄?λ‘? ?λ°λ μ»€λ€?? λ³??λ₯? κ²ͺμ?€. κ·Έλ?... ?¬?¨? ??? μΆλ‘ \n-\t@SAFEVARARGS ? ???΄?\n-\tTRY-WITH-RESOURCES λ¬?\n-\tOPTIONAL T ?Έ?°??΄?€, <b>JAVA</b>.TIME, μ»¬λ ?? ?Έ? ?©?°λ¦? λ©μ? ?±? ?λ‘μ΄ ?Ό?΄λΈλ¬λ¦? κΈ°λ₯"},{"title": "<b>Java</b>? ? ? (μ΅μ  <b>Java</b> 8.0 ?¬?¨)","link": "http://book.naver.com/bookdb/book_detail.php?bid=10191151","image": "https://bookthumb-phinf.pstatic.net/cover/101/911/10191151.jpg?type=m1&udate=20190204","author": "?¨κΆμ±","price": "30000","discount": "27000","publisher": "??°μΆν","pubdate": "20160127","isbn": "8994492038 9788994492032","description": "?λ°μ κΈ°μ΄λΆ??° ?€? ??©κΉμ? λͺ¨λ ?΄?€!?λ°μ κΈ°μ΄λΆ??° κ°μ μ§??₯κ°λ? ??΄ ?€? ??©κΉμ? ?λ‘ν??<b>JAVA</b>? ? ???. ???? ?€? ?€λ¬΄κ²½?κ³? κ°μ?... ?λΆμ΄ κΈ°μ‘΄? κ²½λ ₯??€? ??΄ ?λ°? μ΅μ κΈ°λ₯?Έ ??€?? ?€?Έλ¦Όκ³Ό κ·? λ°μ ?λ°μ μ΅μ λ²μ Ό <b>JAVA</b>8? ?λ‘μ΄ κΈ°λ₯κΉμ? ??Έ?κ²? ?€λͺνκ³? ??€."},{"title": "?΄κ²μ΄ ?λ°λ€ (? ?©κΆμ <b>Java</b> ?λ‘κ·Έ?λ°? ? λ³?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=8589375","image": "https://bookthumb-phinf.pstatic.net/cover/085/893/08589375.jpg?type=m1&udate=20200516","author": "? ?©κΆ?","price": "30000","discount": "27000","publisher": "?λΉλ?Έλ?΄","pubdate": "20150105","isbn": "8968481474 9788968481475","description": "?μ΄κ²μ΄ ?λ°λ€?μ? 15? ?΄? ?λ°? ?Έ?΄λ₯? κ΅μ‘?΄?¨ ?λ°? ? λ¬Έκ°?¬? ?Έ??°λ₯? ?? ??΄ ?΄??Έ ?λ°? ?λ¬Έμ?΄?€. ?λ°? ?λ¬Έμλ₯? λ°°λ €? μΉμ ? ?€λͺκ³Ό λ°°λ €λ‘? 1?₯? ???Έ? ?€μΉ? λ°©λ²? ? κ³΅ν?¬ ?½κ²? ??΅?κ²½μ κ΅¬μΆ?  ? ??€. ?? μ€κΈ κ°λ°?λ‘? ??κ°?κΈ? ?? ??€?(14?₯), JAVAFX(17?₯), NIO(18... "},{"title": "First <b>Java</b>","link": "http://book.naver.com/bookdb/book_detail.php?bid=17936688","image": "https://bookthumb-phinf.pstatic.net/cover/179/366/17936688.jpg?type=m1&udate=20210209","author": "? ?μ§?|?΄??°","price": "30000","discount": "27000","publisher": "?°????","pubdate": "20210129","isbn": "1188831739 9791188831739","description": "??¬ κ΅??΄??? κ³΅κ³΅ λ°? κΈ°μ? ??€??€? ??λΆ?λΆλ€?΄ ?λ°? κΈ°λ°? ?Ή ? ?λ¦¬μ??΄? ?λ‘μ ?Έλ‘? μ§ν?΄ ?κ³? ??€. ?Ή? ?€λ§νΈ ?λ°μ΄?€κ°? λ³΄κΈ?΄ ?κ³? λ³΄νΈ? ?λ©΄μ ??λ‘μ΄? ?΄?μ²΄μ  κΈ°λ°? ?€λ§νΈ ?λ°μ΄?€? ??? κ΄??¬?΄ λ§μμ§?κ³? ?΄ ?λ°μ΄?€?? ?€??΄ κ°??₯? ? ?λ¦¬μ??΄?? ??? ?κ΅¬λ€?΄ λ§μ΄... "},{"title": "?€?±?°? ?΄? <b>Java</b> ?λ‘κ·Έ?λ°?","link": "http://book.naver.com/bookdb/book_detail.php?bid=12236206","image": "https://bookthumb-phinf.pstatic.net/cover/122/362/12236206.jpg?type=m1&udate=20181129","author": "?€?±?°","price": "30000","discount": "27000","publisher": "?€? μ§?λ―Έλ?΄","pubdate": "20170705","isbn": "8996094072 9788996094074","description": "?μ€?±?°? ?΄? <b>JAVA</b> ?λ‘κ·Έ?λ°γ?μ? μ΅μ  ?΄?©? λ°ν?Όλ‘? ?λ‘?κ²? μ§ν? ?λ°μ±?΄?€. ?λ°? 8?΄ λ°ν?λ©΄μ μ΄λ³΄??€?κ²? ?΄? €?Έ ? ?? λ¬Έλ²?  ??κ°? ??Ή? ?¬?¨?΄ ???Ό? ??? ?Ή? ? ?½κ³? λͺν? ?€λͺμ ?΅?΄? ?΄? ΅μ§? ?κ²? ?΄?Ή ?΄?©?€? ?€λͺν μ±μ΄?€. λ³Έμ? ?€λͺμ? ???€? κ³ λ €? κ³Όνμ§?... "},{"title": "μ΄λ³΄?λ₯? ?? JavaScript 200? ","link": "http://book.naver.com/bookdb/book_detail.php?bid=14602049","image": "https://bookthumb-phinf.pstatic.net/cover/146/020/14602049.jpg?type=m1&udate=20190918","author": "κ³ μ¬?|?Έμ§??°","price": "25000","discount": "22500","publisher": "? λ³΄λ¬Έ??¬","pubdate": "20190310","isbn": "8956748241 9788956748245","description": "JAVASCRIPTλ‘? λ¬΄μ?΄?  ?΄?Ό ? ??€!\n\nλ°? λΉ λ₯΄κ²? μ§ν?λ©΄μ ?Έκ³μ? κ°??₯ ?ΈκΈ? ?? ?Έ?΄κ°? ? ?λ°μ€?¬λ¦½νΈλ₯? ?€μΉλ??° ??©κΉμ? ?? λ³λ‘ μΉμ ?κ²? ??΄?? μ±μ΄?€. λ³Έμ? μ΄? 5κ°μ ??Έ(?λ¬?, μ΄κΈ, μ€κΈ, ??©, ?€λ¬?)λ‘? κ΅¬μ±??΄ ??Όλ©? 200κ°μ ?? λ₯? ??΅?λ©΄μ ?λ°μ€?¬λ¦½νΈλ₯? ?΅? ? ??€.... "},{"title": "λͺν <b>JAVA</b> Programming (κ·?λ‘? λ°°μ°? ?λ°κ? ???Ό, ??Όλ‘? λͺΈμΌλ‘? λ°°μ°? ?λ°κ°μ’?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=13650995","image": "https://bookthumb-phinf.pstatic.net/cover/136/509/13650995.jpg?type=m1&udate=20200408","author": "?©κΈ°ν|κΉ??¨?","price": "33000","discount": "31350","publisher": "??₯μΆν?¬","pubdate": "20180601","isbn": "897050947X 9788970509471","description": "?λ°?(<b>JAVA</b>)? κ·? ?΄?  ???? ??? ?λ‘κ·Έ?λ°? ?Έ?΄?? ? μ°¨μ μ§ν? κ°λ?Όλ‘? κ°λ°? κ°?? ?λͺμ ... <b>JAVA</b> 8, 9?? ?Έ?°??΄?€ ? ?κ°? λ°λ?μκΈ? ?λ¬Έμ 5?₯ ?Έ?°??΄?€ λΆ?λΆμ ?? ????€.\n3. 6.7?  WRAPPER ?΄??€ λΆ?λΆμ κ°±μ ????€. <b>JAVA</b> 9λΆ??° ??±?λ₯? ?΄?©??¬ WRAPPER κ°μ²΄λ₯? ??±?? λ°©λ²?΄... "},{"title": "OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests","link": "http://book.naver.com/bookdb/book_detail.php?bid=17941496","image": "https://bookthumb-phinf.pstatic.net/cover/179/414/17941496.jpg?type=m1&udate=20210204","author": "Scott Selikoff","price": "27090","discount": "","publisher": "Sybex","pubdate": "20210201","isbn": "1119696143 9781119696148","description": "NOTE: The OCP <b>Java</b> SE 11 Programmer I Exam 1Z0-815 and Programmer II Exam 1Z0-816 have been... Improve your preparation for the OCP <b>Java</b> SE 11 Developer exam with these comprehensive practice tests  OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests: Exam... "},{"title": "μ΄λ³΄?λ₯? ?? <b>Java</b> 200? ","link": "http://book.naver.com/bookdb/book_detail.php?bid=13746617","image": "https://bookthumb-phinf.pstatic.net/cover/137/466/13746617.jpg?type=m1&udate=20180705","author": "μ‘°ν¨??","price": "28000","discount": "25200","publisher": "? λ³΄λ¬Έ??¬","pubdate": "20180705","isbn": "8956747857 9788956747859","description": "??©?? <b>Java</b> ??  200κ°λ?? ?? ??¬ ?λ‘ν???΅??€. λΉλ³΄? μ°¨νΈ λ§λ€κΈ?, μΉ΄λ κ²μ λ§λ€κΈ?, ?Έ?¬ κ΄?λ¦?(HRM)?© ?΄?λ¦¬μ??΄? λ§λ€κΈ?, μ±ν λ§λ€κΈ? ?±? ?? λ₯? ?΅?΄ ??°?€?½κ²? <b>Java</b> ?λ‘κ·Έ?λ°μ ?΅??΄μ§?κ³? ?₯λ―Έλ?? ?? ? ??λ‘? μ§ν????Όλ©?, ?? λ₯? ???? ?€?΅??€ λ³΄λ©΄ <b>Java</b> ?λ‘κ·Έ?λ°?... "}]}

*/

public class Parser {
	public static ArrayList<Book> Parsing(String jsonData) {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			JSONParser jsonParse = new JSONParser();
		
			//JSONParseΏ‘ json΅₯ΐΜΕΝΈ¦ ³ΦΎξ ΖΔ½ΜΗΡ ΄Ωΐ½ JSONObject·Ξ Ί―Θ―ΗΡ΄Ω. 
			JSONObject jsonObj = (JSONObject) jsonParse.parse(jsonData); 		

			JSONArray itemsArray = (JSONArray) jsonObj.get("items");
			
			
			for(int i=0; i< itemsArray.size(); i++) {
				JSONObject bookObject = (JSONObject)itemsArray.get(i);
				Book book = Book.Parsing(bookObject);
				books.add(book);
			}
			return books;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return books;
		}		
	}
}
