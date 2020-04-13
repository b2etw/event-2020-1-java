package tw.b2e.receipt.common;

import tw.b2e.receipt.vo.CommandParam;

public class PeriodParser {
	
	public final static String YEAR_PARAM_NAME = "-y";
	public final static String MONTH_PARAM_NAME = "-m";	
	public final static String YEAR_WITH_MONTH_PARAM_NAME = "-ym";
		
	public static String parse(CommandParam param){	
		
		String period = null;
		if(param.getParam(YEAR_WITH_MONTH_PARAM_NAME) != null) {
			period = param.getParam(YEAR_WITH_MONTH_PARAM_NAME);
		}else if(param.getParam(YEAR_PARAM_NAME) != null 
				&& param.getParam(MONTH_PARAM_NAME) != null){
			period = param.getParam(YEAR_PARAM_NAME) + param.getParam(MONTH_PARAM_NAME);
		}
		
		if(period == null || "".equals(period))
			return null;
		
		//判斷期別參數是否"不為民國年"
		if(isNotRepublicYear(period)) {
			if(isADYear(period)) {//推測傳入值為西元年，自動轉換為民國年
				period = String.valueOf(Integer.valueOf(period) - 191100);
			}			
		}
		
		return isRepublicYear(period) ? period : null;	
	}
	
	private static boolean isNotRepublicYear(String period) {	
		return !isRepublicYear(period);
	}
	
	private static boolean isRepublicYear(String period) {		
		try {
			DateUtil.parseDate(String.valueOf(Integer.valueOf(period) + 191100) + "01", DateUtil.FORMAT_DATE);
		}catch (Exception e) {
			return false;
		}		
		return period.length() == 5;
	}
	
	private static boolean isADYear(String period) {
		return period.length() == 6;
	}
}
