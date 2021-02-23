package bit.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpMultiClient {
	final int PORT = 4000;
	final String SERVER_IP = "127.0.0.1";// 192.168.0.14
	final String name = "최창민";

	public void Run() {
		try {
			//소켓 생성 및 연결!
			Socket socket = new Socket(SERVER_IP,PORT);
			System.out.println("서버 연결 됨");

			Thread sender = new ClientSender(socket,name);
			Thread receiver = new ClientReceiver(socket);

			sender.start();
			receiver.start();

		}catch(ConnectException e) {
			e.printStackTrace();
		}catch(Exception e) {}
	}

}

//송신 전용 thread
class ClientSender extends Thread{
	Socket socket;
	PrintWriter writer;
	String name;
	ClientSender(Socket socket, String name){
		this.socket=socket;  
		try{
			writer = new PrintWriter(socket.getOutputStream());

			this.name=name;
		}catch(Exception e) {}
	}

	@Override
	public void run(){
		Scanner kb=new Scanner(System.in);
		if(writer !=null){
			writer.println(name);	//이름이 전송!
			writer.flush();
		}

		while(writer !=null){		//<======================언제 데이터(writer)가 null이 되는가? 현재상태로서는 없음!
			String msg = kb.nextLine();		//1.전송 문자열 획득
			writer.println("["+name+"]"+msg);//2. 전송
			writer.flush();
		}
		kb.close();
		//9. 닫음
		try{
			socket.close();
		}catch(IOException e){e.printStackTrace();}
	}
}


//수신 전용 thread
class ClientReceiver extends Thread{
	private Socket socket;
	private BufferedReader reader;
	public ClientReceiver(Socket socket){
		this.socket=socket;
		try{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}catch(IOException e) {}
	}

	@Override
	public void run(){
		while(reader !=null){
			try{
				String msg = reader.readLine();		//1. 수신
				//2. 연산
				System.out.println(msg);			//3. 결과출력
			}catch(IOException e){}
		}
		try{ //소켓 close()
			socket.close();
		}catch(IOException e) {e.printStackTrace(); }
	}
}