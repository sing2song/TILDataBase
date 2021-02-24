package bit.Server;

import java.sql.Timestamp;
import java.util.Calendar;

//14.32.18.42
public class Start {
	
	public static String GetDate(Calendar newtime) {
		String temp = String.format("%04d-%02d-%02d", 
				newtime.get(Calendar.YEAR) ,newtime.get(Calendar.MONTH), newtime.get(Calendar.DAY_OF_MONTH));
		return temp;
	}
	
	public static String GetTime(Calendar newtime) {
		String temp = String.format("%02d:%02d", 
				newtime.get(Calendar.HOUR_OF_DAY),newtime.get(Calendar.MINUTE));
		return temp;
	}
	
	public static void exam1() {
		Timestamp ntime = new Timestamp(System.currentTimeMillis());// = rs.getTimestamp(4);
		Calendar newtime = Calendar.getInstance();
		System.out.println(GetDate(newtime) + " " + GetTime(newtime));		
		newtime.setTime(ntime);
		System.out.println(GetDate(newtime) + " " + GetTime(newtime));		
	}
	
	//main부터 동작하는 쓰레드 : primary Thread
	public static void main(String[] args) {
		
		//exam1();
		
		Manager.getInstance().Run();
		
		/*
		//TcpIpMultiServer server = new TcpIpMultiServer();
		//server.Run();
		 
		try {
		AccountDB1 db = new AccountDB1();
		}
		catch(Exception ex) {			
		}
		*/	
	}
}
