package tw.b2e.stock.vo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockApiVo {

  private String referer;

  private String rtmessage;

  private String exKey;

  private List<StockApiMsgVo> msgArray;
}
