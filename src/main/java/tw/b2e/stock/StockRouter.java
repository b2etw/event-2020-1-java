package tw.b2e.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import tw.b2e.common.CommonUtils;
import tw.b2e.common.Router;
import tw.b2e.stock.vo.StockApiMsgVo;
import tw.b2e.stock.vo.StockApiVo;

@Component
public class StockRouter implements Router<SlashCommandRequest> {

  final public static String EX_CH_TEMP = "tse_%s.tw";

  final public static String API_URL = "https://mis.twse.com.tw/stock/api/getStockInfo.jsp?ex_ch=%s";

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public String handle (SlashCommandRequest req) {
    final RequestEntity<String> requestEntity;
    try {
      requestEntity = new RequestEntity<>(HttpMethod.GET,
          new URI(String.format(StockRouter.API_URL, String.format(StockRouter.EX_CH_TEMP, "2330"))));
    } catch (URISyntaxException e) {
     return String.format("crete uri error:%s", e.getMessage());
    }

    final RestTemplate trustRestTemplate = CommonUtils.getTrustRestTemplate();

    final ResponseEntity<StockApiVo> responseEntity = trustRestTemplate
        .exchange(requestEntity, StockApiVo.class);

    final List<StockApiMsgVo> stockDataList = Optional.ofNullable(responseEntity)
        .map(HttpEntity::getBody)
        .map(StockApiVo::getMsgArray)
        .orElse(null);

    if (!CollectionUtils.isEmpty(stockDataList)) {
      //TODO 取資料呈現部分待補上
      return "Get Data Success";
    }

    return "StockData NotFount";
  }
}
