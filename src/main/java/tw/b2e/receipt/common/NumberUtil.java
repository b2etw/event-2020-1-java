package tw.b2e.receipt.common;

public class NumberUtil {
	public static String format4Thousandth(Object num) {
		return String.format("%,d", num);
	}
}
