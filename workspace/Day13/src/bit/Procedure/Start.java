package bit.Procedure;

public class Start {

	public static void exam1() {
		AccountDB1 db = new AccountDB1();
		db.Run();
		
		//db.MakeAccount(10, "ccm", 1000);
		Account acc = db.SelectAccount(111);
		acc.Println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exam1();
	}

}
