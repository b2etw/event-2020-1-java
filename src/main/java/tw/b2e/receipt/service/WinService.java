package tw.b2e.receipt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.b2e.receipt.dao.crawler.WinInfoDao;
import tw.b2e.receipt.entity.WinInfo;
import tw.b2e.receipt.enums.PrizeEnum;
import tw.b2e.receipt.vo.CommandParam;

@Component
public class WinService implements BaseService {

	public final static String ACTION_COMMAND = "win";

	@Autowired
	private WinInfoDao winInfoDao;
	
	public final static String RESULT_FORMAT = "--------------------------期別:%s--------------------------\r\n" + 
			"特別獎:%s\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"特獎:%s\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"頭獎:%s\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"二獎\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"三獎\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"四獎\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"五獎\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"六獎\r\n" + 
			"%s\r\n" + 
			"----------------------------------------------------------------------\r\n" + 
			"增開六獎:%s\r\n" + 
			"%s\r\n" +
			"----------------------------------------------------------------------";

	public final static String YEAR_PARAM_NAME = "-y";
	public final static String MONTH_PARAM_NAME = "-m";	
	public final static String YEAR_WITH_MONTH_PARAM_NAME = "-ym";
	
	@Override
	public String execute(CommandParam param) {
		String period = null;
		if(param.getParam(YEAR_WITH_MONTH_PARAM_NAME) != null) {
			period = param.getParam(YEAR_WITH_MONTH_PARAM_NAME);
		}else if(param.getParam(YEAR_PARAM_NAME) != null 
				&& param.getParam(MONTH_PARAM_NAME) != null){
			period = param.getParam(YEAR_PARAM_NAME) + param.getParam(MONTH_PARAM_NAME);
		}
		
		List<String> resultArgs = new ArrayList<String>();
		WinInfo winInfo = winInfoDao.get(period);		
		resultArgs.add(winInfo.getPeriod());
		resultArgs.add(convertToString(winInfo.get(PrizeEnum.SPECIAL_FIRST)));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.SPECIAL_FIRST));
		resultArgs.add(convertToString(winInfo.get(PrizeEnum.SPECIAL_SECOND)));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.SPECIAL_SECOND));
		resultArgs.add(convertToString(winInfo.get(PrizeEnum.FIRST)));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.FIRST));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.SECOND));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.THIRD));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.FOURTH));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.FIFTH));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.SIXTH));
		resultArgs.add(convertToString(winInfo.get(PrizeEnum.EXTRA_SIXTH)));
		resultArgs.add(winInfo.getPrizeMemo(PrizeEnum.EXTRA_SIXTH));
		
		return String.format(RESULT_FORMAT, resultArgs.toArray());
	}
	
	private String convertToString(List<String> list) {
		if(list == null || list.size() == 0) {
			return "";
		}
		if(list.size() == 1) {
			return list.get(0);
		}
		
		StringBuffer result = new StringBuffer(list.get(0));
		for(int i = 1; i < list.size(); i++) {
			result.append("、").append(list.get(i));
		}
		return result.toString();
	}

}
