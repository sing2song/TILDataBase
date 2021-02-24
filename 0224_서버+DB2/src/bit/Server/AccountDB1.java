package bit.Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class AccountDB1 {
	private Connection con = null;
	
	//생성자
	public AccountDB1()  {		
	}
	
	//DB연결(Connection 객체 생성)
	public boolean Run() {
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");	//"com.mysql.jdbc.Driver"		
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB?serverTimezone=UTC","root","1234");
			con.setAutoCommit(false);	
			System.out.println("데이터 베이스 연결 성공");
			return true;
			
		} catch (Exception e) {
			System.out.println("[데이터베이스 초기화 에러] " + e.getMessage());
			return false;
		}
	}
	
	//기능
	public boolean MakeAccount(int accnum, String name, int balance) {
		try {
			String Insert = "insert into account(accid,name, balance) values(?,?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);			
			sment.setInt(1,  accnum);
			sment.setString(2,  name);
			sment.setInt(3,  balance);
			int i = sment.executeUpdate();
			sment.close();   //<===================================
			if( i > 0) {
				con.commit();
				return true;
			}	
			return false;
		}
		catch(Exception ex) {
			return false;
		}
	}

	public Account SelectAccount(int accnum) {
		try {
			String sql = "select * from account where accid = ?;";
			PreparedStatement sment = con.prepareStatement(sql);	
			sment.setInt(1, accnum);
			//--------------------------------------------------------
			ResultSet rs = sment.executeQuery();
			rs.next();
			int accid = rs.getInt(1);
			String name = rs.getString(2);
			int balance = rs.getInt(3);
			Timestamp ntime = rs.getTimestamp(4);
			sment.close();
			
			Account acc = new Account(accid, name, balance, ntime);
			return acc;
		}
		catch(Exception ex) {
			return null;
		}		
	}
}






















