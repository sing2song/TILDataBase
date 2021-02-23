package bit.Client;

public class Start {
	public static void main(String[] args) {
		//TcpIpMultiClient client = new TcpIpMultiClient();
		//client.Run();
		
		App app = App.getInstance();
		app.Run();
		app.Exit();
	}
}
