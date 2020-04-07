package tw.b2e.receipt;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;

import tw.b2e.common.Router;
import tw.b2e.receipt.service.BaseService;
import tw.b2e.receipt.service.HelpService;
import tw.b2e.receipt.service.UnknownService;
import tw.b2e.receipt.service.WinListService;
import tw.b2e.receipt.vo.CommandParam;
import tw.b2e.receipt.common.CommandParamParser;

@Component
public class ReceiptRouter implements Router<SlashCommandRequest> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Map<String, BaseService> serviceMapping = new HashMap<String, BaseService>();

	@Autowired
	private CommandParamParser commandParamParser;

	@Resource
	private UnknownService unknownService;

	@Resource
	private HelpService helpService;
	
	@Resource
	private WinListService winListService;
	
	@Bean
	@ConditionalOnBean(value = HelpService.class)
	public void initServiceMapping() {
		serviceMapping.put(HelpService.ACTION_COMMAND, helpService);
		serviceMapping.put(WinListService.ACTION_COMMAND, winListService);
	}

	@Override
	public String handle(SlashCommandRequest req) {

		logger.info("req.getPayload().getText():" + req.getPayload().getText());

		CommandParam commandParam = commandParamParser.Parse(req.getPayload().getText());

		logger.info("commandParam.getAction():" + commandParam.getAction());
		
		if (commandParam.getAction() == null) {
			return serviceMapping.get(HelpService.ACTION_COMMAND).execute(commandParam);
		}else if(serviceMapping.get(commandParam.getAction()) == null) {
			return unknownService.execute(commandParam);
		} else {
			return serviceMapping.get(commandParam.getAction()).execute(commandParam);
		}

	}
}
