
import java.util.Scanner;

//BitGlobal.java

public class BitGlobal {
	static Scanner scan = new Scanner(System.in);
	
	//메서드
	public static void Logo() {
		System.out.println("*************************************************");
		System.out.println("[MySQL 실기시험]");
		System.out.println("by. 이송원");
		System.out.println("*************************************************\n");
		Pause();
	}
	
	public static void Ending() {
		System.out.println("*************************************************");
		System.out.println(" 프로그램을 종료합니다.");
		System.out.println("*************************************************");
	}

	//Pause 멈추는 기능
	public static void Pause() {
		System.out.print("엔터키를 누르세요....");
		scan.nextLine();		
	}

	//입력 기능 함수
	public static int InputNumber(String msg) {
		System.out.print(msg + " : ");
		return Integer.parseInt(scan.nextLine());
	}
	
	public static String InputString(String msg) {
		System.out.print(msg + " : ");
		return scan.nextLine();
	}
	
	public static char InputChar(String msg) {
		System.out.print(msg + " : ");
		//String msg1= scan.nextLine();
		//char ch = msg1.charAt(0);
		//return ch;
		return scan.nextLine().charAt(0);
	}
}
















