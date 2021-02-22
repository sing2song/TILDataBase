
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Account {
	private int accid;//계좌번호
	private String name;//이름
	private int balance;//잔액
	private Calendar newtime;//개설일시

	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
	SimpleDateFormat format2 = new SimpleDateFormat ( "HH:mm");

	//생성자
	public Account(int accid,String name) {
		this(accid,name,0);
	}

	public Account(int accid,String name,int balance) {
		this.accid=accid;
		this.name=name;
		this.balance=balance;
		this.newtime=Calendar.getInstance();
	}	
	
	public Account(int accid, String name, int balance, Date dt) {
		this(accid,name,balance);
		/*
		this.newtime=Calendar.getInstance();
		this.newtime.set(dt.getYear(),dt.getMonth(),dt.getDay());//년월일 처리
		*/
		//Calendar과 Date 변환처리코드 필요
		this.newtime=Calendar.getInstance();
		this.newtime.setTime(dt);
	}

	//getter&setter
	public int getAccid() {
		return accid;
	}

	private void setAccid(int accid) {
		this.accid = accid;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	@SuppressWarnings("unused")
	private void setBalance(int balance) {
		this.balance = balance;
	}

	public Calendar getNewtime() {
		return newtime;
	}

	@SuppressWarnings("unused")
	private void setNewtime(Calendar newtime) {
		this.newtime = newtime;
	}


	//기능 메서드
	public void InputMoney(int money) throws Exception {
		if(money<0) 
			throw new Exception("잘못된 금액입니다.");
		balance += money;
	}


	public void OutputMoney(int money) throws Exception {
		if(money<0) 
			throw new Exception("잘못된 금액입니다.");
		if(money > balance) 
			throw new Exception("잔액이 부족합니다.");
		balance -= money;
	}


	public String GetDate() {
		String temp = String.format("%04d-%02d-%02d", 
				newtime.get(Calendar.YEAR) ,newtime.get(Calendar.MONTH), newtime.get(Calendar.DAY_OF_MONTH));
		return temp;
	}

	public String GetTime() {
		String temp = String.format("%02d:%02d", 
				newtime.get(Calendar.HOUR_OF_DAY) ,newtime.get(Calendar.MINUTE));
		return temp;
	}


	public void Println() {
		System.out.println("[계좌번호]" + accid);
		System.out.println("[이름]" + name);
		System.out.println("[잔액]" + balance + "원");
		System.out.println("[개설일자] " + GetDate());
		System.out.println("[개설시간] " + GetTime());
	}

	public void Print() {		
		System.out.print("[" + accid + "] ");
		System.out.print(name + " ");
		System.out.print(balance + "원 ");
		System.out.print(GetDate() + " ");
		System.out.println(GetTime());
	}

	public void setData(int accid,String name, int balance, Date ndate) {
		this.accid=accid;
		this.name=name;
		this.balance=balance;
		this.newtime=Calendar.getInstance();
		this.newtime.setTime(ndate);
	}



}
