package tw.b2e.receipt.common;

import org.springframework.stereotype.Component;

import tw.b2e.receipt.vo.CommandParam;

@Component
public class CommandParamParser {
		
	public CommandParam Parse(String paramString) {
		CommandParam result = new CommandParam();
		
		if(paramString == null || "".equals(paramString)) {
			return result;
		}
		
		String[] params = paramString.split(" ");		
		result.setAction(params[0]);
				
		for(int i = 1; i < params.length; i = i + 2) {
			result.putParam(params[i], params[i + 1]);
		}
		
		return result;
	}
}
