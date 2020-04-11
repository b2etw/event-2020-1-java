package tw.b2e.receipt.common;

import java.util.Set;

import org.springframework.stereotype.Component;

import tw.b2e.receipt.execution.ParamValueIsNullExecution;
import tw.b2e.receipt.execution.UndefinedCommandExecution;
import tw.b2e.receipt.vo.CommandParam;

/* 
 * @author NaiHua
 * 目的:解析slash command指令參數
 * 範例:
 * 	/receipt -help => CommandParam物件 {"action":"-help"}
 *  /receipt win -y 2019 -m 02 => CommandParam物件 {"action":"win", "params":{"-y":"2020", "-m":"02"}}
 */
@Component
public class CommandParamParser {
		
	
	public CommandParam Parse(String paramString) {
		return Parse(null, paramString);
	}
	
	/**
	 * 解析slash command參數
	 * @param paramString
	 * @return CommandParam
	 */
	public CommandParam Parse(Set<String> commandPool, String paramString) {
		CommandParam result = new CommandParam();
		
		if(paramString == null || "".equals(paramString)) {
			return result;
		}
		
		String[] params = paramString.split(" ");	
		
		//若有提供合法command清單則進行檢查，若非合法Command則拋出錯誤訊息
		if(commandPool != null 
			&& !commandPool.contains(params[0])) {
			throw new UndefinedCommandExecution(params[0]);
		}
		result.setAction(params[0]);		
		
		for(int i = 1; i < params.length; i = i + 2) {
			//若參數非key與value結對傳入則拋出錯誤訊息
			if(params.length < i + 2) {
				throw new ParamValueIsNullExecution(params[i]);
			}else {
				result.putParam(params[i], params[i + 1]);
			}			
		}
		
		return result;
	}
}
