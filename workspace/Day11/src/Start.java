import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Start {


	public static void exam1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception ex) {
			System.out.println("오류 : " + ex.getMessage());
		}
	}

	public static void exam2() {
		Connection conn;
		try {
			//드라이버클래스://호스트주소:포트/데이터베이스명""아이디""패스워드"
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB","root","ssong");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?serverTimezone=UTC","root","ssong");
			System.out.println("데이터 베이스 연결 성공");
		
			//-----------------------------------------------
			//2. 명령객체(쿼리문을 가지고 DBMS명령을 내리는 객체)
			//connection 객체가 연결에 성공하면 명령객체를 생성한다
			Statement st = conn.createStatement();
			
			//3. 만약 DB에서 정보획득이 필요하다면 정보를 담을 수 있는 객체
			ResultSet rs = null;
			//-----------------------------------------------
			
			//방법1
			/*
			if(st.execute("Show databases")) {
				rs=st.getResultSet();	//하나의 row데이터를 가져온다.			
			}
			*/
			//방법2
			rs=st.executeQuery("Show databases");
			
			while(rs.next()) {
				String str=rs.getNString(1);
				System.out.println(str);
			}
			
			
			conn.close();
			System.out.println("데이터베이스 연결 해제");
		
		}catch(Exception ex) {
			System.out.println("에러 : "+ ex.getMessage());
		}
		
		
	}
	public static void main(String[] args) {
		exam2();
	}


}
