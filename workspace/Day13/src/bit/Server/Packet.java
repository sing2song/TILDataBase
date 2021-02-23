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
	"MakeAccount_ack@111#S"
	"MakeAccount_ack@111#F"
*/
public class Packet {
	public static String MakeAccount_ack(int id, boolean b) {
		String msg = null;
		msg +=  "MakeAccount_ack" + "@";
		msg += id + "#";
		if(b)
			msg += "S";
		else
			msg += "F";
		return msg;
	}


}
