package bit.Server;

/*
[client -> Server]
		   "MakeAccount@111#ccm#1000"	//���¹�ȣ, �̸�, �Աݾ�
		   "SelectAccount@111"			//���¹�ȣ
		   "InputAccount@111#1000"  	//���¹�ȣ, �Աݾ�
		   "OutputAccount@111#1000"		//���¹�ȣ, ��ݾ�
		   "DeleteAccount@111"			//���¹�ȣ
		   "SelectAllAccount@"			//����.
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
