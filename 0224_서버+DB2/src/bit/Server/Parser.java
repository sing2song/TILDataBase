package bit.Server;

/*
[client -> Server]
		   "MakeAccount@111#ccm#1000"	//계좌번호, 이름, 입금액
		   "SelectAccount@111"			//계좌번호
		   "InputAccount@111#1000"  	//계좌번호, 입금액
		   "OutputAccount@111#1000"		//계좌번호, 출금액
		   "DeleteAccount@111"			//계좌번호
		   "SelectAllAccount@"			//없음.
*/
public class Parser {

	public static String RecvData(String msg) {
		String[] filter = msg.split("@");
		if( filter[0].equals("MakeAccount")) {
			String[] filter1 = filter[1].split("#");
			int number = Integer.parseInt(filter1[0]);
			String name = filter1[1];
			int balance = Integer.parseInt(filter1[2]);
			return Manager.getInstance().MakeAccount(number, name, balance);
		}
		else if(filter[0].equals("SelectAccount")) {			
			int number = Integer.parseInt(filter[1]);
			return Manager.getInstance().SelectAccount(number);
		}
		else if(filter[0].equals("InputAccount")) {
			
		}
		else if(filter[0].equals("OutputAccount")) {
			
		}
		else if(filter[0].equals("DeleteAccount")) {
			
		}
		else if(filter[0].equals("SelectAllAccount")) {
			
		}
		return "";
	}
}
