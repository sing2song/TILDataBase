//AccountIO.java



import java.util.Calendar;

public class AccountIO {
	private int accnum;
	private int input;
	private int output;
	private int balance;
	Calendar cd;	
	
	//생성자를 통한 저장
	public AccountIO(int accnum,int input,int output,int balance) {
		this.accnum = accnum;
		this.input = input;
		this.output = output;
		this.balance = balance;
		cd = Calendar.getInstance();
	}
	
	//get메서드
	public int getAccNum() { 
		return accnum;
	}
	
	public String GetDate() {
		String temp = String.format("%04d-%02d-%02d", 
				cd.get(Calendar.YEAR) ,cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH));
		return temp;
	}
	
	public String GetTime() {
		String temp = String.format("%02d:%02d:%02d", 
				cd.get(Calendar.HOUR_OF_DAY),cd.get(Calendar.MINUTE), cd.get(Calendar.SECOND));
		return temp;
	}
	
	//출력기능(단일라인으로 출력...)
	public void Print() {
		System.out.print("[" + accnum + "] ");
		System.out.print(input + "\t");
		System.out.print(output + "\t");
		System.out.print(balance + "\t");
		System.out.print(GetDate() + "\t");
		System.out.println(GetTime());
	}
}
