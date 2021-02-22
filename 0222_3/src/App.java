

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
	
	private Bank bank = new Bank();
	
	//�޼���
	//�ʱ�ȭ ����
	private void Init() {
		BitGlobal.Logo();
	}
	
	//�ݺ��� ���� - ����
	public void Run() {
		while(true) {		
			bank.PrintAll(); 
			switch(BitGlobal.MenuPrint()) {
			case '0': return;
			case '1': bank.MakeAccount();	break;
			case '2': bank.SelectAccount(); break;
			case '3': bank.InputMoney(); 	break;
			case '4': bank.OutputMoney(); 	break;
			case '5': bank.DeleteAccount(); break;
			}
			BitGlobal.Pause();
		}
	}
	
	//����ó�� ����
	public void Exit() {
		BitGlobal.Ending();
	}
}












