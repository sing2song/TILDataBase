import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class BitDB {
	Connection con = null;
	Statement stmt = null;

	public BitDB() throws Exception {
		try {			
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("����̹� �ε� ����");
			//"jdbc:mysql://localhost:3306?serverTimezone=UTC","root","1234");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB?serverTimezone=UTC","root","ssong");
			System.out.println("�����ͺ��̽� ���Ἲ��");
			stmt = con.createStatement();
			System.out.println("���ᰴü ȹ�� ����");
		} catch (Exception e) {
			throw new Exception("�����ͺ��̽� ���� ����");
		}		
	}	

	//������ ���(DML)----------------------------------------------------
	//1. ȸ�����
	public boolean InsertMember_DB(String memberId, String name) {		
		try {	
			//Account ���̺� �������� ����
			String query=String.format("insert into BIT_Member(memberid,name) values(\"%s\",\"%s\");",memberId,name);
			ExcuteUpdate(query);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	//2. ȸ����ü���
	public ArrayList<Member> SelectAllMember_DB() {
		try {
			ArrayList<Member> arr = new ArrayList<Member>();
			String query 
			= String.format("select * from BIT_Member;");
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String memberID = rs.getString(1);
				String name = rs.getString(2);
				String level = rs.getString(3);
				arr.add(new Member(memberID, name,level));
			}
			return arr;
		}
		catch(Exception ex) {
			return null;
		}
	}

	//3.��������
	public boolean InsertDrink_DB(String name, int price) {		
		try {	
			//Account ���̺� �������� ����
			String query=String.format("insert into BIT_Drink(name,price) values(\"%s\", %d);",name,price);
			ExcuteUpdate(query);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	//4. �������ü���
	public ArrayList<Drink> SelectAllDrink_DB() {
		try {
			ArrayList<Drink> arr = new ArrayList<Drink>();
			String query 
			= String.format("select * from BIT_Drink;");
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int drinkID = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int count = rs.getInt(4);
				arr.add(new Drink(drinkID, name,price,count));
			}
			return arr;
		}
		catch(Exception ex) {
			return null;
		}
	}

	//5. �����ü���
	public ArrayList<Rank> SelectAllRank_DB() {
		try {
			ArrayList<Rank> arr = new ArrayList<Rank>();
			String query 
			= String.format("select * from BIT_MemberLevel;");
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String levelname = rs.getString(1);
				int low = rs.getInt(2);
				int high = rs.getInt(3);
				arr.add(new Rank(levelname, low,high));
			}
			return arr;
		}
		catch(Exception ex) {
			return null;
		}
	}

	//6. �������̺���ü���
	public ArrayList<BuyDrink> SelectAllBuyDrink_DB() {
		try {
			ArrayList<BuyDrink> arr = new ArrayList<BuyDrink>();
			String query 
			= String.format("select * from BIT_BuyDrink order by memberid asc;");
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int buydrinkid = rs.getInt(1);
				String memberid = rs.getString(2);
				int drinkid = rs.getInt(2);
				int count = rs.getInt(3);
				int totalmoney = rs.getInt(4);
				arr.add(new BuyDrink(buydrinkid, memberid,drinkid,count,totalmoney));
			}
			return arr;
		}
		catch(Exception ex) {
			return null;
		}
	}

	//7. ����� ����
	public boolean Update(String memberId, int drinkId, int count) {
		try {	
			if(DrinkIDCheck(drinkId)==false)
				return false;

			int price = DrinkPrice(drinkId);
			String query;
			//============ BIT_BuyDrink �� ���� Update =============================
			//ȸ����ȣ, �������ȣ, ������ �Է� �޾� ���� ���̺� ����(totalmoney�� ���� �� ����)
			query = String.format("input into BIT_BuyDrink(memberId,drinkid,count,totalmoney) values (\"%s\",%d,%d,%d);",
					memberId, drinkId, count, price*count);

			ExcuteUpdate(query);
			//===============================================================

			//��������̺� ���� Update=========================================
			//����� ���̺��� �ش� ������ �Ǹ� ���� ����
			query = String.format("update BIT_Drink set count= count+ %d where drinkid=%d;",
					count, drinkId);

			ExcuteUpdate(query);			
			//====================================================================


			//ȸ�����̺��� ��� ����(�������̺��� ��������)
			//select count(totalmoney) from Bit_BuyDrink group by(memberid);
			//int totalmoney =countTM(memberId);
					
					
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}	
	}

	//8. ȸ�� �������� �˻�
	public boolean SelectBuyDrink_DB(String memberId,Member member, ArrayList<BuyDrink> buyDrink) {
		try {
			ArrayList<BuyDrink> arr = new ArrayList<BuyDrink>();
			String query; 
			//ȸ����ȣ, ȸ���̸�, ���
			query= String.format("select * from BIT_Member where memberId=%d;",memberId);
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			String name=rs.getString(2);
			String rank=rs.getString(3);
			member=new Member(memberId,name,rank);


			//19.������ ����� �� ����, ������ �� ��
			query= String.format("select * from BIT_BuyDrink where memberId=%d;",memberId);
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				int buydrinkid = rs.getInt(1);
				String memberid = rs.getString(2);
				int drinkid = rs.getInt(2);
				int count = rs.getInt(3);
				int totalmoney = rs.getInt(4);
				buyDrink.add(new BuyDrink(buydrinkid, memberid,drinkid,count,totalmoney));
			}
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	//9. ���� ���� �Ǹŵ� ����� ���� ���
	public Drink SelectBestSeller_DB() {
		try {
			Drink drink;
			String query 
			= String.format("select * from BIT_Drink order by count desc limit 1;");
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			int drinkID = rs.getInt(1);
			String name = rs.getString(2);
			int price = rs.getInt(3);
			int count = rs.getInt(4);
			drink=new Drink(drinkID, name,price,count);

			return drink;
		}
		catch(Exception ex) {
			return null;
		}
	}


	//--------------------------------------------------------------------


	//����� ����
	public int DrinkPrice(int drinkId) {
		try {
			String query 
			= String.format("select price from BIT_Drink where drinkId= %d;", drinkId);
			ResultSet rs = stmt.executeQuery(query);

			rs.next();
			int price = rs.getInt(1);
			return price;
		}		
		catch(Exception ex) {
			return 0;
		}
	}

	//���� �ѱ��ŷ�
	public int countTM(int memberId) {
		try {
			String query="select count(totalmoney) from Bit_BuyDrink group by("+memberId+")";
			ResultSet rs = stmt.executeQuery(query);

			rs.next();
			int total = rs.getInt(1);
			return total;
		}		
		catch(Exception ex) {
			return 0;
		}	
	}


	////helper//////
	public boolean MemberIDCheck(String memberId) {
		try {
			String query = String.format("select * from BIT_Member where memberId= /%s/;", memberId);
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

	public boolean DrinkIDCheck(int drinkId) {
		try {
			String query = String.format("select * from BIT_Drink where drinkId= %d;", drinkId);
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

	public boolean BuyDrinkIDCheck(int buyDrinkId) {
		try {
			String query = String.format("select * from BIT_BuyDrink where buydrinkId= %d;", buyDrinkId);
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
			int i = stmt.executeUpdate(query); //������ ��ģ row data����
			if( i <= 0)
				throw new Exception("����� �ο쵥���Ͱ� ����");
		}
		catch(Exception ex) {
			throw new Exception("���� - " + ex.getMessage());
		}
	}
}










