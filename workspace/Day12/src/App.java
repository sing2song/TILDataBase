
//실행의 흐름을 담당
public class App {

	//싱글톤 패턴 코드 -----------------------------------------------
	//생성자 은닉!
	private App() {
		Init();
	}
	
	//자신의 static 객체 생성
	private static App Singleton = new App();
	
	//내부적으로 생성된 자신의 객체를 외부에 노출 메서드
	public static App getInstance() {
		return Singleton;
	}
	//------------------------------------------------------------	
	
	//Run()함수에서만 사용하기는 하나 일단 선언해줌
	private Bank bank = Bank.getInstance();
	
	//초기화 영역
	private void Init() {
		BitGlobal.Logo();
	}
	
	//반복적 실행 - 엔진
	public void Run() {
		while(true) {
			bank.PrintAll();
			switch(BitGlobal.MenuPrint()) {
			case '0': return;
			case '1': bank.MakeAccount(); break;
			case '2': bank.SelectAccount(); break;
			case '3': bank.InputMoney(); break;
			case '4': bank.OutputMoney(); break;
			case '5': bank.DeleteAccount(); break;			
			}	
			//멈춤
			BitGlobal.Pause();
		}
	}
	
	//종료처리 영역
	public void Exit() {
		BitGlobal.Ending();
	}
}
