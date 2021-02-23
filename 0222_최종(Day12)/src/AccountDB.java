import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
			//query : set autocommit = false;
			con.setAutoCommit(false);
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
			//Account 테이블에 계좌정보 저장
			String query = "insert into account(accid,name) " + 
					   "values("+ id +",'" + name +"');";
			ExcuteUpdate(query);
			
			//AccountIO 테이블에 계좌정보 저장
			query 
			= String.format("insert into accountio(accnum,input,output,balance)	" +				
			"values( %d, %d, %d, (select balance from account where accid=%d));",
			 id, 0,0,id);
			
			ExcuteUpdate(query);	
			
			con.commit();			//<--------------------------------
			
			return true;
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
	
	public boolean Insert(int id, String name, int balance) {
		
		try {	
			String query = 
					String.format("insert into account(accid,name,balance) values(%d,'%s',%d);",
							id, name, balance);
			ExcuteUpdate(query);
			
			//============================================================
			//AccountIO 테이블에 계좌정보 저장
			query 
			= String.format("insert into accountio(accnum,input,output,balance)	" +				
			"values( %d, %d, %d, (select balance from account where accid=%d));",
			 id, balance,0,id);
			
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
			//============ Account 계좌에 대한 Update =============================
			if( isInput == true) 
			query = String.format("update account set balance= balance+ %d where accid=%d;",
							money, id);
			else
				query = String.format("update account set balance= balance- %d where accid=%d;",
						money, id);
			
			ExcuteUpdate(query);
			//===============================================================
			
			//AccountIO 계좌에 대한 Update=========================================
			if( isInput == true) //입금
				query 
				= String.format("insert into accountio(accnum,input,output,balance)	" +				
						"values( %d, %d, %d, (select balance from account where accid=%d));",
						id, money,0,id);
			else   //출금
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
			//---------------- 자식 테이블의 수정 처리 먼저 하고
			String query 
			= String.format("delete from accountio where accnum= %d;", id);
			ExcuteUpdate(query);
			
			//----------------- 부모 테이블의 수정 처리를 해야!!
			query 
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
	public boolean Select(int id, Account acc, ArrayList<AccountIO> accio) {	
		try {
			String query 
			= String.format("select * from account where accid= %d;", id);
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			int accid = rs.getInt(1);
			String name = rs.getString(2);
			int balance = rs.getInt(3);
			Date ndate = rs.getDate(4);
			acc.setData(accid, name, balance, ndate);
			
			//----------------------------------------------------------
			//2번째 쿼리문 Accountio...........
			//while문을 돌리면서 AccountIO객체를 생성 --> accio.add( 추가 );\
			query 
			= String.format("select * from accountio where accnum= %d;", id);
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				int accnum = rs.getInt(2);	//accnum
				int input = rs.getInt(3);//input
				int output = rs.getInt(4); // output
				int balance1 = rs.getInt(5); // balance
				Date ndate1 = rs.getDate(6);  //거래일시.... 
				accio.add(new AccountIO(accnum, input, output, balance1, ndate1));
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
	
	public boolean IdCheck(int id) {
		try {
			String query = String.format("select * from account where accid= %d;", id);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				return true;
			}
		}
		catch(Exception ex) {
			return false;
		}
		return false;
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










