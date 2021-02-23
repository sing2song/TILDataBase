package bit.Client;
import java.util.ArrayList;

//bank.java


public class Bank {

	//��Ÿ���� �ʿ�!
	//Bank�� ������ �� ������ ���� ��û!
	//client�� Run�޼��� ȣ��� ���� ���� ����!
	private TcpIpMultiClient2 client = new TcpIpMultiClient2();

	//Bank������ ���� ���� ��û!
	public Bank() {
		client.Run();
	}

	//�޼���
	//���� ��ü ���� ���!
	public void PrintAll() {
		String msg = Packet.SelectAllAccount();
		client.SendMessage(msg);
		System.out.println("������ ��ü ���� ����Ʈ ������ ��û");
	}

	public void MakeAccount() {
		try {
			//1. ��û����(client)
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
			//1. ��û����(client)
			int number = BitGlobal.InputNumber("���¹�ȣ");
			
			String msg = Packet.SelectAccount(number);
			client.SendMessage(msg);
			System.out.println("������ �˻��� ���¹�ȣ ������ ����");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}
	}	

	public void InputMoney() {
		try {
			//1. ��û����(client)
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
			//1. ��û����(client)
			int number = BitGlobal.InputNumber("���¹�ȣ");
			int money = BitGlobal.InputNumber("�Աݾ�");

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
			//1. ��û����(client)
			int number = BitGlobal.InputNumber("���¹�ȣ");
			
			String msg = Packet.DeleteAccount(number);
			client.SendMessage(msg);
			System.out.println("������ ���»��� ������ ����");
		}
		catch(Exception ex) {
			System.out.println("[���۽���] " + ex.getMessage());
		}
	}
	
}









