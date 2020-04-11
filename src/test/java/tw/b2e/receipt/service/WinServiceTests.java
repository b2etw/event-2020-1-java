package tw.b2e.receipt.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.b2e.receipt.vo.CommandParam;

@SpringBootTest
class WinServiceTests {

	@Autowired
	private WinService winService;

	private static CommandParam param;
	private static List<String> answerArgs;
	
	@BeforeAll
	public static void before() {
		param = new CommandParam();
		param.setAction("win");
		answerArgs = new ArrayList<String>();
		answerArgs.add("109年01 ~02月");
		answerArgs.add("12620024");
		answerArgs.add("同期統一發票收執聯8位數號碼與特別獎號碼相同者獎金1,000萬元");
		answerArgs.add("39793895");
		answerArgs.add("同期統一發票收執聯8位數號碼與特獎號碼相同者獎金200萬元"); 
		answerArgs.add("67913945、09954061、54574947");
		answerArgs.add("同期統一發票收執聯8位數號碼與頭獎號碼相同者獎金20萬元"); 
		answerArgs.add("同期統一發票收執聯末7 位數號碼與頭獎中獎號碼末7 位相同者各得獎金4萬元"); 
		answerArgs.add("同期統一發票收執聯末6 位數號碼與頭獎中獎號碼末6 位相同者各得獎金1萬元"); 
		answerArgs.add("同期統一發票收執聯末5 位數號碼與頭獎中獎號碼末5 位相同者各得獎金4千元"); 
		answerArgs.add("同期統一發票收執聯末4 位數號碼與頭獎中獎號碼末4 位相同者各得獎金1千元"); 
		answerArgs.add("同期統一發票收執聯末3 位數號碼與 頭獎中獎號碼末3 位相同者各得獎金2百元");		
		answerArgs.add("007");
		answerArgs.add("同期統一發票收執聯末3位數號碼與增開六獎號碼相同者各得獎金2百元");
	}

	@Test
	void Should_ShowWinInfo_When_ParamIsYWithM() throws Exception {
		param.putParam("-y", "109");
		param.putParam("-m", "02");
		String result = winService.execute(param);
		String answer = String.format(WinService.RESULT_FORMAT, answerArgs.toArray());
		assertEquals(answer, result);
	}

	@Test
	void Should_ShowWinInfo_When_ParamIsYM() throws Exception {
		param.putParam("-ym", "10901");
		String result = winService.execute(param);
		String answer = String.format(WinService.RESULT_FORMAT, answerArgs.toArray());
		assertEquals(answer, result);
	}
}
