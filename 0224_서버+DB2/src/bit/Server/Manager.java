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
	
	//통신 & 데이터베이스
	private TcpIpMultiServer server = new TcpIpMultiServer(); //<===================
	private AccountDB1 db 			= new AccountDB1();	
		
	//DB와 Network을 연동!
	public void Run() {				//<=================================
		if(db.Run() == false) {
			System.out.println("서버 종료");
			System.exit(0);  //강제 종료함수
		}
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
		if( db.MakeAccount(id, name, balance) == true)
			msg = Packet.MakeAccount_ack(id, true);
		else
			msg = Packet.MakeAccount_ack(id, false);
		
		//클라이언트에 전송!
		return msg;
	}

	public String SelectAccount(int id) {
		Account acc = db.SelectAccount(id);
		
		//패킷 생성
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












