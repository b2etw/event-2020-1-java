package tw.b2e.receipt.service;

import org.springframework.stereotype.Component;

import tw.b2e.receipt.format.service.HelpServiceFormat;
import tw.b2e.receipt.vo.CommandParam;

@Component
public class HelpService implements BaseService{

	public final static String ACTION_COMMAND = "-help";
	
	public final static String RESULT_FORMAT = HelpServiceFormat.RESULT_FORMAT;
	
	@Override
	public String execute(CommandParam param) {
		return RESULT_FORMAT;
	}

}
