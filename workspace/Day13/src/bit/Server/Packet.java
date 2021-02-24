package bit.Server;

import java.util.ArrayList;

/*
[client -> Server]
   "MakeAccount@111#ccm#1000"	//���¹�ȣ, �̸�, �Աݾ�
   "SelectAccount@111"			//���¹�ȣ
   "InputAccount@111#1000"  	//���¹�ȣ, �Աݾ�
   "OutputAccount@111#1000"		//���¹�ȣ, ��ݾ�
   "DeleteAccount@111"			//���¹�ȣ
   "SelectAllAccount@"			//����.
   
[server -> Client]
	"MakeAccount_ack@111#S"						"MakeAccount_ack@111#F"
					//����,���̵�, �̸�, �ܾ�����
	"SelectAccount_ack@S#111#ccm#1000#��¥#�ð�"	"SelectAccount_ack@F#111#-#-"	
	"InputAccount_ack@S#111#�Աݾ�"				"SelectAccount_ack@F#111#�Աݾ�"
	"OutputAccount_ack@S#111#��ݾ�"				"SelectAccount_ack@F#111#��ݾ�"
	
	
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
	
	public static String SelectAccount_ack(int id, String name, int balance, String date, String time, boolean b) {
		String msg = "";
		msg +=  "SelectAccount_ack" + "@";
		msg += id + "#";
		if(b)	msg += "S"+"#";
		else	msg += "F"+"#";
		msg+=id+"#";
		msg+=name+"#";
		msg+=balance+"#";
		msg+=date+"#";
		msg+=time;
		
		return msg;
	}

	public static String InputAccount_ack(int id, int balance, boolean b) {
		String msg = "";
		msg +=  "InputAccount_ack" + "@";
		if(b)	msg += "S";
		else	msg += "F";
		msg += id + "#";
		msg += balance;
		return msg;
	}

	public static String OutputAccount_ack(int id, int balance, boolean b) {
		String msg = "";
		msg +=  "OutputAccount_ack" + "@";		
		if(b)	msg += "S";
		else	msg += "F";
		msg += id + "#";
		msg += balance;
		return msg;
	}

	public static String DeleteAccount_ack(int id, boolean b) {
		String msg = "";
		msg +=  "DeleteAccount_ack" + "@";
		msg += id + "#";
		if(b)	msg += "S";
		else	msg += "F";
		return msg;
	}
	//���(flag)@����/����#ù��°�������%ù����ι�°����%ù���3��°����#
	public static String SelectAllAccount_ack(ArrayList<Account> acclist, boolean b) {
		String msg = "";
		msg +=  "SelectAllAccount_ack" + "@";
		if(b)	msg += "S"+"#";
		else	msg += "F"+"#";
		for(Account ac : acclist) {
			msg+=ac.getAccid()+"%";
			msg+=ac.getName()+"%";
			msg+=ac.getBalance()+"%";
			msg+=ac.GetDate()+"%";
			msg+=ac.GetTime()+"#";
		}
		return msg;
	}
	
}
