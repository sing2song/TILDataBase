import java.util.ArrayList;

//bank.java


public class Bank {
	//Database저장
	private AccountDB  db;
	
	//생성자
	public Bank() {
		try {
			db = new AccountDB();
		}
		catch(Exception ex) {
			System.out.println("데이터 베이스 접속 에러");			
		}
	}
	
	//메서드	
	public void MakeAccount() {
		try {
			//1. 요청정보(client)
			int number = BitGlobal.InputNumber("계좌번호");
			if(IsAccNumberCheck(number)==true)
				throw new Exception("중복된 계좌번호가 존재합니다.");
			
			String name = BitGlobal.InputString("이름");
			int money = BitGlobal.InputNumber("입금액");
			//----------------------------------------------------------------
			
			//사용자와 연결된 프로그램(Client) <-------------> 저장관리 프로그램(Server)
			
			//-----------------------------------------------------------------
			//2. 저장.(Server)
			if(db.Insert(number,  name, money)==false)
				throw new Exception("");
	
			//3. 결과.(client)
			System.out.println("저장 성공!");
		}
		catch(Exception ex) {
			System.out.println("[저장실패] " + ex.getMessage());
		}
	}
	
	private boolean IsAccNumberCheck(int number) {
		return db.IdCheck(number);
	}
		
	//계좌 전체 정보 출력!
	public void PrintAll() {
		//1. client(저장정보 요청)
		
		//2. server(정보를 획득)
		ArrayList<Account> accounts = db.Select();
		
		//3. client(결과 처리)
		System.out.println("[저장개수] " + accounts.size() + "개");
		for(Account ac : accounts) {
			ac.Print();
		}
		System.out.println();
	}

	public void SelectAccount() {
		//1. client
		int number = BitGlobal.InputNumber("계좌번호 입력");
		
		//2. server
		Account acc = new Account(0, "");	//?????
		ArrayList<AccountIO> acciolist = new ArrayList<AccountIO>();		
		if(db.Select(number, acc, acciolist) == false) {
			System.out.println("데이터가 존재하지 않습니다");
			return;
		}
		
		//3. Client
		acc.Println();
		System.out.println("------------------------------------------");
		for(AccountIO aio : acciolist) {
			aio.Print();
		}
		System.out.println();
	}	
			
	public void InputMoney() {
		try {
			//1. client
			int accnum = BitGlobal.InputNumber("계좌번호 입력");
			int money = BitGlobal.InputNumber("입금액 입력");
			
			//2. server 
			boolean result = db.Update(accnum,  true, money);
			
			//3. client
			if(result == false)
				throw new Exception("");
			else			
				System.out.println(money + "원이 입금되었습니다.");
		}
		catch(Exception ex) {
			System.out.println("[입금오류]" + ex.getMessage());
		}
	}
	
		
	public void OutputMoney() {
		try {
			int accnum = BitGlobal.InputNumber("계좌번호 입력");
			int money = BitGlobal.InputNumber("출금액 입력");
			//2. server 
			boolean result = db.Update(accnum,  false, money);
			
			//3. client
			if(result == false)
				throw new Exception("");
			else			
				System.out.println(money + "원이 출금되었습니다.");
		}
		catch(Exception ex) {
			System.out.println("[출금오류]" + ex.getMessage());
		}
	}
	
	public void DeleteAccount() {			
		try {
			//1. client
			int accnum = BitGlobal.InputNumber("계좌번호 입력");
			
			//2.server	
			boolean isreturn = db.Delete(accnum);			
			
			//3. client
			if(isreturn ==true)
				System.out.println("삭제되었습니다.");
			else throw new Exception("");
		}
		catch(Exception ex) {
			System.out.println("[삭제 에러] " + ex.getMessage());
		}
	}
}









