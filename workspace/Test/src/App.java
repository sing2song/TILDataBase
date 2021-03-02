import java.util.Scanner;

//������ �帧�� ���
public class App {

	//�̱��� ���� �ڵ� -------------------------------------------------
	//������ ����!
	private App() {
		Init();
	}
	//�ڽ��� static ��ü ����
	private static App Singleton = new App();
	
	//���������� ������ �ڽ��� ��ü�� �ܺο� ���� �޼���
	public static App getInstance() {
		return Singleton;
	}
	//---------------------------------------------------------------
	
	private Manager manager = new Manager();
	
	//�޼���
	//�ʱ�ȭ ����
	private void Init() {
		BitGlobal.Logo();
	}
	
	//�ݺ��� ���� - ����
	public void Run() {
		while(true) {		
			Scanner sc = new Scanner(System.in);
			System.out.println("===================================================================");
			System.out.println("[���� ���� �ý���]");
			System.out.println("[0]���α׷� ����");
			System.out.println("[1]ȸ�� ��� [2]ȸ�� ��ü ��� [3]����� ��� [4]����� ��ü��� [5]��� ���̺� ��ü ���");
			System.out.println("[6]�������̺� ��� [7]����� ���� [8]ȸ�� ���� ���� �˻� [9]���� ���� �Ǹŵ� ����� ���� ���");
			System.out.print(">>");
			int menu = Integer.parseInt(sc.nextLine().trim());
			System.out.println("===================================================================");

			switch(menu) {
			case 0: Exit(); System.exit(0);
			case 1: manager.InsertMember();	break;
			case 2: manager.SelectAllMember(); break;
			case 3: manager.InsertDrink(); 	break;
			case 4: manager.SelectAllDrink(); 	break;
			case 5: manager.SelectAllRank(); break;
			case 6: manager.SelectAllBuyDrink(); break;
			case 7: manager.BuyDrink(); break;
			case 8: manager.SelectBuyDrink(); break;
			case 9: manager.SelectBestSeller(); break;
			
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�!");
				break;
			}
			BitGlobal.Pause();
		}
	}
	
	//����ó�� ����
	public void Exit() {
		BitGlobal.Ending();
	}
}












