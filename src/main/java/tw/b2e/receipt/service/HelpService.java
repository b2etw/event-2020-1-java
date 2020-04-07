package tw.b2e.receipt.service;

import org.springframework.stereotype.Component;

import tw.b2e.receipt.vo.CommandParam;

@Component
public class HelpService implements BaseService{

	public final static String ACTION_COMMAND = "-help";
	
	@Override
	public String execute(CommandParam param) {
		return "(execute HelpService) TODO: This will show command manual.";
	}

}
