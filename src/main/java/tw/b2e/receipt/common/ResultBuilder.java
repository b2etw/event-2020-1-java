package tw.b2e.receipt.common;

public class ResultBuilder {
	private ResultBuilder() {};
	
	public static String ok(String text) {
		return text;		
	}
	
	
	public static String error(String text) {
		return String.format("[ERROR] %s", text);		
	}
}
