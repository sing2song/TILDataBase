package bit.Client;
import java.util.ArrayList;

//bank.java


public class Bank {

	//통신모듈이 필요!
	//Bank가 생성될 때 서버에 접속 요청!
	//client의 Run메서드 호출시 서버 접속 연결!
	private TcpIpMultiClient2 client = new TcpIpMultiClient2();

	//Bank생성시 서버 접속 요청!
	public Bank() {
		client.Run();
	}

	//메서드
	//계좌 전체 정보 출력!
	public void PrintAll() {
		String msg = Packet.SelectAllAccount();
		client.SendMessage(msg);
		System.out.println("서버로 전체 계좌 리스트 정보를 요청");
	}

	public void MakeAccount() {
		try {
			//1. 요청정보(client)
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
			//1. 요청정보(client)
			int number = BitGlobal.InputNumber("계좌번호");
			
			String msg = Packet.SelectAccount(number);
			client.SendMessage(msg);
			System.out.println("서버로 검색할 계좌번호 정보를 전송");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}
	}	

	public void InputMoney() {
		try {
			//1. 요청정보(client)
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
			//1. 요청정보(client)
			int number = BitGlobal.InputNumber("계좌번호");
			int money = BitGlobal.InputNumber("입금액");

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
			//1. 요청정보(client)
			int number = BitGlobal.InputNumber("계좌번호");
			
			String msg = Packet.DeleteAccount(number);
			client.SendMessage(msg);
			System.out.println("서버로 계좌삭제 정보를 전송");
		}
		catch(Exception ex) {
			System.out.println("[전송실패] " + ex.getMessage());
		}
	}
	
}









