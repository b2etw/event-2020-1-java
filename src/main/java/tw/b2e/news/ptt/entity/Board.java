package tw.b2e.news.ptt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Board {

  /**
   * 看板代碼, e.g. Gossiping, NBA, Baseball, ...
   */
  private String code;

  /**
   * 看板熱度 (人數)
   */
  private Integer hot;

  /**
   * 看板分類 e.g. 綜合, 學術, 省錢, ...
   */
  private String category;

  /**
   * 看板標題, e.g. [八卦] 版主投票開始囉！ ...
   */
  private String title;

  /**
   * 看板 URL, e.g. https://www.ptt.cc/bbs/Gossiping/index.html
   */
  private String url;
}
