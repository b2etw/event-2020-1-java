package tw.b2e.receipt;

import java.util.HashMap;
import java.util.Map;

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
import tw.b2e.receipt.service.CheckService;
import tw.b2e.receipt.service.HelpService;
import tw.b2e.receipt.service.UnknownService;
import tw.b2e.receipt.service.WinService;
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
	private WinService winService;
	
	@Resource
	private CheckService checkService;
	
	@Bean
	@ConditionalOnBean(value = HelpService.class)
	public void initServiceMapping() {
		//初始化時，將Service註冊進Mapping表
		serviceMapping.put(HelpService.ACTION_COMMAND, helpService);
		serviceMapping.put(WinService.ACTION_COMMAND, winService);
		serviceMapping.put(CheckService.ACTION_COMMAND, checkService);
	}

	@Override
	public String handle(SlashCommandRequest req) {

		logger.info("req.getPayload().getText():" + req.getPayload().getText());

		CommandParam commandParam = commandParamParser.Parse(req.getPayload().getText());

		logger.info("commandParam.getAction():" + commandParam.getAction());
		
		if (commandParam.getAction() == null) {//無參數時，預設為Help服務
			return serviceMapping.get(HelpService.ACTION_COMMAND).execute(commandParam);
		}else if(serviceMapping.get(commandParam.getAction()) == null) {
			return unknownService.execute(commandParam);
		} else {
			return serviceMapping.get(commandParam.getAction()).execute(commandParam);
		}

	}
}
