package tw.b2e.receipt.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.b2e.receipt.common.NumberUtil;
import tw.b2e.receipt.entity.Receipt;
import tw.b2e.receipt.enums.PrizeEnum;
import tw.b2e.receipt.vo.CommandParam;

@SpringBootTest
class CheckServiceTests {

	@Autowired
	private CheckService checkService;

	private CommandParam param;

	@BeforeEach
	public void before() {
		param = new CommandParam();
		param.setAction("check");
	}

	@Test
	void Should_ShowPrizeItem_When_ParamIsWinReceipt() {
		String winReceiptNumber = "12620024";

		param.putParam(CheckService.YEAR_PARAM_NAME, "109");
		param.putParam(CheckService.MONTH_PARAM_NAME, "02");
		param.putParam(CheckService.RECEIPT_TEXT_PARAM_NAME, winReceiptNumber);

		String result = checkService.execute(param);

		StringBuffer answer = new StringBuffer();
		answer.append(String.format(CheckService.RESULT_TITLE_FORMAT, "109年01 ~02月"));
		answer.append(String.format(CheckService.RESULT_CNT_FORMAT, "1"));
		answer.append(String.format(CheckService.RESULT_AMOUNT_FORMAT,
				NumberUtil.format4Thousandth(PrizeEnum.SPECIAL_FIRST.getAmount())));
		answer.append(CheckService.RESULT_DETAIL_TITLE_FORMAT);
		answer.append(String.format(CheckService.RESULT_DETAIL_ITEM_FORMAT, PrizeEnum.SPECIAL_FIRST.getTypeName(),
				NumberUtil.format4Thousandth(PrizeEnum.SPECIAL_FIRST.getAmount()), winReceiptNumber));

		assertEquals(answer.toString(), result);
	}

	@Test
	void Should_ShowPrizeItems_When_ParamIsWinReceipts() {
		List<Receipt> winReceipts = new ArrayList<Receipt>();
		winReceipts.add(new Receipt("12620024", PrizeEnum.SPECIAL_FIRST));
		winReceipts.add(new Receipt("39793895", PrizeEnum.SPECIAL_SECOND));
		winReceipts.add(new Receipt("00954061", PrizeEnum.THIRD));
		winReceipts.add(new Receipt("11111947", PrizeEnum.SIXTH));
		winReceipts.add(new Receipt("99990007", PrizeEnum.EXTRA_SIXTH));
		
		List<Receipt> notWinReceipts = new ArrayList<Receipt>();
		notWinReceipts.add(new Receipt("99999999"));
		notWinReceipts.add(new Receipt("00000001"));

		param.putParam(CheckService.YEAR_PARAM_NAME, "109");
		param.putParam(CheckService.MONTH_PARAM_NAME, "02");
		param.putParam(CheckService.RECEIPT_TEXT_PARAM_NAME, join(winReceipts, notWinReceipts).toString());

		String result = checkService.execute(param);

		StringBuffer answer = new StringBuffer();
		answer.append(String.format(CheckService.RESULT_TITLE_FORMAT, "109年01 ~02月"));
		answer.append(String.format(CheckService.RESULT_CNT_FORMAT, winReceipts.size()));
		answer.append(
				String.format(CheckService.RESULT_AMOUNT_FORMAT, NumberUtil.format4Thousandth(sumAmount(winReceipts))));
		answer.append(CheckService.RESULT_DETAIL_TITLE_FORMAT);

		for (Receipt receipt : winReceipts) {
			answer.append(String.format(CheckService.RESULT_DETAIL_ITEM_FORMAT, receipt.getPrize().getTypeName(),
					NumberUtil.format4Thousandth(receipt.getPrize().getAmount()), receipt.getNumber()));
		}

		assertEquals(answer.toString(), result);
	}
	
	@Test
	void Should_ShowNotWinInfo_When_ParamIsNotWinReceipts() {
		
		List<Receipt> notWinReceipts = new ArrayList<Receipt>();
		notWinReceipts.add(new Receipt("99999999"));
		notWinReceipts.add(new Receipt("00000001"));

		param.putParam(CheckService.YEAR_PARAM_NAME, "109");
		param.putParam(CheckService.MONTH_PARAM_NAME, "02");
		param.putParam(CheckService.RECEIPT_TEXT_PARAM_NAME, join(notWinReceipts).toString());

		String result = checkService.execute(param);

		StringBuffer answer = new StringBuffer();
		answer.append(String.format(CheckService.RESULT_TITLE_FORMAT, "109年01 ~02月"));
		answer.append(String.format(CheckService.RESULT_CNT_FORMAT, 0));
		answer.append(
				String.format(CheckService.RESULT_AMOUNT_FORMAT, 0));
		
		assertEquals(answer.toString(), result);
	}

	@SafeVarargs
	private StringBuffer join(List<Receipt>... lists) {
		return join(",", lists);
	}

	@SafeVarargs
	private StringBuffer join(String separate, List<Receipt>... lists) {
		StringBuffer result = new StringBuffer();
		for (List<Receipt> list : lists) {
			for (Receipt receipt : list) {
				if (result.length() > 0) {
					result.append(separate).append(receipt.getNumber());
				} else {
					result.append(receipt.getNumber());
				}
			}
		}
		return result;
	}

	private long sumAmount(List<Receipt> receipts) {
		long result = 0;
		for (Receipt receipt : receipts) {
			if (receipt.isWin())
				result = result + receipt.getPrize().getAmount();
		}

		return result;

	}
}
