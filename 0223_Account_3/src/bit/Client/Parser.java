package bit.Client;

/*
[server -> Client]
	"MakeAccount_ack@111#S"
	"MakeAccount_ack@111#F"
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
	}
}
