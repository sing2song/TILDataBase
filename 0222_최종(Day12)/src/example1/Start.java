package example1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Start {
	
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private ArrayList<Account> accounts1 = new ArrayList<Account>();
	
	public Start() {
		InitData();
	}
	
	private void InitData() {
		accounts.add(new Account(10, "ȫ�浿"));
		accounts.add(new Account(20, "�̱浿", 1000));
		accounts.add(new Account(30, "��浿", 3000));
		accounts.add(new Account(40, "�ֱ浿", 5000));
		accounts.add(new Account(50, "��浿", 6000));
	}
	
	//����ȭ : ��ü -> String -> byte[]
	public byte[] example1() {
		String str = "";
		for(Account ac : accounts) {
			str += ac.getAccid() + "#";
			str += ac.getName() + "#";
			str += ac.getBalance() + "#";
			str += ac.GetDate() + "#";
			str += ac.GetTime() + "@";
		}
		System.out.println(str);
		
		return str.getBytes();
	}
	
	//byte[] -> String -> ��ü
	public void example2(byte[] buffers) {
		
		String str = new String(buffers);
		System.out.println(str);
		
		System.out.println("@�� �̿��Ͽ� ���ڿ� �ڸ���");
		String[] filter = str.split("@");
		System.out.println("���尳�� : " + filter.length);
		try {
			for(String temp : filter) {
				System.out.println(temp);//10#ȫ�浿#0#2021-01-22
				String[] filter1 = temp.split("#");
				
				int accid = Integer.parseInt( filter1[0]);
				String name = filter1[1];
				int balance = Integer.parseInt( filter1[2]);
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");				
				Date date = formatter.parse(filter1[3] + " " + filter1[4]);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				accounts1.add(new Account(accid, name, balance, cal));				
			}			
		}
		catch(Exception ex) {}
		
	}
	
	public void example2_Print() {
		for(Account acc : accounts1) {
			acc.Print();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Start s = new Start();
		byte[] bstr = s.example1();
		System.out.println(bstr);
		//---------------------------------------------
		s.example2(bstr);
		s.example2_Print();
	}
}



