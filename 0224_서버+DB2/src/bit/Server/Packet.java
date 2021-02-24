package bit.Server;
/*
[client -> Server]
   "MakeAccount@111#ccm#1000"	//���¹�ȣ, �̸�, �Աݾ�
   "SelectAccount@111"			//���¹�ȣ
   "InputAccount@111#1000"  	//���¹�ȣ, �Աݾ�
   "OutputAccount@111#1000"		//���¹�ȣ, ��ݾ�
   "DeleteAccount@111"			//���¹�ȣ
   "SelectAllAccount@"			//����.
   
[server -> Client]
	"MakeAccount_ack@111#S"   			"MakeAccount_ack@111#F"
	"SelectAccount_ack@S#111#ccm#1000#��¥#�ð�"	"SelectAccount_ack@F#111#-#0#-#-"
*/
public class Packet {
	public static String MakeAccount_ack(int id, boolean b) {
		String msg = "";
		msg +=  "MakeAccount_ack" + "@";
		msg += id + "#";
		if(b)
			msg += "S";
		else
			msg += "F";
		return msg;
	}

	public static String SelectAccount_ack(int id, String name, int balance, String date, String time, boolean b) {
		String msg = "";
		msg +=  "SelectAccount_ack" + "@";
		if(b)	msg += "S" + "#";
		else	msg += "F" + "#";
		msg += id + "#";
		msg += name + "#";
		msg += balance + "#";
		msg += date + "#";
		msg += time;
		
		return msg;
	}
}















