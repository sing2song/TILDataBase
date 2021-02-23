package bit.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TcpIpMultiClient2 {
	final int PORT = 4000;
	final String SERVER_IP = "192.168.0.14";// �����Լ��� : 14.32.18.42
	private ClientSender2 clientsender = null;
	
	//�޼���
	public void Run() {
		try {
			//���� ���� �� ����!
			Socket socket = new Socket(SERVER_IP,PORT);
			System.out.println("���� ���� ��");
			clientsender = new ClientSender2(socket);
			Thread receiver = new ClientReceiver2(socket);
			clientsender.start();
			receiver.start();

		}catch(ConnectException e) {
			e.printStackTrace();
		}catch(Exception e) {}
	}
	
	//������ ���۱��
	public void SendMessage(String msg) {
		try {
			clientsender.SendMessage(msg);
		}catch(Exception ex) {
			System.out.println("���� ����");
		}
	}

}

//�۽� ���� thread
class ClientSender2{
	private Socket socket;
	private PrintWriter writer;
	 
	public ClientSender2(Socket socket){
	  this.socket=socket;
	  try{
		  writer = new PrintWriter(socket.getOutputStream());		  
	  }catch(Exception e) {}
	}
	
	public void SendMessage(String msg) {		
		writer.println(msg);			
		writer.flush();
	}
}



//���� ���� thread
class ClientReceiver2 extends Thread{
	private Socket socket;
	private BufferedReader reader;
	public ClientReceiver2(Socket socket){
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