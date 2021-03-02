import java.util.Scanner;

//실행의 흐름을 담당
public class App {

	//싱글톤 패턴 코드 -------------------------------------------------
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
	//---------------------------------------------------------------
	
	private Manager manager = new Manager();
	
	//메서드
	//초기화 영역
	private void Init() {
		BitGlobal.Logo();
	}
	
	//반복적 실행 - 엔진
	public void Run() {
		while(true) {		
			Scanner sc = new Scanner(System.in);
			System.out.println("===================================================================");
			System.out.println("[음료 구매 시스템]");
			System.out.println("[0]프로그램 종료");
			System.out.println("[1]회원 등록 [2]회원 전체 출력 [3]음료수 등록 [4]음료수 전체출력 [5]등급 테이블 전체 출력");
			System.out.println("[6]구매테이블 출력 [7]음료수 구매 [8]회원 구매 정보 검색 [9]가장 많이 판매된 음료수 정보 출력");
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
				System.out.println("잘못 입력하셨습니다!");
				break;
			}
			BitGlobal.Pause();
		}
	}
	
	//종료처리 영역
	public void Exit() {
		BitGlobal.Ending();
	}
}












