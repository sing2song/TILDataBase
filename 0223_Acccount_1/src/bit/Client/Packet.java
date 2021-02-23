package bit.Client;

/*
[client -> Server]
   "MakeAccount@111#ccm#1000"	//���¹�ȣ, �̸�, �Աݾ�
   "SelectAccount@111"			//���¹�ȣ
   "InputAccount@111#1000"  	//���¹�ȣ, �Աݾ�
   "OutputAccount@111#1000"		//���¹�ȣ, ��ݾ�
   "DeleteAccount@111"			//���¹�ȣ
   "SelectAllAccount@"			//����.
   
[server -> Client]
	"MakeAccount_ck@111#S"
	"MakeAccount_ack@111#F"
*/

public class Packet {
	public static String MakeAccount(int id, String name, int balance) {
		String msg = null;
		msg +=  "MakeAccount" + "@";
		msg += id + "#";
		msg += name + "#";
		msg += balance;
		return msg;
	}

	public static String SelectAccount(int id) {
		String msg = null;
		msg += "SelectAccount" + "@";
		msg += id;
		return msg;
	}

	public static String InputAccount(int id,int balance) {
		String msg = null;
		msg +=  "InputAccount" + "@";
		msg += id + "#";
		msg += balance;
		return msg;
	}

	public static String OutputAccount(int id,int balance) {
		String msg = null;
		msg +=  "OutputAccount" + "@";
		msg += id + "#";
		msg += balance;
		return msg;
	}

	public static String DeleteAccount(int id) {
		String msg = null;
		msg +=  "DeleteAccount" + "@";
		msg += id;
		return msg;
	}

	public static String SelectAllAccount() {
		String msg = null;
		msg +=  "SelectAllAccount" + "@";
		return msg;
	}
}
