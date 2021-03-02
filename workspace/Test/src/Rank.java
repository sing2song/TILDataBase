

import java.sql.Timestamp;
import java.util.Calendar;

public class Rank {
	private String levelname;
	private int low;
	private int high;
	
		
	public Rank(String levelname, int low, int high) {
		this.setLevelname(levelname);
		this.setLow(low);
		this.setHigh(high);
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}



	public int getLow() {
		return low;
	}



	public void setLow(int low) {
		this.low = low;
	}



	public int getHigh() {
		return high;
	}



	public void setHigh(int high) {
		this.high = high;
	}



	//메서드	
	public void Print() {
		System.out.print("[등급이름]" + levelname+"  ");
		System.out.print("[low]" + low+"  ");
		System.out.print("[high]" + high);
	}

	
}
