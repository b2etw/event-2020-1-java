package tw.b2e.game;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

@Component
public class GameRouter implements Router<SlashCommandRequest> {
}
