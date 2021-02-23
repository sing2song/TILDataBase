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
	final String name = "��â��";

	public void Run() {
		try {
			//���� ���� �� ����!
			Socket socket = new Socket(SERVER_IP,PORT);
			System.out.println("���� ���� ��");

			Thread sender = new ClientSender(socket,name);
			Thread receiver = new ClientReceiver(socket);

			sender.start();
			receiver.start();

		}catch(ConnectException e) {
			e.printStackTrace();
		}catch(Exception e) {}
	}

}

//�۽� ���� thread
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
			writer.println(name);	//�̸��� ����!
			writer.flush();
		}

		while(writer !=null){		//<======================���� ������(writer)�� null�� �Ǵ°�? ������·μ��� ����!
			String msg = kb.nextLine();		//1.���� ���ڿ� ȹ��
			writer.println("["+name+"]"+msg);//2. ����
			writer.flush();
		}
		kb.close();
		//9. ����
		try{
			socket.close();
		}catch(IOException e){e.printStackTrace();}
	}
}


//���� ���� thread
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
				String msg = reader.readLine();		//1. ����
				//2. ����
				System.out.println(msg);			//3. ������
			}catch(IOException e){}
		}
		try{ //���� close()
			socket.close();
		}catch(IOException e) {e.printStackTrace(); }
	}
}