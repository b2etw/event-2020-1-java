package tw.b2e.news;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tw.b2e.common.BlocksResponseRouter;
import tw.b2e.news.ptt.entity.Board;
import tw.b2e.news.ptt.entity.PttCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class NewsRouter implements BlocksResponseRouter<SlashCommandRequest> {

  private CommandService commandService;
  private PttCrawlerService pttCrawlerService;
  private ReplyService replyService;

  @Autowired
  NewsRouter(CommandService commandService,
             PttCrawlerService pttCrawlerService,
             ReplyService replyService) {
    this.commandService = commandService;
    this.pttCrawlerService = pttCrawlerService;
    this.replyService = replyService;
  }

  public List<LayoutBlock> handle(SlashCommandRequest req) {
    log.info("req: {}", req);

    if (Objects.isNull(req)
        || Objects.isNull(req.getPayload())
        || Objects.isNull(req.getPayload().getText())
        || req.getPayload().getText().isEmpty()) {
      // oops
      return Collections.singletonList(sectionBlock("Meow~"));
    }

    PttCommand command = commandService.wrapCommand(req.getPayload().getText());

    // parser command
    if (command.getMainOption().contains("list")) {
      // todo - data crawler
      List<Board> boards = this.pttCrawlerService.list(command);
      // simple response
      return replyService.reply(boards);
    }

    // default response
    return Collections.singletonList(sectionBlock(command.getMainOption()));
  }

  private SectionBlock sectionBlock(final String msg) {
    SectionBlock section = new SectionBlock();
    section.setText(new MarkdownTextObject(msg, false));
    return section;
  }
}
