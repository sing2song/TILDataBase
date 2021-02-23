package bit.Server;
/*
[client -> Server]
   "MakeAccount@111#ccm#1000"	//계좌번호, 이름, 입금액
   "SelectAccount@111"			//계좌번호
   "InputAccount@111#1000"  	//계좌번호, 입금액
   "OutputAccount@111#1000"		//계좌번호, 출금액
   "DeleteAccount@111"			//계좌번호
   "SelectAllAccount@"			//없음.
   
[server -> Client]
	"MakeAccount_ack@111#S"			"MakeAccount_ack@111#F"
	//성공,아이디, 이름, 잔액정보
	"SelectAccount_ack@S#111"		"SelectAccount_ack@F#111"	
*/
public class Packet {
	public static String MakeAccount_ack(int id, boolean b) {
		String msg = "";
		msg +=  "MakeAccount_ack" + "@";
		msg += id + "#";
		if(b)	msg += "S";
		else	msg += "F";
		return msg;
	}
	public static String SelectAccount_ack(int id, String name, int balance, boolean b) {
		String msg = "";
		msg +=  "SelectAccount_ack" + "@";
		msg += id + "#";
		if(b)	msg += "S"+"#";
		else	msg += "F"+"#";
		msg+=id+"#";
		msg+=name+"#";
		msg+=balance;
		
		return msg;
	}

}
