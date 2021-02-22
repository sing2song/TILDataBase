import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class AccountDB {
	Connection con = null;
	Statement stmt = null;

	public AccountDB() throws Exception {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩 성공");
			//"jdbc:mysql://localhost:3306?serverTimezone=UTC","root","1234");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB?serverTimezone=UTC","root","1234");
			System.out.println("데이터베이스 연결성공");
			stmt = con.createStatement();
			System.out.println("연결객체 획득 성공");
		} catch (Exception e) {
			throw new Exception("데이터베이스 연결 오류");
		}
	}


	//쿼리문 사용(DML)----------------------------------------------------
	public boolean Insert(int id, String name) {
		try {
			String query = "insert into account(accid,name) " +
					"values("+ id +",'" + name +"');";
			ExcuteUpdate(query);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean Insert(int id, String name, int balance) {

		try {
			String query =
					String.format("insert into account(accid,name,balance) values(%d,'%s',%d);",
							id, name, balance);
			ExcuteUpdate(query);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean Update(int id, boolean isInput, int money) {
		try {
			String query;
			if( isInput == true)
				query = String.format("update account set balance= balance+ %d where accid=%d;",
						money, id);
			else
				query = String.format("update account set balance= balance- %d where accid=%d;",
						money, id);

			ExcuteUpdate(query);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean Delete(int id) {
		try {
			String query
					= String.format("delete from account where accid= %d;", id);
			ExcuteUpdate(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	//--------------------------------------------------------------------

	//쿼리문 사용(DQL)
	//select * from account where id = 10;
	public void Select(int id) {
		try {
			String query
					= String.format("select * from account where accid= %d;", id);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int accid = rs.getInt(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				Date ndate = rs.getDate(4);
				System.out.println(accid + "," + name + ","+balance + ","+ndate);
			}
		}
		catch(Exception ex) {

		}
	}

	public void Select() {
		try {
			String query
					= String.format("select * from account ;");
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int accid = rs.getInt(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				Date ndate = rs.getDate(4);
				System.out.println(accid + "," + name + ","+balance + ","+ndate);
			}
		}
		catch(Exception ex) {

		}
	}

	private void ExcuteUpdate(String query) throws Exception {
		try {
			int i = stmt.executeUpdate(query); //영향을 미친 row data갯수
			if( i <= 0)
				throw new Exception("변경된 로우데이터가 없음");
		}
		catch(Exception ex) {
			throw new Exception("에러 - " + ex.getMessage());
		}
	}
}










