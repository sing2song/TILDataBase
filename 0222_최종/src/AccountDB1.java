import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AccountDB1 {
	Connection con = null;
	
	//�̸� �������� ������ �̸� ����...
	PreparedStatement stmt_insert = null;
	
	public AccountDB1() throws Exception {
		try {			
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� �ε� ����");
			//"jdbc:mysql://localhost:3306?serverTimezone=UTC","root","1234");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB?serverTimezone=UTC","root","1234");
			con.setAutoCommit(false);
			System.out.println("�����ͺ��̽� ���Ἲ��");
			InitStatement();		//<=================================
			
		} catch (Exception e) {
			throw new Exception("�����ͺ��̽� ���� ����");
		}		
	}
	
	private void InitStatement() {
		BitGlobalStatement.Init(con);
	}
	
	//���
	public boolean Insert(int id, String name) {		
		try {	
			BitGlobalStatement.state_Insert.setInt(1,  id);
			BitGlobalStatement.state_Insert.setString(2,  name);
			int i = BitGlobalStatement.state_Insert.executeUpdate();
			if( i > 0) {
				con.commit();
				return true;
			}
			else       
				throw new Exception("");	
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			try {
			con.rollback();			//<=-------------------------------
			}
			catch(Exception ex1) {}
			return false;
		}
	}
}











