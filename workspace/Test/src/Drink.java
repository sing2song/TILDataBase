

import java.sql.Timestamp;
import java.util.Calendar;

public class Drink {
	private int drinkID;
	private String name;
	private int price;
	private int count;
	
		
	public Drink(int drinkID, String name, int price,int count) {
		this.setDrinkID(drinkID);
		this.setName(name);
		this.setPrice(price);
		this.setCount(count);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getDrinkID() {
		return drinkID;
	}

	public void setDrinkID(int drinkID) {
		this.drinkID = drinkID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	//메서드	
	public void Print() {
		System.out.print("[음료수아이디]" + drinkID+"  ");
		System.out.print("[음료수명]" + name+"  ");
		System.out.print("[가격]" + price+"  ");
		System.out.println("[판매 수량]"+count);
	}

	
}
