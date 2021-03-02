package sample2;

import java.util.Scanner;

public class Start {	
	private Scanner scan = new Scanner(System.in);
	
	public Start() {
		Init();
	}
	
	private void Init() {
		System.out.println("------------------------------------------");
		System.out.println("[번역기] 파파고 OPEN API활용");
		System.out.println(" 종료하려면 exit를 입력하세요");
		System.out.println("------------------------------------------\n");
	}
	
	public void Run() {
		System.out.println(">> 안녕하세요. 오늘 기분은 어떻습니까?");
		String msg = TransOpenAPI.Request("안녕하세요. 오늘 기분은 어떻습니까?");		
		System.out.println(Parser.Parsing(msg));
		
		while(true) {
			System.out.print("\n>> ");
			String inputmsg = scan.nextLine();
			if(inputmsg.equals("exit"))
				break;
			String retmsg = TransOpenAPI.Request(inputmsg);
			System.out.println(Parser.Parsing(retmsg));			
		}
	}
	
	public static void main(String[] args) {		
		Start s = new Start();
		s.Run();
    }    
}

