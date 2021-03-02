

import java.sql.Timestamp;
import java.util.Calendar;

public class BuyDrink {
	private int buydrinkId;
	private String memberId;
	private int drinkId;
	private int count;
	private int totalmoney;
	
	public BuyDrink(int buydrinkId, String memberId, int drinkId, int count, int totalmoney) {
		this.setBuydrinkId(buydrinkId);
		this.setMemberId(memberId);
		this.setDrinkId(drinkId);
		this.setCount(count);
		this.setTotalmoney(totalmoney);
	}
	
	public int getBuydrinkId() {
		return buydrinkId;
	}



	public void setBuydrinkId(int buydrinkId) {
		this.buydrinkId = buydrinkId;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public int getDrinkId() {
		return drinkId;
	}



	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public int getTotalmoney() {
		return totalmoney;
	}



	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}



	//메서드	
	public void Print() {
		System.out.print("[구매번호]" + buydrinkId+"  ");
		System.out.print("[회원 아이디]" + memberId+"  ");
		System.out.print("[음료 아이디]" + drinkId+"  ");
		System.out.println("[판매 수량]"+count+"  ");
		System.out.println("[총판매량]"+totalmoney);
	}

	
}
