package bit.Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class AccountDB1 {
	private Connection con = null;
	
	//������
	public AccountDB1()  {		
	}
	
	//DB����(Connection ��ü ����)
	public boolean Run() {
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");	//"com.mysql.jdbc.Driver"		
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB?serverTimezone=UTC","root","1234");
			con.setAutoCommit(false);	
			System.out.println("������ ���̽� ���� ����");
			return true;
			
		} catch (Exception e) {
			System.out.println("[�����ͺ��̽� �ʱ�ȭ ����] " + e.getMessage());
			return false;
		}
	}
	
	//���
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






















