package tw.b2e.news;

import com.slack.api.app_backend.slash_commands.payload.SlashCommandPayload;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

@Slf4j
@Component
public class NewsRouter implements Router<SlashCommandRequest> {

  @Override
  public String handle(SlashCommandRequest req) {
    log.info("req: {}", req);
    SlashCommandPayload payload = req.getPayload();
    String text = payload.getText();
    return text;
  }
}
