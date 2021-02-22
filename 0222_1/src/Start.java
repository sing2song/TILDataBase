
public class Start {
	public static void main(String[] args) {
		try {
			AccountDB acc = new AccountDB();
			//acc.Insert(10, "홍길동");
			//acc.Insert(20, "이길동", 1000);

			//acc.Update(10, true, 1000);
			//acc.Update(10, false, 200);		
			//acc.Update(20, true, 1000);
			//acc.Update(20, true, 2000);
			//10 : 800, 20 : 4000

			//acc.Select(20);
			acc.Select();

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}
}
