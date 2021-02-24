package bit.Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
			String Insert = "insert into account(accid,name,balance) values(?,?,?);";
			PreparedStatement sment = con.prepareStatement(Insert);

			sment.setInt(1, accnum);
			sment.setString(2, name);
			sment.setInt(3, balance);
			sment.close();
			int i= sment.executeUpdate();
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
			String sql = "select * from account where accid = ?;";
			PreparedStatement sment = con.prepareStatement(sql);	
			sment.setInt(1, accnum);
			//--------------------------------------------------------
			ResultSet rs = sment.executeQuery();//������ sql�� �̹� �����!

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

	public boolean InputAccount(int accnum, int balance) {
		try {
			String sql = "update account set balance = balance + ? where accid=?;";
			PreparedStatement sment = con.prepareStatement(sql);			
			sment.setInt(1,  accnum);
			sment.setInt(2,  balance);
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

	public boolean OutputAccount(int accnum, int balance) {
		try {
			//�ܾ��� ������ ���?????
			String sql = "update account set balance = balance - ? where accid=? and balance >=?";
			PreparedStatement sment = con.prepareStatement(sql);			
			sment.setInt(1,  balance);
			sment.setInt(2,  accnum);
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

	public boolean DeleteAccount(int id) {
		try {
			String Delete = "delete from account where accid=?;";
			PreparedStatement sment = con.prepareStatement(Delete);

			sment.setInt(1, id);
			sment.close();
			int i= sment.executeUpdate();
			if(i>0) {
				con.commit();
				return true;
			}
			return false;

		} catch (Exception e) {
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










