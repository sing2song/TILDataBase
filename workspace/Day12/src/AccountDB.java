import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

//DB���� Ŭ����
public class AccountDB {
	Connection conn = null;
	Statement stmt = null;

	public AccountDB() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� �ε� ����");
			//String dburl = "jdbc:mysql://localhost:3306?serverTimezone=UTC";
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB?serverTimezone=UTC","root","ssong");
			System.out.println("�����ͺ��̽� ���� ����");
			stmt = conn.createStatement();
			System.out.println("���ᰴü ȹ�� ����");	

		}catch(Exception e) {
			throw new Exception("�����ͺ��̽� ���� ����");

		}
	}

	//������ ���(DML)-------------------------------------
	public boolean Insert(int id, String name) {
		try {
			//Account ���̺� �������� ����
			String query = "insert into account(accid,name) " +
					"values("+ id +",'" + name +"');";
			ExcuteUpdate(query);

			//AccountIO ���̺� �������� ����
			query=String.format("insert into accountio(accnum,input,output,balance)"
					+ "values( %d, %d, %d, (select balance from account where accid=%d));",
					id,0,0,id);

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

			//AccountIO ���̺� �������� ����
			query=String.format("insert into accountio(accnum,input,output,balance)"
					+ "values( %d, %d, %d, (select balance from account where accid=%d));",
					id,balance,0,id);

			ExcuteUpdate(query);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	//�����
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


			//AccountIO ���¿� ���� Update=========================================
			if( isInput == true) 
				query 
				= String.format("insert into accountio(accnum,input,output,balance)	" +				
						"values( %d, %d, %d, (select balance from account where accid=%d));",
						id, money,0,id);
			else
				query 
				= String.format("insert into accountio(accnum,input,output,balance)	" +				
						"values( %d, %d, %d, (select balance from account where accid=%d));",
						id, 0,money,id);
			ExcuteUpdate(query);		
			//====================================================================

			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public boolean Delete(int id) {
		try {
			//�ڽ����̺��� ����ó�� ����
			String query
			= String.format("delete from accountio where accnum= %d;", id);
			ExcuteUpdate(query);

			//�θ����̺����ó��
			query
			= String.format("delete from account where accid= %d;", id);
			ExcuteUpdate(query);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	//������ ���(DQL)-----------------------------------------
	//select * from account;
	public boolean Select(int id,ArrayList<AccountIO> accio) {

		Account acc = null;
		try {
			String query
			= String.format("select * from account where accid= %d;", id);
			ResultSet rs = stmt.executeQuery(query);
			rs.next(); //while�� �ʿ��� rs.next()ȣ���� �� �ʿ��ϴ�
			int accid = rs.getInt(1);
			String name = rs.getString(2);
			int balance = rs.getInt(3);
			Date ndate = rs.getDate(4);
			//System.out.println(accid + "," + name + ","+balance + ","+ndate);

			acc = new Account(accid,name,balance,ndate);


			//------------------------------------------------
			//2���� ������ Accountio............
			//while���� �����鼭 AccountIO��ü�� ���� --> accio.add( �߰� );
			query
			= String.format("select * from accountio where accnum= %d;", id);
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				int accnum = rs.getInt(2);	//accnum	
				int input = rs.getInt(3);	//input
				int output = rs.getInt(4);	//output
				int balance2 = rs.getInt(5);	//balance
				Date ndate2 = rs.getDate(6);	//�ŷ��Ͻ�...
				//System.out.println(accid + "," + name + ","+balance + ","+ndate);

				accio.add(new AccountIO(accnum,input,output,balance2));
			}
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	public ArrayList<Account> Select() {
		try {
			ArrayList<Account> arr = new ArrayList<Account>();
			String query 
			= String.format("select * from account ;");
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int accid = rs.getInt(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				Date ndate = rs.getDate(4);				
				arr.add(new Account(accid, name, balance, ndate));
			}
			return arr;
		}
		catch(Exception ex) {
			return null;
		}
	}


	private void ExcuteUpdate(String query) throws Exception {
		try {
			int i = stmt.executeUpdate(query); //������ ��ģ row data����
			if( i <= 0)
				throw new Exception("����� �ο쵥���Ͱ� ����");
		}
		catch(Exception ex) {
			throw new Exception("���� - " + ex.getMessage());
		}
	}



}
