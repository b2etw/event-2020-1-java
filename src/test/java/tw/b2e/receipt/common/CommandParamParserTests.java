package tw.b2e.receipt.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;

import tw.b2e.receipt.execution.ParamValueIsNullExecution;
import tw.b2e.receipt.vo.CommandParam;

@SpringBootTest
class CommandParamParserTests {

	@Autowired
	CommandParamParser commandParamParser;
	@Test
	void Should_GetParam_When_OnlyAction() {
		String paramString = "help";
		CommandParam param = commandParamParser.Parse(paramString);
		assertEquals(paramString, param.getAction());
	}

	@Test
	void Should_GetParam_When_ActionWithSingleParam() {
		String action = "win";
		String[] paramKeys = new String[] {"-y"};
		String[] paramValues = new String[] {"2020"};
		StringBuffer paramString = new StringBuffer(action);
		for(int i = 0; i < paramKeys.length; i++) {
			paramString.append(String.format(" %s %s", paramKeys[i], paramValues[i]));
		}
		CommandParam param = commandParamParser.Parse(paramString.toString());
		assertEquals(action, param.getAction());
		for(int i = 0; i < paramKeys.length; i++) {
			assertEquals(paramValues[i], param.getParam(paramKeys[i]));
		}
	}
	@Test
	void Should_GetParam_When_ActionWithMultipleParam() {
		String action = "win";
		String[] paramKeys = new String[] {"-y", "-m", "-i"};
		String[] paramValues = new String[] {"2020", "04", "AB12345678"};
		StringBuffer paramString = new StringBuffer(action);
		for(int i = 0; i < paramKeys.length; i++) {
			paramString.append(String.format(" %s %s", paramKeys[i], paramValues[i]));
		}
		CommandParam param = commandParamParser.Parse(paramString.toString());
		assertEquals(action, param.getAction());
		for(int i = 0; i < paramKeys.length; i++) {
			assertEquals(paramValues[i], param.getParam(paramKeys[i]));
		}
	}

	@Test
	void Should_ThrowsParamValueIsNullExecution_When_LackParamValue() {		
		Assertions.assertThrows(ParamValueIsNullExecution.class, () -> {
			commandParamParser.Parse("win -y");
		});
	}
	
}
