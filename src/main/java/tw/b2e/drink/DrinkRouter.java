package tw.b2e.drink;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

@Component
public class DrinkRouter implements Router<SlashCommandRequest> {
}
