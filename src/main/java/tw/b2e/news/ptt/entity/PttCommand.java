package tw.b2e.news.ptt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PttCommand {

  /**
   * 主操作指令,
   * 支援 help, list
   */
  private String mainOption;

  /**
   * 次操作指令,
   * 支援 ptt board code {@link Board#getCode()}
   */
  private String subOperation;

  /**
   * 配合指令的參數.
   */
  private List<String> args;
}
