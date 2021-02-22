import java.util.ArrayList;

//데이터 및 기능관리
public class Bank {
	private BitArray arr;
	private BitArray acciolist = new BitArray(100);//계좌거래리스트 관리
	private AccountDB db = new AccountDB();
	
	
	//싱글톤 패턴 코드 -----------------------------------------------
	//생성자 은닉!
	private Bank() {
		arr = new BitArray(InputMax());
	}

	//자신의 static 객체 생성
	private static Bank Singleton = new Bank();

	//내부적으로 생성된 자신의 객체를 외부에 노출 메서드
	public static Bank getInstance() {
		return Singleton;
	}
	//------------------------------------------------------------	


	//메서드
	public int InputMax() {
		return BitGlobal.InputNumber("저장 개수");
	}


	//기능메서드
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


	//검색 알고리즘(순차 검색)
	//계좌번호를 중복체크
	private boolean IsAccNumberCheck(int accid) {
		for(int i=0;i<arr.getSize();i++) {
			Account acc = (Account)arr.getData(i);
			if(acc.getAccid()==accid) {
				//순차검색
				return false;
			}
		}
		return true;
	}

	//거래리스트 저장 함수
	private void InsertAccountIO(int number, int input, int output, int balance) {
		try {
			AccountIO io = new AccountIO(number, input, output, balance);
			acciolist.Insert(io);
		}
		catch(Exception ex) {
			System.out.println("[거래내역저장에러] " + ex.getMessage());
		}
	}

	//특정계좌의 거래리스트 개수 반환
	private int getAccountIOCount(int accnumber) {
		int sum = 0;
		for(int i=0; i< acciolist.getSize(); i++)	{
			AccountIO accio = (AccountIO)acciolist.getData(i); 
			if( accio.getAccnum() == accnumber)
				sum++;
		}
		return sum;
	}

	//거래리스트 출력 함수
	private void PrintAccountIO(int accnumber) {
		System.out.println("[거래개수] " + getAccountIOCount(accnumber) + "개");
		for(int i=0; i< acciolist.getSize(); i++)	{
			AccountIO accio = (AccountIO)acciolist.getData(i); 
			if( accio.getAccnum() == accnumber)
				accio.Print();
		}
	}



	public void MakeAccount() {
		try {
			//1.변수 선언[IN][OUT]
			//2.초기화 (IN변수는 초기화의 대상, OUT변수는 기본 초기화)
			int accid=BitGlobal.InputNumber("계좌번호");
			if(IsAccNumberCheck(accid)==false)
				throw new Exception("중복된 계좌번호가 존재합니다") ;

			String name=BitGlobal.InputString("이름");
			int balance=BitGlobal.InputNumber("입금액");
			//----------------------------------------------
			
			//사용자와 연결된 프로그램(client)<--->저장관리 프로그램(Server)
			//----------------------------------------------
			Account acc=new Account(accid,name,balance);
			arr.Insert(acc);//Insert함수가 예외를 갖고있으므로 여기도 try-catch

			//계좌 개설 성공----------------------------------
			//1. 계좌번호 : number
			//2. 입금 : money
			//3. 출금: 0
			//4. 잔액 : money
			//---------------------------------------------
			InsertAccountIO(accid,balance,0,balance);
			System.out.println("저장 성공!");

		}catch(Exception ex) {
			System.out.println("[저장 실패] "+ex.getMessage());
		}
	}

	private int NumberToIdx(int accid) {
		for(int i=0;i<arr.getSize();i++) {
			Account acc = (Account)arr.getData(i);
			if(acc.getAccid()==accid) {
				//순차검색
				return i;
			}
		}
		return -1;
	}

	public void SelectAccount() {
		//1. client
		int accid=BitGlobal.InputNumber("계좌번호");
		
		//2. server
		Account acc = new Account();
		ArrayList<AccountIO> acciolist = new ArrayList<AccountIO>();
		if(db.Select(accid,acc,acciolist)==false) {
			System.out.println("데이터가 존재하지 않습니다.");
			return;
		}			
		
		//3. client
		int idx=NumberToIdx(accid);
		if(idx!=-1) {
			Account acc= (Account)arr.getData(idx);
			acc.Println();

			PrintAccountIO(accid);
		}else
			System.out.println("없는 번호입니다.");
	}

	//회원이름 전달-> 회원을 반환(실패 : 예외 발생
	private Account NameToMember(int accid) throws Exception {
		for(int i=0;i<arr.getSize();i++) {
			Account acc = (Account)arr.getData(i);
			if(acc.getAccid() == accid) {//담고있는 string이 같은지 체크
				//순차검색
				return  acc;
			}
		}
		throw new Exception("업는 회원입니다");
	}

	public void InputMoney() {
		//1. 사용자에게 검색할 회원번호를 입력
		//2. 수정할 전화번호를 입력
		//3. 검색
		try {
			int accid=BitGlobal.InputNumber("계좌번호");
			int money=BitGlobal.InputNumber("입금할 금액");
			int idx=NumberToIdx(accid);
			if(idx==-1)
				throw new Exception("해당 계좌번호는 존재하지 않습니다");

			Account acc=NameToMember(accid);
			//검색하는 과정에서 throw를 발생시키므로 바로 성공패턴을 진행 - try catch로 예외처리하기
			//4. 검색 결과에 따라 전화번호를 수정!
			acc.InputMoney(money);

			//계좌 개설 성공----------------------------------
			//1. 계좌번호 : number
			//2. 입금 : money
			//3. 출금: 0
			//4. 잔액 : money
			//---------------------------------------------
			InsertAccountIO(accid,money,0,acc.getBalance());

			System.out.println("입금 성공");
		}catch(Exception ex) {
			System.out.println("[입금오류]"+ex.getMessage());
		}

	}

	public void OutputMoney() {
		//1. 사용자에게 검색할 회원번호를 입력
		//2. 수정할 전화번호를 입력
		//3. 검색
		try {
			int accid=BitGlobal.InputNumber("계좌번호");
			int money=BitGlobal.InputNumber("출금할 금액");
			int idx=NumberToIdx(accid);
			if(idx==-1)
				throw new Exception("해당 계좌번호는 존재하지 않습니다");

			Account acc=NameToMember(accid);
			//검색하는 과정에서 throw를 발생시키므로 바로 성공패턴을 진행 - try catch로 예외처리하기
			//4. 검색 결과에 따라 전화번호를 수정!
			acc.OutputMoney(money);
			//계좌 개설 성공----------------------------------
			//1. 계좌번호 : number
			//2. 입금 : 0
			//3. 출금: money
			//4. 잔액 : money
			//---------------------------------------------
			InsertAccountIO(accid,0,money,acc.getBalance());

			System.out.println(money+ "원 출금 성공");
		}catch(Exception ex) {
			System.out.println("[출금오류]"+ex.getMessage());
		}

	}

	public void DeleteAccount() {
		//사용자에게 회원번호 입력받아 검색 후 해당 회원을 삭제(BitArray.Delete함수를 활용)
		//전반적인 흐름은 Select와 유사하다.
		try {
			int accid=BitGlobal.InputNumber("계좌번호");
			int idx=NumberToIdx(accid);
			if(idx==-1)
				throw new Exception("없는 계좌번호 입니다.");

			arr.Delete(idx);
			System.out.println("삭제 되었습니다.");

		}catch(Exception ex) {
			System.out.println("[삭제 에러] "+ex.getMessage());
		}
	}
}
