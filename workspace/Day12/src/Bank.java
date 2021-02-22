import java.util.ArrayList;

//������ �� ��ɰ���
public class Bank {
	private BitArray arr;
	private BitArray acciolist = new BitArray(100);//���°ŷ�����Ʈ ����
	private AccountDB db = new AccountDB();
	
	
	//�̱��� ���� �ڵ� -----------------------------------------------
	//������ ����!
	private Bank() {
		arr = new BitArray(InputMax());
	}

	//�ڽ��� static ��ü ����
	private static Bank Singleton = new Bank();

	//���������� ������ �ڽ��� ��ü�� �ܺο� ���� �޼���
	public static Bank getInstance() {
		return Singleton;
	}
	//------------------------------------------------------------	


	//�޼���
	public int InputMax() {
		return BitGlobal.InputNumber("���� ����");
	}


	//��ɸ޼���
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


	//�˻� �˰���(���� �˻�)
	//���¹�ȣ�� �ߺ�üũ
	private boolean IsAccNumberCheck(int accid) {
		for(int i=0;i<arr.getSize();i++) {
			Account acc = (Account)arr.getData(i);
			if(acc.getAccid()==accid) {
				//�����˻�
				return false;
			}
		}
		return true;
	}

	//�ŷ�����Ʈ ���� �Լ�
	private void InsertAccountIO(int number, int input, int output, int balance) {
		try {
			AccountIO io = new AccountIO(number, input, output, balance);
			acciolist.Insert(io);
		}
		catch(Exception ex) {
			System.out.println("[�ŷ��������忡��] " + ex.getMessage());
		}
	}

	//Ư�������� �ŷ�����Ʈ ���� ��ȯ
	private int getAccountIOCount(int accnumber) {
		int sum = 0;
		for(int i=0; i< acciolist.getSize(); i++)	{
			AccountIO accio = (AccountIO)acciolist.getData(i); 
			if( accio.getAccnum() == accnumber)
				sum++;
		}
		return sum;
	}

	//�ŷ�����Ʈ ��� �Լ�
	private void PrintAccountIO(int accnumber) {
		System.out.println("[�ŷ�����] " + getAccountIOCount(accnumber) + "��");
		for(int i=0; i< acciolist.getSize(); i++)	{
			AccountIO accio = (AccountIO)acciolist.getData(i); 
			if( accio.getAccnum() == accnumber)
				accio.Print();
		}
	}



	public void MakeAccount() {
		try {
			//1.���� ����[IN][OUT]
			//2.�ʱ�ȭ (IN������ �ʱ�ȭ�� ���, OUT������ �⺻ �ʱ�ȭ)
			int accid=BitGlobal.InputNumber("���¹�ȣ");
			if(IsAccNumberCheck(accid)==false)
				throw new Exception("�ߺ��� ���¹�ȣ�� �����մϴ�") ;

			String name=BitGlobal.InputString("�̸�");
			int balance=BitGlobal.InputNumber("�Աݾ�");
			//----------------------------------------------
			
			//����ڿ� ����� ���α׷�(client)<--->������� ���α׷�(Server)
			//----------------------------------------------
			Account acc=new Account(accid,name,balance);
			arr.Insert(acc);//Insert�Լ��� ���ܸ� ���������Ƿ� ���⵵ try-catch

			//���� ���� ����----------------------------------
			//1. ���¹�ȣ : number
			//2. �Ա� : money
			//3. ���: 0
			//4. �ܾ� : money
			//---------------------------------------------
			InsertAccountIO(accid,balance,0,balance);
			System.out.println("���� ����!");

		}catch(Exception ex) {
			System.out.println("[���� ����] "+ex.getMessage());
		}
	}

	private int NumberToIdx(int accid) {
		for(int i=0;i<arr.getSize();i++) {
			Account acc = (Account)arr.getData(i);
			if(acc.getAccid()==accid) {
				//�����˻�
				return i;
			}
		}
		return -1;
	}

