import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Start {

	public static void main(String[] args) {
		
		try{
			AccountDB acc = new AccountDB();
			//acc.Insert(10, "홍길동");
			//acc.Insert(20, "이길동",1000);
			
			//acc.Update(10, true, 1000);
			//acc.Update(10, false, 200);		
			//acc.Update(20, true, 1000);
			//acc.Update(20, true, 2000);
			//10 : 800, 20 : 4000

			//acc.Delete(10);
			
			ArrayList<AccountIO> accio = new ArrayList<AccountIO>();
			//acc.Select(20,accio);
			Account ac = acc.Select(20,accio);//전체출력			
			ac.Print();
			
			System.out.println("------------------------------------");
			for(AccountIO acio : accio) {
				acio.Print();
			}
			
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}


}
