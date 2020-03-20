package tw.b2e.receipt;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

@Component
public class ReceiptRouter implements Router<SlashCommandRequest> {
@Override
    public String handle(SlashCommandRequest sReq){
        String aa = sReq.getRequestBodyAsString();
        System.out.println(aa);
        return "Coming soon";
    }

}
