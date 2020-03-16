package tw.b2e.receipt;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

@Component
public class ReceiptRouter implements Router<SlashCommandRequest> {
}