	public void SelectAccount() {
		//1. client
		int accid=BitGlobal.InputNumber("���¹�ȣ");
		
		//2. server
		Account acc = new Account();
		ArrayList<AccountIO> acciolist = new ArrayList<AccountIO>();
		if(db.Select(accid,acc,acciolist)==false) {
			System.out.println("�����Ͱ� �������� �ʽ��ϴ�.");
			return;
		}			
		
		//3. client
		int idx=NumberToIdx(accid);
		if(idx!=-1) {
			Account acc= (Account)arr.getData(idx);
			acc.Println();

			PrintAccountIO(accid);
		}else
			System.out.println("���� ��ȣ�Դϴ�.");
	}

	//ȸ���̸� ����-> ȸ���� ��ȯ(���� : ���� �߻�
	private Account NameToMember(int accid) throws Exception {
		for(int i=0;i<arr.getSize();i++) {
			Account acc = (Account)arr.getData(i);
			if(acc.getAccid() == accid) {//����ִ� string�� ������ üũ
				//�����˻�
				return  acc;
			}
		}
		throw new Exception("���� ȸ���Դϴ�");
	}

	public void InputMoney() {
		//1. ����ڿ��� �˻��� ȸ����ȣ�� �Է�
		//2. ������ ��ȭ��ȣ�� �Է�
		//3. �˻�
		try {
			int accid=BitGlobal.InputNumber("���¹�ȣ");
			int money=BitGlobal.InputNumber("�Ա��� �ݾ�");
			int idx=NumberToIdx(accid);
			if(idx==-1)
				throw new Exception("�ش� ���¹�ȣ�� �������� �ʽ��ϴ�");

			Account acc=NameToMember(accid);
			//�˻��ϴ� �������� throw�� �߻���Ű�Ƿ� �ٷ� ���������� ���� - try catch�� ����ó���ϱ�
			//4. �˻� ����� ���� ��ȭ��ȣ�� ����!
			acc.InputMoney(money);

			//���� ���� ����----------------------------------
			//1. ���¹�ȣ : number
			//2. �Ա� : money
			//3. ���: 0
			//4. �ܾ� : money
			//---------------------------------------------
			InsertAccountIO(accid,money,0,acc.getBalance());

			System.out.println("�Ա� ����");
		}catch(Exception ex) {
			System.out.println("[�Աݿ���]"+ex.getMessage());
		}

	}

	public void OutputMoney() {
		//1. ����ڿ��� �˻��� ȸ����ȣ�� �Է�
		//2. ������ ��ȭ��ȣ�� �Է�
		//3. �˻�
		try {
			int accid=BitGlobal.InputNumber("���¹�ȣ");
			int money=BitGlobal.InputNumber("����� �ݾ�");
			int idx=NumberToIdx(accid);
			if(idx==-1)
				throw new Exception("�ش� ���¹�ȣ�� �������� �ʽ��ϴ�");

			Account acc=NameToMember(accid);
			//�˻��ϴ� �������� throw�� �߻���Ű�Ƿ� �ٷ� ���������� ���� - try catch�� ����ó���ϱ�
			//4. �˻� ����� ���� ��ȭ��ȣ�� ����!
			acc.OutputMoney(money);
			//���� ���� ����----------------------------------
			//1. ���¹�ȣ : number
			//2. �Ա� : 0
			//3. ���: money
			//4. �ܾ� : money
			//---------------------------------------------
			InsertAccountIO(accid,0,money,acc.getBalance());

			System.out.println(money+ "�� ��� ����");
		}catch(Exception ex) {
			System.out.println("[��ݿ���]"+ex.getMessage());
		}

	}

	public void DeleteAccount() {
		//����ڿ��� ȸ����ȣ �Է¹޾� �˻� �� �ش� ȸ���� ����(BitArray.Delete�Լ��� Ȱ��)
		//�������� �帧�� Select�� �����ϴ�.
		try {
			int accid=BitGlobal.InputNumber("���¹�ȣ");
			int idx=NumberToIdx(accid);
			if(idx==-1)
				throw new Exception("���� ���¹�ȣ �Դϴ�.");

			arr.Delete(idx);
			System.out.println("���� �Ǿ����ϴ�.");

		}catch(Exception ex) {
			System.out.println("[���� ����] "+ex.getMessage());
		}
	}
}
