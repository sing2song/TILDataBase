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
        String clientId = "CERXzODq_Ptd1HkbBZ7w"; 	//���ø����̼� Ŭ���̾�Ʈ ���̵�"
        String clientSecret = "3BBPjQV_y0"; 		//���ø����̼� Ŭ���̾�Ʈ ��ũ����"

        String text = null;
        try {
            text = URLEncoder.encode("Java", "UTF-8");
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
	"lastBuildDate": "Thu, 25 Feb 2021 11:22:47 +0900",
	"total": 14381,
	"start": 1,
	"display": 10,
	"items": [
		{
			"title": "<b>Java</b>?�� ?��?�� 기초?��",
			"link": "http://book.naver.com/bookdb/book_detail.php?bid=16031391",
			"image": "https://bookthumb-phinf.pstatic.net/cover/160/313/16031391.jpg?type=m1&udate=20200108",
			"author": "?��궁성",
			"price": "25000",
			"discount": "22500",
			"publisher": "?��?��출판",
			"pubdate": "20191230",
			"isbn": "8994492046 9788994492049",
			"description": "교육?��?��?��?�� ?��쳐�??�� ?��?��?��?�� ?��?�� ?���?, ?��?��?��?���? 직접 �?증받�? ?��?��받�? �?. 코딩?�� 처음 배우?�� ?��?��?�� ?��바�?? ?���? 배울?�� ?���? ?��??�??��."
		},
		{
			"title": "?��?��?���? ?���? (Effective <b>Java</b>)","link": "http://book.naver.com/bookdb/book_detail.php?bid=14097515","image": "https://bookthumb-phinf.pstatic.net/cover/140/975/14097515.jpg?type=m1&udate=20181023","author": "조슈?�� 블로?��","price": "36000","discount": "32400","publisher": "?��?��?��?��","pubdate": "20181101","isbn": "8966262287 9788966262281","description": "?���? ?��?��?�� 모범 ?���? ?���? �??��?�� - <b>JAVA</b> 7, 8, 9 ???��?���? 6 출시 직후 출간?�� ?�이?��?���? ?���? 2?��?? ?��?���? ?��바는 커다?? �??���? 겪었?��. 그래?��... ?��?��?�� ???�� 추론\n-\t@SAFEVARARGS ?��?��?��?��?��\n-\tTRY-WITH-RESOURCES �?\n-\tOPTIONAL T ?��?��?��?��?��, <b>JAVA</b>.TIME, 컬렉?��?�� ?��?�� ?��?���? 메서?�� ?��?�� ?��로운 ?��?��브러�? 기능"},{"title": "<b>Java</b>?�� ?��?�� (최신 <b>Java</b> 8.0 ?��?��)","link": "http://book.naver.com/bookdb/book_detail.php?bid=10191151","image": "https://bookthumb-phinf.pstatic.net/cover/101/911/10191151.jpg?type=m1&udate=20190204","author": "?��궁성","price": "30000","discount": "27000","publisher": "?��?��출판","pubdate": "20160127","isbn": "8994492038 9788994492032","description": "?��바의 기초�??�� ?��?��?��?��까�? 모두 ?��?��!?��바의 기초�??�� 객제�??��개념?�� ?��?�� ?��?��?��?��까�? ?��록한??<b>JAVA</b>?�� ?��?��??. ???��?�� ?��?�� ?��무경?���? 강의?��... ?��불어 기존?�� 경력?��?��?�� ?��?�� ?���? 최신기능?�� ?��?��?? ?��?��림과 �? 밖의 ?��바의 최신버젼 <b>JAVA</b>8?�� ?��로운 기능까�? ?��?��?���? ?��명하�? ?��?��."},{"title": "?��것이 ?��바다 (?��?��권의 <b>Java</b> ?��로그?���? ?���?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=8589375","image": "https://bookthumb-phinf.pstatic.net/cover/085/893/08589375.jpg?type=m1&udate=20200516","author": "?��?���?","price": "30000","discount": "27000","publisher": "?��빛�?�디?��","pubdate": "20150105","isbn": "8968481474 9788968481475","description": "?�이것이 ?��바다?��? 15?�� ?��?�� ?���? ?��?���? 교육?��?�� ?���? ?��문강?��?�� ?��?��?���? ?��?�� ?��?�� ?��?��?�� ?���? ?��문서?��?��. ?���? ?��문자�? 배려?�� 친절?�� ?��명과 배려�? 1?��?�� ???��?�� ?���? 방법?�� ?��공하?�� ?���? ?��?��?��경을 구축?�� ?�� ?��?��. ?��?�� 중급 개발?���? ?��?���?�? ?��?�� ?��?��?��(14?��), JAVAFX(17?��), NIO(18... "},{"title": "First <b>Java</b>","link": "http://book.naver.com/bookdb/book_detail.php?bid=17936688","image": "https://bookthumb-phinf.pstatic.net/cover/179/366/17936688.jpg?type=m1&udate=20210209","author": "?��?���?|?��?��?��","price": "30000","discount": "27000","publisher": "?��?��?��?��?��","pubdate": "20210129","isbn": "1188831739 9791188831739","description": "?��?�� �??��?��?��?�� 공공 �? 기업?�� ?��?��?��?��?�� ??�?분들?�� ?���? 기반?�� ?�� ?��?��리�??��?�� ?��로젝?���? 진행?�� ?���? ?��?��. ?��?�� ?��마트 ?��바이?���? 보급?�� ?���? 보편?�� ?��면서 ?��?��로이?�� ?��?��체제 기반?�� ?��마트 ?��바이?��?�� ???�� �??��?�� 많아�?�? ?�� ?��바이?��?��?�� ?��?��?�� �??��?�� ?��?��리�??��?��?�� ???�� ?��구들?�� 많이... "},{"title": "?��?��?��?�� ?��?�� <b>Java</b> ?��로그?���?","link": "http://book.naver.com/bookdb/book_detail.php?bid=12236206","image": "https://bookthumb-phinf.pstatic.net/cover/122/362/12236206.jpg?type=m1&udate=20181129","author": "?��?��?��","price": "30000","discount": "27000","publisher": "?��?���?미디?��","pubdate": "20170705","isbn": "8996094072 9788996094074","description": "?�윤?��?��?�� ?��?�� <b>JAVA</b> ?��로그?��밍�?��? 최신 ?��?��?�� 바탕?���? ?���?�? 집필?�� ?��바책?��?��. ?���? 8?�� 발표?��면서 초보?��?��?���? ?��?��?�� ?�� ?��?�� 문법?�� ?��?���? ?��?��?�� ?��?��?�� ?��?��?��?�� ???�� ?��?��?�� ?���? 명확?�� ?��명을 ?��?��?�� ?��?���? ?���? ?��?�� ?��?��?��?�� ?��명한 책이?��. 본서?�� ?��명에?�� ?��?��?��?�� 고려?�� 과하�?... "},{"title": "초보?���? ?��?�� JavaScript 200?��","link": "http://book.naver.com/bookdb/book_detail.php?bid=14602049","image": "https://bookthumb-phinf.pstatic.net/cover/146/020/14602049.jpg?type=m1&udate=20190918","author": "고재?��|?���??��","price": "25000","discount": "22500","publisher": "?��보문?��?��","pubdate": "20190310","isbn": "8956748241 9788956748245","description": "JAVASCRIPT�? 무엇?��?�� ?��?�� ?�� ?��?��!\n\n�? 빠르�? 진화?��면서 ?��계에?�� �??�� ?���? ?��?�� ?��?���? ?�� ?��바스?��립트�? ?��치�??�� ?��?��까�? ?��?��별로 친절?���? ?��?��?��?�� 책이?��. 본서?�� �? 5개의 ?��?��(?���?, 초급, 중급, ?��?��, ?���?)�? 구성?��?�� ?��?���? 200개의 ?��?���? ?��?��?��면서 ?��바스?��립트�? ?��?�� ?�� ?��?��.... "},{"title": "명품 <b>JAVA</b> Programming (�?�? 배우?�� ?��바�? ?��?��?��, ?��?���? 몸으�? 배우?�� ?��바강�?)","link": "http://book.naver.com/bookdb/book_detail.php?bid=13650995","image": "https://bookthumb-phinf.pstatic.net/cover/136/509/13650995.jpg?type=m1&udate=20200408","author": "?��기태|�??��?��","price": "33000","discount": "31350","publisher": "?��?��출판?��","pubdate": "20180601","isbn": "897050947X 9788970509471","description": "?���?(<b>JAVA</b>)?�� �? ?��?�� ?��???�� ?��?��?�� ?��로그?���? ?��?��?��?�� ?�� 차원 진화?�� 개념?���? 개발?�� �??�� ?��명적... <b>JAVA</b> 8, 9?��?�� ?��?��?��?��?�� ?��?���? 바�?�었�? ?��문에 5?�� ?��?��?��?��?�� �?분을 ?��?��?��???��.\n3. 6.7?�� WRAPPER ?��?��?�� �?분을 갱신?��???��. <b>JAVA</b> 9�??�� ?��?��?���? ?��?��?��?�� WRAPPER 객체�? ?��?��?��?�� 방법?��... "},{"title": "OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests","link": "http://book.naver.com/bookdb/book_detail.php?bid=17941496","image": "https://bookthumb-phinf.pstatic.net/cover/179/414/17941496.jpg?type=m1&udate=20210204","author": "Scott Selikoff","price": "27090","discount": "","publisher": "Sybex","pubdate": "20210201","isbn": "1119696143 9781119696148","description": "NOTE: The OCP <b>Java</b> SE 11 Programmer I Exam 1Z0-815 and Programmer II Exam 1Z0-816 have been... Improve your preparation for the OCP <b>Java</b> SE 11 Developer exam with these comprehensive practice tests  OCP Oracle Certified Professional <b>Java</b> SE 11 Developer Practice Tests: Exam... "},{"title": "초보?���? ?��?�� <b>Java</b> 200?��","link": "http://book.naver.com/bookdb/book_detail.php?bid=13746617","image": "https://bookthumb-phinf.pstatic.net/cover/137/466/13746617.jpg?type=m1&udate=20180705","author": "조효??","price": "28000","discount": "25200","publisher": "?��보문?��?��","pubdate": "20180705","isbn": "8956747857 9788956747859","description": "?��?��?��?�� <b>Java</b> ?��?�� 200개�?? ?��?��?��?�� ?��록하???��?��?��. 빌보?�� 차트 만들�?, 카드 게임 만들�?, ?��?�� �?�?(HRM)?�� ?��?��리�??��?�� 만들�?, 채팅 만들�? ?��?�� ?��?���? ?��?�� ?��?��?��?���? <b>Java</b> ?��로그?��밍에 ?��?��?���?�? ?��미�?? ?��?�� ?�� ?��?���? 집필?��???���?, ?��?���? ?��?��?��?�� ?��?��?��?�� 보면 <b>Java</b> ?��로그?���?... "
		}
	 ]
}
*/
