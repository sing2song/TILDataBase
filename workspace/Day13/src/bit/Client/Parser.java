package bit.Client;

/*
[server -> Client]
	"MakeAccount_ack@111#S	
	"MakeAccount_ack@111#F"
*/
public class Parser {

	public static void RecvData(String msg,Bank bank) {
		
		String[] filter = msg.split("@");
		if( filter[0].equals("MakeAccount")) {
			String[] filter1 = filter[1].split("#");
			int number = Integer.parseInt(filter1[0]);
			String result = filter1[1];//성공 실패 값
			System.out.println("Client.Parser-MA result="+result);
			bank.MakeAccount_Ack(number,result);
		}
		else if(filter[0].equals("SelectAccount")) {
			
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
