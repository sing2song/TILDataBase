
import java.util.Scanner;

//BitGlobal.java

public class BitGlobal {
	static Scanner scan = new Scanner(System.in);
	
	//�޼���
	public static void Logo() {
		System.out.println("*************************************************");
		System.out.println("[MySQL �Ǳ����]");
		System.out.println("by. �̼ۿ�");
		System.out.println("*************************************************\n");
		Pause();
	}
	
	public static void Ending() {
		System.out.println("*************************************************");
		System.out.println(" ���α׷��� �����մϴ�.");
		System.out.println("*************************************************");
	}

	//Pause ���ߴ� ���
	public static void Pause() {
		System.out.print("����Ű�� ��������....");
		scan.nextLine();		
	}

	//�Է� ��� �Լ�
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
















