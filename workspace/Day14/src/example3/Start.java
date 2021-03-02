package example3;

//���̹� �˻� API ���� - blog �˻�
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Start {

	public static void main(String[] args) {
		String clientId = "CERXzODq_Ptd1HkbBZ7w"; //���ø����̼� Ŭ���̾�Ʈ ���̵�"
		String clientSecret = "3BBPjQV_y0"; //���ø����̼� Ŭ���̾�Ʈ ��ũ����"

		String text = null;
		try {
			text = URLEncoder.encode("�׸����丮", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("�˻��� ���ڵ� ����",e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text;    // json ���
		//String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml ���

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
			if (responseCode == HttpURLConnection.HTTP_OK) { // ���� ȣ��
				return readBody(con.getInputStream());
			} else { // ���� �߻�
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API ��û�� ���� ����", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl){
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("������ �����߽��ϴ�. : " + apiUrl, e);
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
			throw new RuntimeException("API ������ �дµ� �����߽��ϴ�.", e);
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
	        	  "title": "NHN?�� ?��?��?�� 27�? 빌딩 <b>그린?��?���?</b> ?��?��?���?",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6448365",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/064/483/06448365.jpg?type=m1&udate=20120111",
	        	  "author": "NHN",
	        	  "price": "21000",
	        	  "discount": "",
	        	  "publisher": "?��?��?��?��?��",
	        	  "pubdate": "20110107",
	        	  "isbn": "8993976295 9788993976298",
	        	  "description": "NHN?�� �?치�?? ?��?? 분당 ?��?��?�� '<b>그린?��?���?</b>'?�� 건설 ?��?��?��리북. NHN?? 건축물의 ?���? 목표?? 과정 그리�? 결과�? ?��?��기로 ?��?�� 기업?�� �?치�?? ??중에�? ?��?��?���?, ?��로젝?��?�� 진행방법?��?�� ?��공기?�� �? ?��료�?? 공유?��?�� 기록?��로서?�� ?��미까�? ?��겼다. 3?��?��?��?�� <b>그린?��?���?</b>?��?�� 1.5?���? ?��?��?�� NHN 직원?��?��... "
	          },
	          {
	        	  "title": "4�? ?��?��?���? 주요 ?��?��기술 ?��?���? ?��마트?��?�� <b>?��?���?</b> ?��?�� ?��?��분석",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=12270484",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/122/704/12270484.jpg?type=m1&udate=20170721",
	        	  "author": "R&D?��보센?��",
	        	  "price": "350000",
	        	  "discount": "",
	        	  "publisher": "�??��?��?��?��보원",
	        	  "pubdate": "20170719",
	        	  "isbn": "115862056X 9791158620561",
	        	  "description": "<b>?��?���?</b> 분야?�� �??��?�� 최신 ?��?��?�� 분석?���? �?로벌 ?��?�� ?��?��?�� ?��?�� 경쟁?�� 강화?�� ?��???�� ?��고자 �??��기�??��?�� 분석 ?��보자료�?? ?��리하?�� ??4�? ?��?��?���? 주요 ?��?��기술 ?��?���? ?��마트?��?�� <b>?��?���?</b>?��?��... ?��?��?��명의 ?��?��?�� ?��마트?��?��?? ?��마트 <b>?��?���?</b>?�� �??��?�� 추진 ?��?���? 주요 ?��?�� ?��?��?�� ?��록하???��."
	          },
	          {
	        	  "title": "Sewing Factory ?��?�� <b>?��?���?</b> (반년�?) : �??��겨울?�� [2011?��]",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6725369",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/067/253/06725369.jpg?type=m1&udate=20110830",
	        	  "author": "",
	        	  "price": "9800",
	        	  "discount": "",
	        	  "publisher": "?��?��문화?��",
	        	  "pubdate": "20110901",
	        	  "isbn": "",
	        	  "description": "�?�? : 1. ?��?��?��?�� ?��미네?�� ?��?�� DIY�?�?(반제?��)          2. ?��, 모자, ?��?���? ?�� ?��?��메이?�� ?��?�� ?��물본 11�?(책속"
	          },
	          {
	        	  "title": "?��모니 ?��?�� <b>?��?���?</b>",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=6060216",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/060/602/06060216.jpg?type=m1&udate=20120729",
	        	  "author": "???�� ?��",
	        	  "price": "12000",
	        	  "discount": "",
	        	  "publisher": "?���??��?��",
	        	  "pubdate": "20090830",
	        	  "isbn": "8972883522 9788972883524",
	        	  "description": "기억�? 진실?�� �??���? ?�� ?��?��?�� 진짜 ?��?���?!?�� 중국?�� 직물 ?��?��?�� ?��?�� <b>그린</b> ?��?��?�하모니 ?��?�� <b>?��?���?</b>??. ?���??��?�� 주목받고 ?��?�� ?��?�� ???�� ?��?�� �? ?��?��?��?���?, 2005?�� ?��?��브레?�� ?���? 코먼?��?�� ?��?�� ?��?��?���? 맨�?�? ?�� 최종 ?��보에 ?��?��?��. 20?���? ?��반의 불안?�� 말레?��?��?���? 배경?���?, 중국 ?��민자 2?��... "
	          },
	          {
	        	  "title": "?��마트<b>?��?���?</b> 구축 R&D?��?�� �? ?��?��?��?��분석 (?��물인?��?��/?��?��?��?�� 컴퓨?��/빅데?��?��)",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=10016685",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/100/166/10016685.jpg?type=m1&udate=20191227",
	        	  "author": "R&D?��보센?��",
	        	  "price": "330000",
	        	  "discount": "",
	        	  "publisher": "�??��?��?��?��보원",
	        	  "pubdate": "20151230",
	        	  "isbn": "115862011X 9791158620110",
	        	  "description": "?�스마트<b>?��?���?</b> 구축 R&D?��?�� �? ?��?��?��?��분석?��? ?��마트<b>?��?���?</b> ?��?�� 분야 ?��천기?�� 개발 ?��보�?? ?��?�� 경쟁?�� 강화?�� ?��???�� ?��고자 �??��기�??��?�� ?��보�?? 분석 ?��리한 책이?��. ?��마트<b>?��?���?</b> ?��?��?��?�� ?���? 주목받고 ?��?�� ?��물인?��?��/?��?��?��?�� 컴퓨?��/빅데?��?�� ?��?��?�� 기술 �? ?��?��?��망과 ?��?��?���?... "
	          },
	          {
	        	  "title": "?���? <b>그린</b> 백만 ?��?��?�� 12 (?��?��)",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547598",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547598.jpg?type=m1&udate=20201203",
	        	  "author": "?���?",
	        	  "price": "2000",
	        	  "discount": "",
	        	  "publisher": "고렘<b>?��?���?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164498940 9791164498949",
	        	  "description": "?���? <b>그린</b> 그림?��?�� ?��?��?���? ?��?���?? \n?��무것?�� ?��?�� 짐꾼?��?�� 백만 ?��?��?��?��?�� 주인?�� ?��기까�?. \n그림�? 그리�? 모두 ?��?�� �??��?�� ?��?��?��?�� ?��?���?.\n?��?��?��?�� 것들?? 모두 ?��?��?�� �??��?��?��."
	          },
	          {
	        	  "title": "?���? <b>그린</b> 백만 ?��?��?�� 11",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547597",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547597.jpg?type=m1&udate=20201203",
	        	  "author": "?���?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "고렘<b>?��?���?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164495860 9791164495863",
	        	  "description": "?���? <b>그린</b> 그림?��?�� ?��?��?���? ?��?���?? \n?��무것?�� ?��?�� 짐꾼?��?�� 백만 ?��?��?��?��?�� 주인?�� ?��기까�?. \n그림�? 그리�? 모두 ?��?�� �??��?�� ?��?��?��?�� ?��?���?.\n?��?��?��?�� 것들?? 모두 ?��?��?�� �??��?��?��."
	          },
	          {
	        	  "title": "?���? <b>그린</b> 백만 ?��?��?�� 2",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547599",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547599.jpg?type=m1&udate=20201203",
	        	  "author": "?���?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "고렘<b>?��?���?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164490761 9791164490769",
	        	  "description": "?���? <b>그린</b> 그림?��?�� ?��?��?���? ?��?���?? \n?��무것?�� ?��?�� 짐꾼?��?�� 백만 ?��?��?��?��?�� 주인?�� ?��기까�?. \n그림�? 그리�? 모두 ?��?�� �??��?�� ?��?��?��?�� ?��?���?.\n?��?��?��?�� 것들?? 모두 ?��?��?�� �??��?��?��."
	          },
	          {
	        	  "title": "?���? <b>그린</b> 백만 ?��?��?�� 1",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547595",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547595.jpg?type=m1&udate=20201203",
	        	  "author": "?���?",
	        	  "price": "0",
	        	  "discount": "",
	        	  "publisher": "고렘<b>?��?���?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164490656 9791164490653",
	        	  "description": "?���? <b>그린</b> 그림?��?�� ?��?��?���? ?��?���?? \n?��무것?�� ?��?�� 짐꾼?��?�� 백만 ?��?��?��?��?�� 주인?�� ?��기까�?. \n그림�? 그리�? 모두 ?��?�� �??��?�� ?��?��?��?�� ?��?���?.\n?��?��?��?�� 것들?? 모두 ?��?��?�� �??��?��?��."
	          },
	          {
	        	  "title": "?���? <b>그린</b> 백만 ?��?��?�� 10",
	        	  "link": "http://book.naver.com/bookdb/book_detail.php?bid=17547596",
	        	  "image": "https://bookthumb-phinf.pstatic.net/cover/175/475/17547596.jpg?type=m1&udate=20201203",
	        	  "author": "?���?",
	        	  "price": "3200",
	        	  "discount": "",
	        	  "publisher": "고렘<b>?��?���?</b>",
	        	  "pubdate": "20201126",
	        	  "isbn": "1164494996 9791164494996",
	        	  "description": "?���? <b>그린</b> 그림?��?�� ?��?��?���? ?��?���?? \n?��무것?�� ?��?�� 짐꾼?��?�� 백만 ?��?��?��?��?�� 주인?�� ?��기까�?. \n그림�? 그리�? 모두 ?��?�� �??��?�� ?��?��?��?�� ?��?���?.\n?��?��?��?�� 것들?? 모두 ?��?��?�� �??��?��?��."
	          }
	          ]
	        		  
}
*/
