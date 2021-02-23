//bank.java
package bit.Client;

public class Bank {
	//통신 모듈
	//client의 Run메서드 호출시 서버 접속 연결!
	private TcpIpMultiClient2 client;
	
	//Bank생성시 서버 접속 요청!
	public Bank() {
		client = new TcpIpMultiClient2(this);	//자신을 넘기기 위해 this를 보냄. 쌍방참조를 위해.
		client.Run(); 
	}
	
	//---------------------------- 사용자 요청에 의해서 서버로 메시지를 전송---------
	public void PrintAll() {
		try {		
			String msg = Packet.SelectAllAccount();
			client.SendMessage(msg);
			System.out.println("서버로 전체계좌리스트 정보를 요청");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}
	}
			
	public void MakeAccount() {
		try {
			int number = BitGlobal.InputNumber("계좌번호");
			String name = BitGlobal.InputString("이름");
			int money = BitGlobal.InputNumber("입금액");
					
			String msg = Packet.MakeAccount(number, name, money);
			client.SendMessage(msg);
			System.out.println("서버로 신규계좌 정보를 전송");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}
	}
		
	public void SelectAccount() {
		try {
			int number = BitGlobal.InputNumber("계좌번호");
					
			String msg = Packet.SelectAccount(number);
			client.SendMessage(msg);
			System.out.println("서버로 검색할 계좌번호를 정보를 전송");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}	
	}
		
	public void InputMoney() {
		try {
			int number = BitGlobal.InputNumber("계좌번호");
			int money = BitGlobal.InputNumber("입금액");
			
			String msg = Packet.InputAccount(number, money);
			client.SendMessage(msg);
			System.out.println("서버로 계좌입금 정보를 전송");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}	
	}
		
	public void OutputMoney() {
		try {
			int number = BitGlobal.InputNumber("계좌번호");
			int money = BitGlobal.InputNumber("출금액");
			
			String msg = Packet.OutputAccount(number, money);
			client.SendMessage(msg);
			System.out.println("서버로 계좌출금 정보를 전송");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}	
	}
	
	public void DeleteAccount() {	
		try {
			int number = BitGlobal.InputNumber("계좌번호");
			
			String msg = Packet.DeleteAccount(number);
			client.SendMessage(msg);
			System.out.println("서버로 계좌삭제 정보를 전송");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}	
	}
	//-----------------------------------------------------------------------

	//------------------통신모듈에서 수신된 데이터를 받는 기능--------------
	public void RecvData(String msg) {
		Parser.RecvData(msg, this);
	}
	
	public void MakeAccount_Ack(int number,String result) {
		if(result.equals("S")) 
			System.out.println(number + "계좌의 생성 성공");
		else
			System.out.println(number + "계좌의 생성 실패!");
		
	}
}









