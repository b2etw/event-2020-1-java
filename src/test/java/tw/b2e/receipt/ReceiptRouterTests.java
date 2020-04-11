package tw.b2e.receipt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tw.b2e.MockResponsePayload;
import tw.b2e.receipt.common.ResultBuilder;
import tw.b2e.receipt.execution.ParamValueIsNullExecution;
import tw.b2e.receipt.execution.UndefinedCommandExecution;
import tw.b2e.receipt.format.service.HelpServiceFormat;
import tw.b2e.receipt.service.UnknownService;

@SpringBootTest
class ReceiptRouterTests {

	@Autowired
	ReceiptRouter receiptRouter;

	@Test
	void Should_HelpResult_When_EemptyParam() {

		String body = MockResponsePayload.builder().command("/receip").build().toBodyString();

		String result = receiptRouter.handle(new SlashCommandRequest(body, null));

		assertEquals(HelpServiceFormat.RESULT_FORMAT, result);
	}

	@Test
	void Should_HelpResult_When_HelpParam() {
		String action = "-help";
		String body = MockResponsePayload.builder().command("/receip").text(action).build().toBodyString();

		String result = receiptRouter.handle(new SlashCommandRequest(body, null));

		assertEquals(HelpServiceFormat.RESULT_FORMAT, result);
	}

	@Test
	void Should_UnknownResult_When_UndefinedParam() {
		String action = "test";
		String body = MockResponsePayload.builder().command("/receip").text(action).build().toBodyString();

		String result = receiptRouter.handle(new SlashCommandRequest(body, null));

		assertEquals(String.format(UnknownService.RESULT_FORMAT, action), result);
	}

	@Test
	void Should_ParamValueIsNullResult_When_LackParamValues() {
		String action = "win";
		String param = "-t";
		String body = MockResponsePayload.builder().command("/receip").text(action + " " + param).build().toBodyString();

		String result = receiptRouter.handle(new SlashCommandRequest(body, null));

		assertEquals(ResultBuilder.error(String.format(ParamValueIsNullExecution.REQUIRES_VALUE_FORMAT, param)),
				result);
	}

	@Test
	void Should_UnknownResult_When_UnknownParam() {
		String action = "test";		
		String body = MockResponsePayload.builder().command("/receip").text(action).build().toBodyString();

		String result = receiptRouter.handle(new SlashCommandRequest(body, null));

		assertEquals(ResultBuilder.error(String.format(UndefinedCommandExecution.UNDEFINED_FORMAT, action)),
				result);
	}
}
