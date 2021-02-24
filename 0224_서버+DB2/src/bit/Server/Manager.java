package bit.Server;

import java.util.HashMap;

public class Manager {
	//�̱��� ���� �ڵ� -------------------------------------------------
	//������ ����!
	private Manager() { 	}		
	//�ڽ��� static ��ü ����
	private static Manager Singleton = new Manager();
	
	//���������� ������ �ڽ��� ��ü�� �ܺο� ���� �޼���
	public static Manager getInstance() {
		return Singleton;
	}
	//---------------------------------------------------------------
	
	//��� & �����ͺ��̽�
	private TcpIpMultiServer server = new TcpIpMultiServer(); //<===================
	private AccountDB1 db 			= new AccountDB1();	
		
	//DB�� Network�� ����!
	public void Run() {				//<=================================
		if(db.Run() == false) {
			System.out.println("���� ����");
			System.exit(0);  //���� �����Լ�
		}
		server.Run();	
	}
	
	//��Ÿ�⿡�� ���� ---> �ļ����� ����
	public String RecvData(String msg) {
		return Parser.RecvData(msg);
	}
	
	//�ļ����� �м��� ����� ���� �ش� �Լ��� ȣ�� -----------------------------------
	public String MakeAccount(int id, String name, int balance) {
		//����!
		String msg = null;
		
		Account acc = new Account(id, name, balance);
		System.out.println("[���Ÿ޽���]");		//<============ test�ڵ�------
		acc.Print();							//<============ test�ڵ�------
		if( db.MakeAccount(id, name, balance) == true)
			msg = Packet.MakeAccount_ack(id, true);
		else
			msg = Packet.MakeAccount_ack(id, false);
		
		//Ŭ���̾�Ʈ�� ����!
		return msg;
	}

	public String SelectAccount(int id) {
		Account acc = db.SelectAccount(id);
		
		//��Ŷ ����
		String msg = null;
		if(acc == null) {
			msg = Packet.SelectAccount_ack(id, "-", 0, "-", "-", false);
		}
		else {
			msg = Packet.SelectAccount_ack(
					id, acc.getName(), acc.getBalance(), acc.GetDate(), acc.GetTime(), true);
		}
		return msg;
	}
}












