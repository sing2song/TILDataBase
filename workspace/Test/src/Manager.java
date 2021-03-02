import java.util.ArrayList;

//bank.java


public class Manager {
	//Database����
	private BitDB  db;

	//������
	public Manager() {
		try {
			db = new BitDB();
		}
		catch(Exception ex) {
			System.out.println("������ ���̽� ���� ����");			
		}
	}

	//�޼���	
	//1.ȸ�����
	public void InsertMember() {
		try {
			//1. ��û����(client)
			String memberID = BitGlobal.InputString("ȸ�� ���̵�");
			if(IsMemberIDCheck(memberID)==true)
				throw new Exception("�ߺ��� ȸ�� ���̵� �����մϴ�.");

			String name = BitGlobal.InputString("�̸�");

			//2. ����.(Server)
			if(db.InsertMember_DB(memberID,  name)==false)
				throw new Exception("ȸ�� ��� ����");

			//3. ���.(client)
			System.out.println("ȸ�� ��� ����!");
		}
		catch(Exception ex) {
			System.out.println("[ȸ����Ͻ���] " + ex.getMessage());
		}
	}
	//+ȸ�����̵�üũ
	private boolean IsMemberIDCheck(String memberId) {
		return db.MemberIDCheck(memberId);
	}

	//2.ȸ����ü ���!
	public void SelectAllMember() {
		ArrayList<Member> members = db.SelectAllMember_DB();
		System.out.println("[��ü ȸ�� ��] " + members.size() + "��");
		for(Member m : members) {
			m.Print();
		}
		System.out.println();
	}

	//3.��������
	public void InsertDrink() {
		try {
			//1. ��û����(client)
			String name = BitGlobal.InputString("�������");
			int price = BitGlobal.InputNumber("����");

			//2. ����.(Server)
			if(db.InsertDrink_DB(name,  price)==false)
				throw new Exception("����� ��� ����");

			//3. ���.(client)
			System.out.println("����� ��� ����!");
		}
		catch(Exception ex) {
			System.out.println("[�������Ͻ���] " + ex.getMessage());
		}
	}

	//4.�������ü ���!
	public void SelectAllDrink() {
		ArrayList<Drink> drinks = db.SelectAllDrink_DB();
		System.out.println("[��ü ����� ��] " + drinks.size() + "��");
		for(Drink d : drinks) {
			d.Print();
		}
		System.out.println();
	}
	
	//5.��� ��ü ���
	public void SelectAllRank() {
		ArrayList<Rank> ranks = db.SelectAllRank_DB();
		for(Rank r : ranks) {
			r.Print();
		}
		System.out.println();
	}

	//6.�������̺� ���
	public void SelectAllBuyDrink() {
		ArrayList<BuyDrink> bds = db.SelectAllBuyDrink_DB();
		System.out.println("[��ü ���� ��] " + bds.size() + "��");
		for(BuyDrink bd : bds) {
			bd.Print();
		}
		System.out.println();
	}
	
	//7.���������
	public void BuyDrink() {
		try {
			//1. client
			String memberId = BitGlobal.InputString("ȸ�� ���̵� �Է�");
			int drinkId = BitGlobal.InputNumber("����� ��ȣ �Է�");
			int count = BitGlobal.InputNumber("����");
			
			//2. server 
			boolean result = db.Update(memberId,  drinkId, count);

			//3. client
			if(result == false)
				throw new Exception("");
			else			
				System.out.println("����� ���Ű� �Ϸ�Ǿ����ϴ�.");
		}
		catch(Exception ex) {
			System.out.println("[����� ���ſ���]" + ex.getMessage());
		}
	}

	//8.ȸ�� �������� �˻�
	public void SelectBuyDrink() {
		String memberId = BitGlobal.InputString("ȸ�� ���̵� �Է�");
			
		Member member = new Member();	//?????
		ArrayList<BuyDrink> bds = new ArrayList<BuyDrink>();		
		if(db.SelectBuyDrink_DB(memberId,member,bds) == false) {
			System.out.println("�����Ͱ� �������� �ʽ��ϴ�");
			return;
		}
		//ȸ������
		member.Print();
		System.out.println("------------------------------------------");
		for(BuyDrink bd : bds) {
			System.out.println("[������ ����� �� ����]" +bd.getCount());
			System.out.println("[������ �� ��]"+bd.getTotalmoney()+"��");
		}
		System.out.println();
		
		
		System.out.println();
	}
	
	//9. ���� ���� �Ǹŵ� ����� ���� ���
	public void SelectBestSeller() {
		Drink drink = db.SelectBestSeller_DB();
		System.out.println("[�������]"+drink.getName());
		System.out.println("[����]"+drink.getPrice());
		System.out.println("[�� �Ǹż���]"+drink.getCount());		
	}
	
	
	
}









