package tw.b2e.stock.vo;

import java.util.List;

public class StockApiVo {


  private String referer;

  private String rtmessage;

  private String exKey;

  private List<StockApiMsgVo> msgArray;

  public String getReferer () {
    return referer;
  }

  public void setReferer (String referer) {
    this.referer = referer;
  }

  public String getRtmessage () {
    return rtmessage;
  }

  public void setRtmessage (String rtmessage) {
    this.rtmessage = rtmessage;
  }

  public String getExKey () {
    return exKey;
  }

  public void setExKey (String exKey) {
    this.exKey = exKey;
  }

  public List<StockApiMsgVo> getMsgArray () {
    return msgArray;
  }

  public void setMsgArray (List<StockApiMsgVo> msgArray) {
    this.msgArray = msgArray;
  }
}
