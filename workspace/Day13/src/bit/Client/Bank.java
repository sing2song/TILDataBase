//bank.java
package bit.Client;

import java.util.ArrayList;

public class Bank {
	//��� ���
	//client�� Run�޼��� ȣ��� ���� ���� ����!
	private TcpIpMultiClient2 client;

	//Bank������ ���� ���� ��û!
	public Bank() {
		client = new TcpIpMultiClient2(this);	//�ڽ��� �ѱ�� ���� this�� ����. �ֹ������� ����.
		client.Run(); 
	}

	//---------------------------- ����� ��û�� ���ؼ� ������ �޽����� ����---------
	public void PrintAll() {
		try {		
			String msg = Packet.SelectAllAccount();
			client.SendMessage(msg);
			System.out.println("������ ��ü���¸���Ʈ ������ ��û");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}
	}

	public void MakeAccount() {
		try {
			int number = BitGlobal.InputNumber("���¹�ȣ");
			String name = BitGlobal.InputString("�̸�");
			int money = BitGlobal.InputNumber("�Աݾ�");

			String msg = Packet.MakeAccount(number, name, money);
			client.SendMessage(msg);
			System.out.println("������ �ű԰��� ������ ����");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}
	}

	public void SelectAccount() {
		try {
			int number = BitGlobal.InputNumber("���¹�ȣ");

			String msg = Packet.SelectAccount(number);
			client.SendMessage(msg);
			System.out.println("������ �˻��� ���¹�ȣ�� ������ ����");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}	
	}

	public void InputMoney() {
		try {
			int number = BitGlobal.InputNumber("���¹�ȣ");
			int money = BitGlobal.InputNumber("�Աݾ�");

			String msg = Packet.InputAccount(number, money);
			client.SendMessage(msg);
			System.out.println("������ �����Ա� ������ ����");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}	
	}

	public void OutputMoney() {
		try {
			int number = BitGlobal.InputNumber("���¹�ȣ");
			int money = BitGlobal.InputNumber("��ݾ�");

			String msg = Packet.OutputAccount(number, money);
			client.SendMessage(msg);
			System.out.println("������ ������� ������ ����");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}	
	}

	public void DeleteAccount() {	
		try {
			int number = BitGlobal.InputNumber("���¹�ȣ");

			String msg = Packet.DeleteAccount(number);
			client.SendMessage(msg);
			System.out.println("������ ���»��� ������ ����");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}	
	}
	//-----------------------------------------------------------------------

	//------------------��Ÿ�⿡�� ���ŵ� �����͸� �޴� ���--------------
	public void RecvData(String msg) {
		Parser.RecvData(msg, this);
	}

	public void MakeAccount_Ack(int number,String result) {
		if(result.equals("S")) 
			System.out.println(number + "������ ���� ����");
		else
			System.out.println(number + "������ ���� ����!");

	}

	public void SelectAccount_Ack(String result, int number, String name, int balance,String date, String time) {
		if(result.equals("F")) {
			System.out.println(number + "���� ��ȣ�� ���� ��ȣ�Դϴ�");
			return;
		}

		System.out.println("���¹�ȣ : " + number);
		System.out.println("�̸� : " + name);
		System.out.println("�ܾ� : " + balance);
		System.out.println("��¥ : " + date);
		System.out.println("�ð� : " + time);
	}

	public void InputAccount_Ack(String result, int number, int money) {
		if(result.equals("S"))
			System.out.println(number + "�Ա� ����");
		else
			System.out.println(number + "�Ա� ����");
	}
	
	public void OutputAccount_Ack(String result, int number, int money) {
		if(result.equals("S"))
			System.out.println(number + "��� ����");
		else
			System.out.println(number + "��� ����");
	}

	public void SelectALLAccount_Ack(String result, ArrayList<Account> acclist) {
		
		System.out.println("���尳�� : "+acclist.size());
		for(Account acc : acclist)
			acc.Print();
	}

	

}









