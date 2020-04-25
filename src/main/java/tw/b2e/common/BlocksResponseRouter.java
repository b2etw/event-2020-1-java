package tw.b2e.common;

import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.model.block.LayoutBlock;

import java.util.List;

public interface BlocksResponseRouter<T> {

  /**
   * 支援回傳 Block 形式的訊息.
   * 參閱 {@link SlashCommandContext#respond(List)}
   * 參閱文件: https://api.slack.com/block-kit/building
   *
   * @param req
   * @return
   */
  List<LayoutBlock> handle(T req);
}
