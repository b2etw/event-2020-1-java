package tw.b2e.receipt.dao.crawler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tw.b2e.receipt.entity.WinInfo;
import tw.b2e.receipt.enums.PrizeEnum;

@SpringBootTest
class WinInfoDaoTests {

	private static WinInfo answerInfo;
	
	@Autowired
	private WinInfoDao winInfoDao;

	@BeforeEach
	public void before() {
		answerInfo = new WinInfo();
		answerInfo.setPrizeMemo(PrizeEnum.SPECIAL_FIRST, "同期統一發票收執聯8位數號碼與特別獎號碼相同者獎金1,000萬元");
		answerInfo.setPrizeMemo(PrizeEnum.SPECIAL_SECOND, "同期統一發票收執聯8位數號碼與特獎號碼相同者獎金200萬元");		
		answerInfo.setPrizeMemo(PrizeEnum.FIRST, "同期統一發票收執聯8位數號碼與頭獎號碼相同者獎金20萬元");
		answerInfo.setPrizeMemo(PrizeEnum.SECOND, "同期統一發票收執聯末7 位數號碼與頭獎中獎號碼末7 位相同者各得獎金4萬元");
		answerInfo.setPrizeMemo(PrizeEnum.THIRD, "同期統一發票收執聯末6 位數號碼與頭獎中獎號碼末6 位相同者各得獎金1萬元");
		answerInfo.setPrizeMemo(PrizeEnum.FOURTH, "同期統一發票收執聯末5 位數號碼與頭獎中獎號碼末5 位相同者各得獎金4千元");
		answerInfo.setPrizeMemo(PrizeEnum.FIFTH, "同期統一發票收執聯末4 位數號碼與頭獎中獎號碼末4 位相同者各得獎金1千元");
		answerInfo.setPrizeMemo(PrizeEnum.SIXTH, "同期統一發票收執聯末3 位數號碼與 頭獎中獎號碼末3 位相同者各得獎金2百元");
		answerInfo.setPrizeMemo(PrizeEnum.EXTRA_SIXTH, "同期統一發票收執聯末3位數號碼與增開六獎號碼相同者各得獎金2百元");
	}

	@Test
	void Should_GetWinReceipt_When_ParamIs10901() throws IOException {
		String period = "10901";

		answerInfo.setPeriod("109年01 ~02月");
		answerInfo.add(PrizeEnum.SPECIAL_FIRST, genAnswerDetail("12620024"));
		answerInfo.add(PrizeEnum.SPECIAL_SECOND, genAnswerDetail("39793895"));
		answerInfo.add(PrizeEnum.FIRST, genAnswerDetail("67913945", "09954061", "54574947"));
		answerInfo.add(PrizeEnum.EXTRA_SIXTH, genAnswerDetail("007"));
		
		WinInfo winInfo = winInfoDao.get(period);
		for (PrizeEnum key : answerInfo.keySet()) {
			assertEquals(answerInfo.size(), winInfo.size());
			assertEquals(answerInfo.get(key), winInfo.get(key));
			assertEquals(answerInfo.getPrizeMemo(key), winInfo.getPrizeMemo(key));
		}
	}
	
	@Test
	void Should_GetWinReceipt_When_ParamIs10902() throws IOException {
		String period = "10902";

		answerInfo.setPeriod("109年01 ~02月");
		answerInfo.add(PrizeEnum.SPECIAL_FIRST, genAnswerDetail("12620024"));
		answerInfo.add(PrizeEnum.SPECIAL_SECOND, genAnswerDetail("39793895"));
		answerInfo.add(PrizeEnum.FIRST, genAnswerDetail("67913945", "09954061", "54574947"));
		answerInfo.add(PrizeEnum.EXTRA_SIXTH, genAnswerDetail("007"));
		
		WinInfo winInfo = winInfoDao.get(period);
		for (PrizeEnum key : answerInfo.keySet()) {
			assertEquals(answerInfo.size(), winInfo.size());
			assertEquals(answerInfo.get(key), winInfo.get(key));
			assertEquals(answerInfo.getPrizeMemo(key), winInfo.getPrizeMemo(key));
		}
	}

	@Test
	void Should_GetWinReceipt_When_ParamIs10812() throws IOException {
		String period = "10812";

		answerInfo.setPeriod("108年11 ~12月");
		answerInfo.add(PrizeEnum.SPECIAL_FIRST, genAnswerDetail("59647042"));
		answerInfo.add(PrizeEnum.SPECIAL_SECOND, genAnswerDetail("01260528"));
		answerInfo.add(PrizeEnum.FIRST, genAnswerDetail("01616970", "69921388", "53451508"));
		answerInfo.add(PrizeEnum.EXTRA_SIXTH, genAnswerDetail("710", "585", "633"));
		
		WinInfo winInfo = winInfoDao.get(period);
		for (PrizeEnum key : answerInfo.keySet()) {
			assertEquals(answerInfo.size(), winInfo.size());
			assertEquals(answerInfo.get(key), winInfo.get(key));
			assertEquals(answerInfo.getPrizeMemo(key), winInfo.getPrizeMemo(key));
		}
	}
	
	private List<String> genAnswerDetail(String... numbers) {
		List<String> result = new ArrayList<String>();
		for (String number : numbers) {
			result.add(number);
		}
		return result;
	}
}
