package bit.Client;

/*
[server -> Client]
	"MakeAccount_ack@111#S"   			"MakeAccount_ack@111#F"
	"SelectAccount_ack@S#111#ccm#1000#날짜#시간"	"SelectAccount_ack@F#111#-#0#-#-"
*/
public class Parser {	
	public static void RecvData(String msg, Bank bank) {
		String[] filter = msg.split("@");
		if( filter[0].equals("MakeAccount_ack")) {
			String[] filter1 = filter[1].split("#");
			int number    = Integer.parseInt(filter1[0]);
			String result = filter1[1];
			
			bank.MakeAccount_Ack(number, result);			
		}
		else if( filter[0].equals("SelectAccount_ack")) {
			String[] filter1 = filter[1].split("#");
			String result 	= filter1[0];
			int number    	= Integer.parseInt(filter1[1]);
			String name 	= filter1[2];
			int balance 	= Integer.parseInt(filter1[3]);
			String date 	= filter1[4];
			String time		= filter1[5];
			
			bank.SelectAccount_Ack(result, number, name, balance, date, time);			
		}
	}
}





