package bit.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

//Server�ڵ� ���!

public class TcpIpMultiServer {
	//�����Ͽ��� ����� ��Ʈ��ȣ
	final int PORT = 4000;				
	//������ Ŭ���̾�Ʈ��� ��ȭ�ϱ� ���� ���� ����!
	HashMap<String, PrintWriter>clients= new HashMap<String, PrintWriter>(); 
	//������
	private ServerSocket serverSocket =null;	
	
	//������
	public TcpIpMultiServer(){			  
	}
	
	public void Run() {
		try{
			InitWaitSocket();
		  
			while(true){
				//Ŭ���̾�Ʈ ���� & ��ż��� ����
				Socket socket = serverSocket.accept();
				System.out.println("[Ŭ���̾�Ʈ����] " + socket.getInetAddress()+":"+socket.getPort());
				   
				//���� ����� ������ IO�� ������ ���� thread
				ServerReceiver thread = new ServerReceiver(socket, clients);
				thread.start();	//threadŬ������ run�޼��尡 ȣ��!
			}			
		}catch(Exception e){
		    System.out.println("exception: "+e.getMessage());
		}	
	}

	//1. ������(���ϻ���, ���ε�, ����)
	private void InitWaitSocket() throws IOException {
		serverSocket = new ServerSocket(PORT);	//14.32.18.42
		System.out.println("��������,���� ��ٸ�");		
	}
}

//������ Ŭ����
//run�� ���۸޼���
class ServerReceiver extends Thread{
	private Socket socket;			//��� ����(Ŭ���̾�Ʈ�� ����� ����)
	private BufferedReader reader;	//read��ü(Ŭ���̾�Ʈ�� ���� ������ ���� �� �ִ�.)
	private PrintWriter writer;		//write��ü(Ŭ���̾�Ʈ���� ������ ���� �� �ִ�.)
	//----------------------------------------------
	private HashMap<String, PrintWriter> clients;
	
	//������
	public ServerReceiver(Socket socket, HashMap<String, PrintWriter>clients) {
		this.socket = socket;
		this.clients = clients;
		
		//
		try{
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
		}catch(Exception e){}

	}
	
	//������ �Լ�
	@Override
	public void run() {
		String name="";
		try{
			//Init -----------------------------------------------------------
			//name = reader.readLine();	 //1) ù��° Receive
		   // clients.put(name,writer);	 //<------------------------ [����!]		    
		   // sendToAll(name+"����"); 	 //2) ��ü ����(1�� �� ���)		    
		   // System.out.println("���� �����ڼ�"+clients.size()+"�̴�.");
		   // System.out.println(name + "����");
		    //----------------------------------------------------------------

		    //Run------------------------------------------------------------
		   while(reader!=null){
			   String msg = reader.readLine();	//1) ������ ����
			   System.out.println(msg);//2) ������ ó��
			   //sendToAll(msg); 					//3) ����� ����		
		   }
		   //-----------------------------------------------------------------
		  
		  
		}catch(Exception e){e.printStackTrace();}
		finally {	//������ ������ �ʿ��� �ڵ尡 ������ ���![�� �̻� �ش� ������ �ʿ� ����]
		   sendToAll(name+"������");	//<------------------
		   clients.remove(name);	//<------------------------------- [����!]
		   System.out.println("[Ŭ���̾�Ʈ ����] " + socket.getInetAddress()+":"+socket.getPort());
		   System.out.println("���� ���������ڼ�"+clients.size());
		   
		   try{				
			   socket.close();
		   }catch(Exception e){e.printStackTrace();}
		}		
	}

	//1�� �� ��� �Լ�
	void sendToAll(String msg){
		//����� ��ȸ ��ü	
		//clients��� ������� key���� ��ȸ�� �� �ִ� iterator�� ��ȯ�� ��!
	//	Iterator<String> itr = clients.keySet().iterator();
	//	while(itr.hasNext()){
	//		PrintWriter writer = clients.get(itr.next()); //KEY�� ���ؼ� value�� ȹ��!
	//		writer.println(msg);	//����!!!!!!![��¹��ۿ� ����]
	//		writer.flush();			//[��¹��ۿ� �ִ� ������ �о�� ����]	
	//	}
	}
}








