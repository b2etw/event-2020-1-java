package tw.b2e.news;

import com.slack.api.app_backend.slash_commands.payload.SlashCommandPayload;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.PlainTextObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tw.b2e.common.BlocksResponseRouter;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class NewsRouter implements BlocksResponseRouter<SlashCommandRequest> {

  public List<LayoutBlock> handle(SlashCommandRequest req) {
    log.info("req: {}", req);
    SlashCommandPayload payload = req.getPayload();
    String text = payload.getText();

    // parser command

    // get crawler data

    // response
    SectionBlock simpleBlock = new SectionBlock();
    simpleBlock.setText(new PlainTextObject(text, true));

    return Collections.singletonList(simpleBlock);
  }
}
