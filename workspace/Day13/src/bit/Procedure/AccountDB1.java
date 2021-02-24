package bit.Procedure;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AccountDB1 {
	Connection con = null;
	//�̸� �������� ������ �̸� ����
	PreparedStatement stmt_insert = null;
	//������
	public AccountDB1(){		
	}	

	public boolean Run()  {
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� �ε� ����");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB?serverTimezone=UTC","root","ssong");
			con.setAutoCommit(false);
			System.out.println("������ ���̽� ���� ����");
			return true;
		} catch (Exception e) {
			System.out.println("[�����ͺ��̽� �ʱ�ȭ ����] "+e.getMessage());
			return false;
		}		
	}

	//���
	public boolean MakeAccount(int accnum,String name, int balance) {
		try {
			String query = "{call MakeAccount(?,?,?)};";
			PreparedStatement sment = con.prepareStatement(query);

			sment.setInt(1, accnum);
			sment.setString(2, name);
			sment.setInt(3, balance);
			int i= sment.executeUpdate();
			sment.close();


			if(i>0) {
				con.commit();
				return true;
			}
			return false;

		} catch (Exception e) {
			return false;
		}		
	}

	public Account SelectAccount(int accnum) {
		try {
			String sql = "{call SelectAccount(?,?,?,?)};";
			CallableStatement sment = con.prepareCall(sql);	
			sment.setInt(1, accnum);
			sment.registerOutParameter(2, Types.VARCHAR);
			sment.registerOutParameter(3,  Types.INTEGER);
			sment.registerOutParameter(4,  Types.TIMESTAMP);
			//--------------------------------------------------------
			sment.execute();			
	
			String name = sment.getString(2);
			int balance = sment.getInt(3);
			Timestamp ntime = sment.getTimestamp(4, Calendar.getInstance());

			sment.close();
			
			Account acc = new Account(accnum, name, balance, ntime);
			return acc;
		}
		catch(Exception ex) {
			System.out.println("���� �߻�");
			return null;
		}		
	}


	public boolean InputAccount(int accnum, int balance) {
		try {
			String sql = "{call InputAccount(?,?)};";
			CallableStatement sment = con.prepareCall(sql);	
			sment.setInt(1,  accnum);
			sment.setInt(2,  balance);
			sment.execute();
			sment.close();   //<===================================
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	public boolean OutputAccount(int accnum, int balance) {
		try {
			String sql = "{call OutputAccount(?,?)};";
			CallableStatement sment = con.prepareCall(sql);			
			sment.setInt(1,  accnum);
			sment.setInt(2,  balance);
			sment.execute();
			sment.close();   //<===================================			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}


	public boolean DeleteAccount(int accnum) {		//3
		try {
			String sql = "{call DeleteAccount(?)};";
			CallableStatement sment = con.prepareCall(sql);			
			sment.setInt(1,  accnum);
			sment.execute();
			sment.close();   //<===================================			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}



	public ArrayList<Account> selectAllAccount() {
		try {
			String sql = "select * from account";
			PreparedStatement sment = con.prepareStatement(sql);	
			//--------------------------------------------------------
			ArrayList<Account> acclist = new ArrayList<Account>();
			ResultSet rs = sment.executeQuery();//������ sql�� �̹� �����!

			while(rs.next()) {
				int accid = rs.getInt(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				Timestamp ntime = rs.getTimestamp(4);
				acclist.add(new Account(accid,name,balance,ntime));
			}
			sment.close();

			return acclist;
		}
		catch(Exception ex) {
			return null;
		}		
	}


}










