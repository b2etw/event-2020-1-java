package tw.b2e.receipt.common;

import org.springframework.stereotype.Component;

import tw.b2e.receipt.vo.CommandParam;
/**
 * 
 * @author NaiHua
 * 目的:解析slash command指令參數
 * 範例:
 * 	/receipt help => CommandParam物件 {"action":"help"}
 *  /receipt win -y 2019 -m 02 => CommandParam物件 {"action":"win", "params":{"-y":"2020", "-m":"02"}}
 */
@Component
public class CommandParamParser {
		
	/**
	 * 解析slash command參數
	 * @param paramString
	 * @return CommandParam
	 */
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
