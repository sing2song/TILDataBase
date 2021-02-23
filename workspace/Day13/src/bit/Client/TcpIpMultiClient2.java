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
	final String SERVER_IP = "14.32.18.42"; //127.0.0.1
	private ClientSender2 clientsender = null;
	private Bank bank;
	
	//������
	public TcpIpMultiClient2(Bank bank) {
		this.bank=bank;	//�ֹ�����. ���ʴ� �޼ҵ� ������ �����ϰ� �ȴ�
	}
	
	//�޼���
	public void Run() {
		try{
			//���� ���� �� ����!
			Socket socket = new Socket(SERVER_IP, PORT);
			System.out.println("���� ���� ��");	
			clientsender = new ClientSender2(socket);		//�Ϲ� ��ü
			Thread receiver = new ClientReceiver2(socket,bank); 	//������ ��ü			   
			receiver.start();			
		}catch(ConnectException e){
			e.printStackTrace();
		}catch(Exception e) { }
	}	

	//������ ���� ���
	public void SendMessage(String msg) {
		try {
			clientsender.SendMessage(msg);//null�� ����� ��ü�� ��������� ������!
		}
		catch(Exception ex) {		
			System.out.println("���� ����");
		}
	}
}

//�۽� ���� thread
class ClientSender2{
	//private Socket socket;
	private PrintWriter writer;
	 
	public ClientSender2(Socket socket){
	  //this.socket=socket;
	  try{
		  writer = new PrintWriter(socket.getOutputStream());		  
	  }catch(Exception e) {}
	}
	
	public void SendMessage(String msg) {		
		writer.println(msg);			
		writer.flush();
		//--------msg���--------------
	}
}

//���� ���� thread
class ClientReceiver2 extends Thread{
	 private Socket socket;
	 private BufferedReader reader;
	 private Bank bank;				//����ʵ� ����
	 
	 //������
	 public ClientReceiver2(Socket socket,Bank bank){
		 this.socket=socket;
		 this.bank=bank;			//�����ڿ� ����
		 try{
			 reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));	   
		 }catch(IOException e) {}
	 }
	 
	 @Override
	 public void run(){
		 while(reader !=null){
			 try{
				 String msg = reader.readLine();	//1. ����
				 bank.RecvData(msg);				//2. ���� - �����ڸ� ���ؼ� bank�� �޾Ƽ� �ʵ忡 ���ϰ� �� ��
				 //System.out.println(msg);			//3. ������
			 }catch(IOException e){}
		 }
		 try{
			 socket.close();
		 }catch(IOException e) {e.printStackTrace(); }
	 }
}





