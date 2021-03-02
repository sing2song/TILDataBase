package example5;

import java.util.ArrayList;

public class Start {
	public static void main(String[] args) {
		System.out.println(">> Java");
		String retmsg = TransOpenAPI.Request("Java");	
		ArrayList<Book> books = Parser.Parsing(retmsg);
		
		//占쏙옙占� 占쏙옙占�
		System.out.println("-------------------------");
		for(Book b : books) {			
			b.Print();
			System.out.println("-------------------------");
		}		
    }
}

