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
			System.out.println("���� : " + ex.getMessage());
		}
	}

	public static void exam2() {
		Connection conn;
		try {
			//����̹�Ŭ����://ȣ��Ʈ�ּ�:��Ʈ/�����ͺ��̽���""���̵�""�н�����"
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampleDB","root","ssong");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?serverTimezone=UTC","root","ssong");
			System.out.println("������ ���̽� ���� ����");
		
			//-----------------------------------------------
			//2. ��ɰ�ü(�������� ������ DBMS����� ������ ��ü)
			//connection ��ü�� ���ῡ �����ϸ� ��ɰ�ü�� �����Ѵ�
			Statement st = conn.createStatement();
			
			//3. ���� DB���� ����ȹ���� �ʿ��ϴٸ� ������ ���� �� �ִ� ��ü
			ResultSet rs = null;
			//-----------------------------------------------
			
			//���1
			/*
			if(st.execute("Show databases")) {
				rs=st.getResultSet();	//�ϳ��� row�����͸� �����´�.			
			}
			*/
			//���2
			rs=st.executeQuery("Show databases");
			
			while(rs.next()) {
				String str=rs.getNString(1);
				System.out.println(str);
			}
			
			
			conn.close();
			System.out.println("�����ͺ��̽� ���� ����");
		
		}catch(Exception ex) {
			System.out.println("���� : "+ ex.getMessage());
		}
		
		
	}
	public static void main(String[] args) {
		exam2();
	}


}
