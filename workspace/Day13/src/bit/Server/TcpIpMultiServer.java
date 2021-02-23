package bit.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

//
public class TcpIpMultiServer {
	//대기소켓에서 사용할 포트번호
	final int PORT = 4000;	
	//접속한 클라이언트들과 대화하기 위한 정보 저장!
	HashMap<String, PrintWriter>clients = new HashMap<String,PrintWriter>();	
	//대기소켓
	private ServerSocket serverSocket =null;	

	//생성자
	public TcpIpMultiServer(){
	}

	public void Run() {
		try{
			InitWaitSocket();//호출

			//run부분
			while(true){//예외발생시에만 종료됨! - 더이상 소켓을 사용할 수 없다는 뜻.
				//클라이언트 접속 & 통신소켓 생성
				Socket socket = serverSocket.accept();
				System.out.println("[클라이언트접속] "+socket.getInetAddress()+":"+socket.getPort()+"에서 접속했당");//클라이언트 접속 정보 출력

				//현재 연결된 소켓의 IO를 수행할 전용 thread
				ServerReceiver thread = new ServerReceiver(socket, clients);//위에서 생성한 소켓과 클라이언트
				thread.start(); //thread의 run메서드가 호출!
			}

			//2. ServerSocket 의 accept() 실행 및 대기(클라이언트가 접속할 때까지)
			//5. 클라이언트가 접속을 시도하면 accept() 메소드가 클라이언트의 socket을 리턴
		}catch(Exception e){
			System.out.println("exception: "+e.getMessage());
		}
	}

	//1. 대기소켓(소켓생성, 바인드, 릿슨)
	private void InitWaitSocket() throws IOException {
		serverSocket = new ServerSocket(PORT);// 192.168.0.14
		System.out.println("서버시작,접속 기다림");	
	}



}

//쓰레드 클래스
//run이 동작메서드
class ServerReceiver extends Thread{
	private Socket socket;			//통신소켓(클라이언트와 연결된 상태)
	private BufferedReader reader;	//read객체(클라이언트가 보낸 정보를 읽을 수 있음)
	private PrintWriter writer;		//write객체(클라이언트에게 정보를 보낼 수 있음)
	//---------------------------------------------
	private HashMap<String, PrintWriter> clients;

	//생성자
	public ServerReceiver(Socket socket,HashMap<String, PrintWriter> clients) {
		this.socket=socket;
		this.clients=clients;

		//
		try {
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
		}catch(Exception e) {

		}
	}

	//스레드 함수
	@Override
	public void run() {
		String name="";
		try{
			//init---------------------------------------------------
			name = reader.readLine();	//1) 첫번째 Receive
			clients.put(name,writer);	//<------------------- 저장!
			sendToAll(name+"들어옴");		//2) 전체 전송(1대 다 통신)
			System.out.println("현재 접속자수"+clients.size()+"이다.");
			System.out.println(name+"들어옴");
			//-------------------------------------------------------

			//Run----------------------------------------------------
			while(reader!=null){
				String msg = reader.readLine();	//1) 데이터 수신
				System.out.println(msg); 		//2) 데이터 처리
				sendToAll(msg);					//3) 결과를 전송
			}


		}catch(Exception e){e.printStackTrace();}
		finally {	//무조건 실행이 필요한 코드가 존재할 경우![더 이상 해당 소켓은 필요없는 경우 발생]
			//정상적인 종료코드
			sendToAll(name+"나갔어");	//<----------------
			clients.remove(name);	//<----------------	[삭제!]
			System.out.println("[클라이언트 해제] "+socket.getInetAddress()+":"+socket.getPort());
			System.out.println("현재 서버접속자수: "+clients.size());

			try{
				socket.close();
			}catch(Exception e){e.printStackTrace();}
		}

	}

	//1대 다 통신 함수
	void sendToAll(String msg){
		//저장소 순회 객체
		//client라는 저장소의 key값을 순회할 수 있는 iterator를 반환해줘!
		Iterator<String> itr = clients.keySet().iterator();
		while(itr.hasNext()){
			PrintWriter writer = clients.get(itr.next());
			writer.println(msg);	//전송!!![출력버퍼에 저장]
			writer.flush();			//[출력버퍼에 있는 정보를 밀어내는 역할]
		}
	}

}
