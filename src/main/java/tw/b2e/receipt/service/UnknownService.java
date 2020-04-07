package tw.b2e.receipt.service;

import org.springframework.stereotype.Component;

import tw.b2e.receipt.vo.CommandParam;

@Component
public class UnknownService implements BaseService{

	@Override
	public String execute(CommandParam param) {
		return String.format("%s is not a receipt command. See '/receipt -help'.", param.getAction());
	}

}
