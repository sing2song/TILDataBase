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
