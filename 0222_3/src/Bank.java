import java.util.ArrayList;

//bank.java


public class Bank {
	//Database����
	private AccountDB  db;
	
	//������
	public Bank() {
		try {
			db = new AccountDB();
		}
		catch(Exception ex) {
			System.out.println("������ ���̽� ���� ����");			
		}
	}
	
	//�޼���	
	public void MakeAccount() {
		try {
			//1. ��û����(client)
			int number = BitGlobal.InputNumber("���¹�ȣ");
			if(IsAccNumberCheck(number)==true)
				throw new Exception("�ߺ��� ���¹�ȣ�� �����մϴ�.");
			
			String name = BitGlobal.InputString("�̸�");
			int money = BitGlobal.InputNumber("�Աݾ�");
			//----------------------------------------------------------------
			
			//����ڿ� ����� ���α׷�(Client) <-------------> ������� ���α׷�(Server)
			
			//-----------------------------------------------------------------
			//2. ����.(Server)
			if(db.Insert(number,  name, money)==false)
				throw new Exception("");
	
			//3. ���.(client)
			System.out.println("���� ����!");
		}
		catch(Exception ex) {
			System.out.println("[�������] " + ex.getMessage());
		}
	}
	
	private boolean IsAccNumberCheck(int number) {
		return db.IdCheck(number);
	}
		
	//���� ��ü ���� ���!
	public void PrintAll() {
		//1. client(�������� ��û)
		
		//2. server(������ ȹ��)
		ArrayList<Account> accounts = db.Select();
		
		//3. client(��� ó��)
		System.out.println("[���尳��] " + accounts.size() + "��");
		for(Account ac : accounts) {
			ac.Print();
		}
		System.out.println();
	}

	public void SelectAccount() {
		//1. client
		int number = BitGlobal.InputNumber("���¹�ȣ �Է�");
		
		//2. server
		Account acc = new Account(0, "");	//?????
		ArrayList<AccountIO> acciolist = new ArrayList<AccountIO>();		
		if(db.Select(number, acc, acciolist) == false) {
			System.out.println("�����Ͱ� �������� �ʽ��ϴ�");
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
			int accnum = BitGlobal.InputNumber("���¹�ȣ �Է�");
			int money = BitGlobal.InputNumber("�Աݾ� �Է�");
			
			//2. server 
			boolean result = db.Update(accnum,  true, money);
			
			//3. client
			if(result == false)
				throw new Exception("");
			else			
				System.out.println(money + "���� �ԱݵǾ����ϴ�.");
		}
		catch(Exception ex) {
			System.out.println("[�Աݿ���]" + ex.getMessage());
		}
	}
	
		
	public void OutputMoney() {
		try {
			int accnum = BitGlobal.InputNumber("���¹�ȣ �Է�");
			int money = BitGlobal.InputNumber("��ݾ� �Է�");
			int idx = NumberToIdx(accnum);
			if(idx == -1)
				throw new Exception("�ش� ���¹�ȣ�� �������� �ʽ��ϴ�.");
			
			Account acc = (Account)arr.getData(idx);
			acc.OutputMoney(money);
			
			//-------------- ��� ����------------------------
			//1. ���¹�ȣ : accnum
			//2. �Ա� : 0
			//3. ��� : money
			//4. �ܾ� : acc.getBalance()
			//--------------------------------------------------
			InsertAccountIO(accnum, 0, money, acc.getBalance());
			
			System.out.println(money + "���� ��ݵǾ����ϴ�.");
		}
		catch(Exception ex) {
			System.out.println("[��ݿ���]" + ex.getMessage());
		}
	}
	
	public void DeleteAccount() {			
		try {
			int accnum = BitGlobal.InputNumber("���¹�ȣ �Է�");
			int idx = NumberToIdx(accnum);
			if(idx == -1) 
				throw new Exception("���� ���¹�ȣ �Դϴ�.");	//<=======
			
			arr.Delete(idx);
			System.out.println("�����Ǿ����ϴ�.");
		}
		catch(Exception ex) {
			System.out.println("[���� ����] " + ex.getMessage());
		}
	}
}









