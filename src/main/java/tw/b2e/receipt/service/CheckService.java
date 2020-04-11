package tw.b2e.receipt.service;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.b2e.receipt.common.NumberUtil;
import tw.b2e.receipt.common.PeriodParser;
import tw.b2e.receipt.common.ResultBuilder;
import tw.b2e.receipt.dao.crawler.WinInfoDao;
import tw.b2e.receipt.entity.Receipt;
import tw.b2e.receipt.entity.WinInfo;
import tw.b2e.receipt.enums.PrizeEnum;
import tw.b2e.receipt.format.service.CheckServiceFormat;
import tw.b2e.receipt.vo.CommandParam;

@Service
public class CheckService implements BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public final static String ACTION_COMMAND = "check";

	@Autowired
	private WinInfoDao winInfoDao;

	public final static String RECEIPT_TEXT_PARAM_NAME = "-t";
	public final static String RECEIPT_TEXT_SEPARATE = ",";

	public final static String RESULT_TITLE_FORMAT = CheckServiceFormat.RESULT_TITLE_FORMAT;
	public final static String RESULT_CNT_FORMAT = CheckServiceFormat.RESULT_CNT_FORMAT;
	public final static String RESULT_AMOUNT_FORMAT = CheckServiceFormat.RESULT_AMOUNT_FORMAT;
	public final static String RESULT_DETAIL_TITLE_FORMAT = CheckServiceFormat.RESULT_DETAIL_TITLE_FORMAT;
	public final static String RESULT_DETAIL_ITEM_FORMAT = CheckServiceFormat.RESULT_DETAIL_ITEM_FORMAT;

	@Override
	public String execute(CommandParam param) throws Exception {

		try {
			//取得期別參數
			String period = PeriodParser.parse(param);

			if (period == null) {
				return ResultBuilder.error(CheckServiceFormat.RESULT_PARSE_PERIOD_FAIL);
			}

			// 取得開獎資訊
			WinInfo winInfo = winInfoDao.get(period);

			// 取使用者傳入的發票號碼
			List<Receipt> receipts = parseReceipt(param);

			if (receipts == null || receipts.isEmpty()) {
				return ResultBuilder.error(CheckServiceFormat.RESULT_PARSE_RECEIPT_FAIL);
			}

			// 對獎
			List<Receipt> winReceipt = matchAllPrize(winInfo, receipts);

			// 產生回傳資料格式
			return ResultBuilder.ok(genResult(winInfo, winReceipt));
		} catch (ConnectException e) {
			return ResultBuilder.error(e.getMessage());
		}
	}

	/**
	 * 取傳入的發票號碼
	 * 
	 * @param param
	 * @return
	 */
	private List<Receipt> parseReceipt(CommandParam param) {
		List<Receipt> result = new ArrayList<Receipt>();

		if (param.getParam(CheckService.RECEIPT_TEXT_PARAM_NAME) == null) {
			return null;
		}

		String[] receipts = param.getParam(CheckService.RECEIPT_TEXT_PARAM_NAME).split(RECEIPT_TEXT_SEPARATE);
		for (String receipt : receipts) {
			if (receipt.length() == 10) {
				result.add(new Receipt(receipt.substring(2)));
			} else {
				result.add(new Receipt(receipt));
			}
		}

		return result;
	}

	/**
	 * 依據開獎資訊與傳入的發票清單進行對獎作業，並回傳中獎發票清單
	 * 
	 * @param winInfo
	 * @param originReceipts
	 * @return 中獎發票清單
	 */
	private List<Receipt> matchAllPrize(WinInfo winInfo, List<Receipt> originReceipts) {
		// 複製一份List避免異動到原始的List物件
		List<Receipt> result = new ArrayList<Receipt>(originReceipts);

		matchPrize(winInfo, PrizeEnum.SPECIAL_FIRST, result);
		matchPrize(winInfo, PrizeEnum.SPECIAL_SECOND, result);
		matchPrize(winInfo, PrizeEnum.EXTRA_SIXTH, result);

		List<String> baseWinNumber = winInfo.get(PrizeEnum.FIRST);
		matchPrize(baseWinNumber, PrizeEnum.SIXTH, result);
		// 因發票號碼末三碼已無符合中獎發票，後續不需再次對獎，故提前從List中移除，減少後續迴圈次數
		removeNotWinReceipt(result);
		matchPrize(baseWinNumber, PrizeEnum.FIFTH, result);
		// 因發票號碼末四碼已無符合中獎發票，後續不需再次對獎，故提前從List中移除，減少後續迴圈次數
		removeNotWinReceipt(result);
		matchPrize(baseWinNumber, PrizeEnum.FOURTH, result);
		matchPrize(baseWinNumber, PrizeEnum.THIRD, result);
		matchPrize(baseWinNumber, PrizeEnum.SECOND, result);
		matchPrize(baseWinNumber, PrizeEnum.FIRST, result);

		// 清除所有未中獎發票號碼
		removeNotWinReceipt(result);

		return result;
	}

	private void matchPrize(WinInfo winInfo, PrizeEnum prize, List<Receipt> receipts) {
		matchPrize(winInfo.get(prize), prize, receipts);
	}

	private void matchPrize(List<String> winNumbers, PrizeEnum prize, List<Receipt> receipts) {
		for (String winNumber : winNumbers) {
			// 除了特別獎、特獎、頭獎的中獎條件需8碼全符合之外，其餘皆比對末N碼，故依照獎別擷取末N碼號碼
			String suffix = prize.getMatchBeginIndex() == 8 ? winNumber
					: winNumber.substring(prize.getMatchBeginIndex());
			for (Receipt receipt : receipts) {
				if (receipt.getNumber().endsWith(suffix)) {
					if (!receipt.isWin() || (receipt.isWin() && receipt.getPrize().getAmount() < prize.getAmount())) {
						receipt.setPrize(prize);
					}
				}
			}
		}
	}

	/**
	 * 移除未中獎的發票
	 * 
	 * @param receipts
	 */
	private void removeNotWinReceipt(List<Receipt> receipts) {
		receipts.removeIf(value -> !value.isWin());
	}

	/**
	 * 
	 * @param receipts
	 * @return
	 */
	private long sumPrizeAmount(List<Receipt> receipts) {
		long result = 0;
		for (Receipt receipt : receipts) {
			result = result + (receipt.isWin() ? receipt.getPrize().getAmount() : 0);
		}
		return result;
	}

	/**
	 * 產生回傳資料
	 * 
	 * @param winInfo
	 * @param receipts
	 * @return
	 */
	private String genResult(WinInfo winInfo, List<Receipt> receipts) {
		StringBuffer result = new StringBuffer();

		result.append(String.format(RESULT_TITLE_FORMAT, winInfo.getPeriod()));
		result.append(String.format(RESULT_CNT_FORMAT, receipts.size()));
		result.append(String.format(RESULT_AMOUNT_FORMAT, NumberUtil.format4Thousandth(sumPrizeAmount(receipts))));

		// 有中獎才回傳明細區塊
		if (receipts.size() > 0) {
			result.append(RESULT_DETAIL_TITLE_FORMAT);
			for (Receipt receipt : receipts) {
				PrizeEnum prize = receipt.getPrize();
				result.append(String.format(RESULT_DETAIL_ITEM_FORMAT, prize.getTypeName(),
						NumberUtil.format4Thousandth(prize.getAmount()), receipt.getNumber()));
			}
		}
		return result.toString();
	}

}
