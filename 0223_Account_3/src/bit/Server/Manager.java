package bit.Server;

import java.util.HashMap;

public class Manager {
	//싱글톤 패턴 코드 -------------------------------------------------
	//생성자 은닉!
	private Manager() { 	}		
	//자신의 static 객체 생성
	private static Manager Singleton = new Manager();
	
	//내부적으로 생성된 자신의 객체를 외부에 노출 메서드
	public static Manager getInstance() {
		return Singleton;
	}
	//---------------------------------------------------------------
	
	private TcpIpMultiServer server = new TcpIpMultiServer(); //<===================
	
	//계좌번호, 계좌
	private HashMap<Integer, Account> accountlist = new HashMap<Integer, Account>();
	
	public void Run() {				//<=================================
		server.Run();
	}
	
	//통신모듈에서 전달 ---> 파서에게 전달
	public String RecvData(String msg) {
		return Parser.RecvData(msg);
	}
	
	//파서에서 분석된 결과에 따라 해당 함수를 호출 -----------------------------------
	public String MakeAccount(int id, String name, int balance) {
		//저장!
		String msg = null;
		
		Account acc = new Account(id, name, balance);
		System.out.println("[수신메시지]");		//<============ test코드------
		acc.Print();							//<============ test코드------
		if( accountlist.put(acc.getAccid(), acc) == null)
			msg = Packet.MakeAccount_ack(id, true);
		else
			msg = Packet.MakeAccount_ack(id, false);
		
		//클라이언트에 전송!
		return msg;
	}
	
}












