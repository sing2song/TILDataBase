
import java.util.Scanner;

//BitGlobal.java

public class BitGlobal {
	static Scanner scan = new Scanner(System.in);
	
	//�޼���
	public static void Logo() {
		System.out.println("*************************************************");
		System.out.println(" Java ������ ����");
		System.out.println(" 2021.02.09");
		System.out.println(" �迭�ڷᱸ���� Ȱ���� ���� ���� ���α׷�");
		System.out.println(" ��â��");
		System.out.println("*************************************************\n");
		Pause();
	}
	
	public static void Ending() {
		System.out.println("*************************************************");
		System.out.println(" ���α׷��� �����մϴ�.");
		System.out.println("*************************************************");
	}

	//�޴�
	public static char MenuPrint() {
		System.out.println("*************************************************");
		System.out.println(" [0] ���α׷� ����");
		System.out.println(" [1] ���� ����");
		System.out.println(" [2] ���� �˻�");
		System.out.println(" [3] �Ա�");
		System.out.println(" [4] ���");
		System.out.println(" [5] ���� ����");
		System.out.println("*************************************************");
		System.out.print(" >> ");
		return scan.nextLine().charAt(0);
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
















