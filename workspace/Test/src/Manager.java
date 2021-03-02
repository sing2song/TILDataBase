import java.util.ArrayList;

//bank.java


public class Manager {
	//Database저장
	private BitDB  db;

	//생성자
	public Manager() {
		try {
			db = new BitDB();
		}
		catch(Exception ex) {
			System.out.println("데이터 베이스 접속 에러");			
		}
	}

	//메서드	
	//1.회원등록
	public void InsertMember() {
		try {
			//1. 요청정보(client)
			String memberID = BitGlobal.InputString("회원 아이디");
			if(IsMemberIDCheck(memberID)==true)
				throw new Exception("중복된 회원 아이디가 존재합니다.");

			String name = BitGlobal.InputString("이름");

			//2. 저장.(Server)
			if(db.InsertMember_DB(memberID,  name)==false)
				throw new Exception("회원 등록 실패");

			//3. 결과.(client)
			System.out.println("회원 등록 성공!");
		}
		catch(Exception ex) {
			System.out.println("[회원등록실패] " + ex.getMessage());
		}
	}
	//+회원아이디체크
	private boolean IsMemberIDCheck(String memberId) {
		return db.MemberIDCheck(memberId);
	}

	//2.회원전체 출력!
	public void SelectAllMember() {
		ArrayList<Member> members = db.SelectAllMember_DB();
		System.out.println("[전체 회원 수] " + members.size() + "개");
		for(Member m : members) {
			m.Print();
		}
		System.out.println();
	}

	//3.음료수등록
	public void InsertDrink() {
		try {
			//1. 요청정보(client)
			String name = BitGlobal.InputString("음료수명");
			int price = BitGlobal.InputNumber("가격");

			//2. 저장.(Server)
			if(db.InsertDrink_DB(name,  price)==false)
				throw new Exception("음료수 등록 실패");

			//3. 결과.(client)
			System.out.println("음료수 등록 성공!");
		}
		catch(Exception ex) {
			System.out.println("[음료수등록실패] " + ex.getMessage());
		}
	}

	//4.음료수전체 출력!
	public void SelectAllDrink() {
		ArrayList<Drink> drinks = db.SelectAllDrink_DB();
		System.out.println("[전체 음료수 수] " + drinks.size() + "개");
		for(Drink d : drinks) {
			d.Print();
		}
		System.out.println();
	}
	
	//5.등급 전체 출력
	public void SelectAllRank() {
		ArrayList<Rank> ranks = db.SelectAllRank_DB();
		for(Rank r : ranks) {
			r.Print();
		}
		System.out.println();
	}

	//6.구매테이블 출력
	public void SelectAllBuyDrink() {
		ArrayList<BuyDrink> bds = db.SelectAllBuyDrink_DB();
		System.out.println("[전체 구매 수] " + bds.size() + "개");
		for(BuyDrink bd : bds) {
			bd.Print();
		}
		System.out.println();
	}
	
	//7.음료수구매
	public void BuyDrink() {
		try {
			//1. client
			String memberId = BitGlobal.InputString("회원 아이디 입력");
			int drinkId = BitGlobal.InputNumber("음료수 번호 입력");
			int count = BitGlobal.InputNumber("수량");
			
			//2. server 
			boolean result = db.Update(memberId,  drinkId, count);

			//3. client
			if(result == false)
				throw new Exception("");
			else			
				System.out.println("음료수 구매가 완료되었습니다.");
		}
		catch(Exception ex) {
			System.out.println("[음료수 구매오류]" + ex.getMessage());
		}
	}

	//8.회원 구매정보 검색
	public void SelectBuyDrink() {
		String memberId = BitGlobal.InputString("회원 아이디 입력");
			
		Member member = new Member();	//?????
		ArrayList<BuyDrink> bds = new ArrayList<BuyDrink>();		
		if(db.SelectBuyDrink_DB(memberId,member,bds) == false) {
			System.out.println("데이터가 존재하지 않습니다");
			return;
		}
		//회원정보
		member.Print();
		System.out.println("------------------------------------------");
		for(BuyDrink bd : bds) {
			System.out.println("[구매한 음료수 총 수량]" +bd.getCount());
			System.out.println("[구매한 총 합]"+bd.getTotalmoney()+"원");
		}
		System.out.println();
		
		
		System.out.println();
	}
	
	//9. 가장 많이 판매된 음료수 정보 출력
	public void SelectBestSeller() {
		Drink drink = db.SelectBestSeller_DB();
		System.out.println("[음료수명]"+drink.getName());
		System.out.println("[가격]"+drink.getPrice());
		System.out.println("[총 판매수량]"+drink.getCount());		
	}
	
	
	
}









