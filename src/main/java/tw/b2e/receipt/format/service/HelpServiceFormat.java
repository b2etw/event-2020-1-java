package tw.b2e.receipt.format.service;

public class HelpServiceFormat {
	public static final String RESULT_FORMAT = "用法: /receipt [win] [check]\r\n" + 
			"[win] 查詢開獎發票號碼\r\n" + 
			"-y <民國年>\r\n" + 
			"-m <月份>\r\n" + 
			"-ym <民國年月>\r\n" + 
			"範例:/receipt win -ym 10902\r\n" + 
			"\r\n" + 
			"[check] 進行自動對獎\r\n" + 
			"-y <民國年>\r\n" + 
			"-m <月份>\r\n" + 
			"-ym <民國年月>\r\n" + 
			"-i <發票號碼，多張已\",\"分隔>\r\n" + 
			"範例:/receipt check -ym 10902 -i AB00001234, AC08199453";
}
