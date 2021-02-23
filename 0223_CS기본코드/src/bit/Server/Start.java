package bit.Server;

public class Start {
	public static void main(String[] args) {
		TcpIpMultiServer server = new TcpIpMultiServer();
		server.Run();
	}
}
