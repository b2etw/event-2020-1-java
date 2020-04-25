package tw.b2e.news;

import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tw.b2e.news.ptt.entity.Board;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 參考文件:
 * https://api.slack.com/interactivity/slash-commands#responding_to_commands
 */
@Slf4j
@Service
class ReplyService {

  /**
   * 輸出範例:
   * 看板 [分類] - 標題, 1234 人
   * https://ptt.cc...
   */
  private static final String BOARDS_TEMPLATE = "*`%s`* [%s] - %s, %s人\n%s\n";

  public List<LayoutBlock> reply(List<Board> boards) {
    return boards.stream()
        .map(board -> {
          SectionBlock section = new SectionBlock();
          section.setText(new MarkdownTextObject(
              String.format(BOARDS_TEMPLATE,
                  board.getCode(),
                  board.getCategory(),
                  board.getTitle(),
                  board.getHot(),
                  board.getUrl()
              ),
              false));
          return section;
        })
        .collect(Collectors.toList());
  }
}
