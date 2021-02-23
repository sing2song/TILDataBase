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
	
	private TcpIpMultiServer server = new TcpIpMultiServer(); //<===================
	
	//���¹�ȣ, ����
	private HashMap<Integer, Account> accountlist = new HashMap<Integer, Account>();
	
	public void Run() {				//<=================================
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
		if( accountlist.put(acc.getAccid(), acc) == null)
			msg = Packet.MakeAccount_ack(id, false);
		else
			msg = Packet.MakeAccount_ack(id, true);
		
		//Ŭ���̾�Ʈ�� ����!
		return msg;		
	}
	
	public String SelectAccount(int id) {
		Account acc = accountlist.get(id);
		
		//��Ŷ ����
		String msg =null;
		if(acc==null) {//����������
			msg = Packet.SelectAccount_ack(id, "-", 0, false);
		}else {//����������
			msg = Packet.SelectAccount_ack(id, acc.getName(), acc.getBalance(), true);
		}
		
		return msg;
	}
	
}











