package tw.b2e.receipt.format.service;

public class CheckServiceFormat {
	public final static String RESULT_PARSE_PERIOD_FAIL = "無傳入發票期別參數(-y,-m,-ym)或日期格式錯誤\r\n" + 
			"日期請使用民國年月，範例如下\r\n" + 
			"/receipt check -ym <民國年月份> -t <發票號碼>\r\n" + 
			"or\r\n" + 
			"/receipt check -y <民國年> -m <月份> -t <發票號碼>";
	
	public final static String RESULT_PARSE_RECEIPT_FAIL = "無傳入發票號碼或格式錯誤\r\n" + 
			"多張發票號碼請用,號分隔，範例如下\r\n" + 
			"/receipt check -ym <民國年月份> -t <發票號碼>\r\n" + 
			"or\r\n" + 
			"/receipt check -y <民國年> -m <月份> -t <發票號碼>";
	
	public final static String RESULT_TITLE_FORMAT = "--------------------------查詢期別:%s-------------------------- \r\n";
	public final static String RESULT_CNT_FORMAT = "中獎張數:%s \r\n";
	public final static String RESULT_AMOUNT_FORMAT = "獲得總獎金:$%s \r\n";
	public final static String RESULT_DETAIL_TITLE_FORMAT = "中獎明細:\r\n" + "  [獎項] / [獎金] / [發票號碼]\r\n";
	public final static String RESULT_DETAIL_ITEM_FORMAT = "  %s / $%s / %s\r\n";

}
