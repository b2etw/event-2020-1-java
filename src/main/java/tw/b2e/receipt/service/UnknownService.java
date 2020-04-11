package tw.b2e.receipt.service;

import org.springframework.stereotype.Component;

import tw.b2e.receipt.format.service.UnknownServiceFormat;
import tw.b2e.receipt.vo.CommandParam;

@Component
public class UnknownService implements BaseService{

	public final static String RESULT_FORMAT = UnknownServiceFormat.RESULT_FORMAT;
	
	@Override
	public String execute(CommandParam param) {
		return String.format(RESULT_FORMAT, param.getAction());
	}

}
