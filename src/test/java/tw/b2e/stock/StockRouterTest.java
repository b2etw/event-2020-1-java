package tw.b2e.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tw.b2e.common.CommonUtils;
import tw.b2e.stock.vo.StockApiMsgVo;
import tw.b2e.stock.vo.StockApiVo;

@SpringBootTest
class StockRouterTest {


  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testGetStockData() throws Exception {
    final RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET,
        new URI(String.format(StockRouter.API_URL, String.format(StockRouter.EX_CH_TEMP, "2330"))));

    final RestTemplate trustRestTemplate = CommonUtils.getTrustRestTemplate();

    final ResponseEntity<StockApiVo> responseEntity = trustRestTemplate
        .exchange(requestEntity, StockApiVo.class);

    final StockApiVo stockApiVo = responseEntity.getBody();
    assertNotNull(stockApiVo);

    assertNotNull(stockApiVo.getMsgArray());

    final List<StockApiMsgVo> msgArray = stockApiVo.getMsgArray();
    assertNotNull(msgArray);

    final Optional<StockApiMsgVo> anyData = msgArray.stream().findAny();
    assertTrue(anyData.isPresent());
    assertEquals(anyData.get().getN(), "台積電");

  }


}