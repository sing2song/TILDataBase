package sample5;

import org.json.simple.JSONObject;


public class Book {
	private String title;
	private String link;
	private String image;
	private String author;
	private int price;	
	private int discount;
	private String publisher;
	private String pubdate;
	private String isbn;
	private String description; 
	
	public Book(String title, String link, String image, String author, int price, int discount, String publisher,String pubdate, 	String isbn, String description) {
		this.title = title;
		this.link = link;
		this.image = image;
		this.author = author;
		this.price = price;
		this.discount = discount;
		this.publisher = publisher;
		this.pubdate = pubdate;
		this.isbn = isbn;
		this.description = description;
	}
	
	public static Book Parsing(JSONObject personObject) {		
		String title = (String)personObject.get("title"); 
		String link = (String)personObject.get("link"); 
		String image = (String)personObject.get("image"); 
		String author = (String)personObject.get("author"); 
		int price = Integer.parseInt((String)personObject.get("price")); 
		int discount;
		try {
			discount = 	Integer.parseInt((String)personObject.get("discount"));
		}
		catch(Exception ex) {
			discount = price;
		}
		String publisher = (String)personObject.get("publisher"); 
		String pubdate = (String)personObject.get("pubdate"); 
		String isbn = (String)personObject.get("isbn"); 
		String description = (String)personObject.get("description"); 
		return new Book(title, link, image, author, price, discount, publisher, pubdate, isbn, description);
	}

	public void Print() {
		System.out.println("[title]" +  title);
		System.out.println("[link]" +  link);
		System.out.println("[image]" +  image);
		System.out.println("[author]" +  author);
		System.out.println("[price]" +  price);
		System.out.println("[discount]" +  discount);
		System.out.println("[publisher]" +  publisher);
		System.out.println("[pubdate]" +  pubdate);
		System.out.println("[isbn]" +  isbn);
		System.out.println("[description]" +  description);		
	}

	private static String Utf8toEucKr(String src) {
		try {
			String retmsg = new String(src.getBytes("euc-kr"), "x-windows-949");
			return retmsg;			
		}
		catch(Exception ex) {
			return "";
		}

		/*
		Charset euckrCharset = Charset.forName("euc-kr");
		ByteBuffer byteBuffer = euckrCharset.encode(src);
		byte[] euckrStringBuffer = new byte[byteBuffer.remaining()];
		byteBuffer.get(euckrStringBuffer);
		return new String(euckrStringBuffer);
		*/
	}
}
