package sample2;

import java.util.Scanner;

public class Start {	
	private Scanner scan = new Scanner(System.in);
	
	public Start() {
		Init();
	}
	
	private void Init() {
		System.out.println("------------------------------------------");
		System.out.println("[������] ���İ� OPEN APIȰ��");
		System.out.println(" �����Ϸ��� exit�� �Է��ϼ���");
		System.out.println("------------------------------------------\n");
	}
	
	public void Run() {
		System.out.println(">> �ȳ��ϼ���. ���� ����� ����ϱ�?");
		String msg = TransOpenAPI.Request("�ȳ��ϼ���. ���� ����� ����ϱ�?");		
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

