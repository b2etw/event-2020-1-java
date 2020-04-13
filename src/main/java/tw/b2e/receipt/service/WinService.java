package tw.b2e.receipt.service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.b2e.receipt.common.PeriodParser;
import tw.b2e.receipt.common.ResultBuilder;
import tw.b2e.receipt.dao.crawler.WinInfoDao;
import tw.b2e.receipt.entity.WinInfo;
import tw.b2e.receipt.enums.PrizeEnum;
import tw.b2e.receipt.format.service.WinServiceFormat;
import tw.b2e.receipt.vo.CommandParam;

@Component
public class WinService implements BaseService {

	public final static String ACTION_COMMAND = "win";

	@Autowired
	private WinInfoDao winInfoDao;
	public final static String RESULT_FORMAT = WinServiceFormat.RESULT_FORMAT;

	@Override
	public String execute(CommandParam param) throws Exception {

		try {
			//取得期別參數
			String period = PeriodParser.parse(param);
	
			if (period == null) {
				return ResultBuilder.error(WinServiceFormat.RESULT_PARSE_PERIOD_FAIL);
			}
			
			//取得開獎資訊
			WinInfo winInfo = winInfoDao.get(period);
			
			// 產生回傳資料格式
			return ResultBuilder.ok(genResult(winInfo));
		} catch (ConnectException e) {
			return ResultBuilder.error(e.getMessage());
		}
	}

	private String genResult(WinInfo winInfo) {
		List<String> resultArgs = new ArrayList<String>();

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
		if (list == null || list.size() == 0) {
			return "";
		}
		if (list.size() == 1) {
			return list.get(0);
		}

		StringBuffer result = new StringBuffer(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			result.append("、").append(list.get(i));
		}
		return result.toString();
	}
}
