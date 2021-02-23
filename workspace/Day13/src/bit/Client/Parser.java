package bit.Client;

/*
[server -> Client]
	"MakeAccount_ack@111#S"   			"MakeAccount_ack@111#F"
	"SelectAccount_ack@S#111#ccm#1000"	"SelectAccount_ack@F#111#-#0"
*/

public class Parser {

	public static void RecvData(String msg,Bank bank) {
		
		String[] filter = msg.split("@");
		if( filter[0].equals("MakeAccount_ack")) {
			String[] filter1 = filter[1].split("#");
			int number = Integer.parseInt(filter1[0]);
			String result = filter1[1];//성공 실패 값
			
			System.out.println("Client.Parser-MA result="+result);
			
			bank.MakeAccount_Ack(number,result);
		}
		else if(filter[0].equals("SelectAccount_ack")) {
			String[] filter1 = filter[1].split("#");
			String result 		= filter1[0];//성공 실패 값
			int number 			= Integer.parseInt(filter1[1]);
			String name			= filter1[2];
			int balance 		= Integer.parseInt(filter1[3]);
			
			bank.SelectAccount_Ack(result,number,name,balance);
		}
		else if(filter[0].equals("InputAccount")) {
			
		}
		else if(filter[0].equals("OutputAccount")) {
			
		}
		else if(filter[0].equals("DeleteAccount")) {
			
		}
		else if(filter[0].equals("SelectAllAccount")) {
			
		}
	}
}
