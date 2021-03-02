package example4;


public class Start {
	public static void main(String[] args) {
		String retmsg = TransOpenAPI.Request("Java");	
		System.out.println(Parser.Parsing(retmsg));
    }

}

