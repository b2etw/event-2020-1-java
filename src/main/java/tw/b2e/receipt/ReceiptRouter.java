package tw.b2e.receipt;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

@Component
public class ReceiptRouter implements Router<SlashCommandRequest> {

    @Override
    public String handle(SlashCommandRequest sReq){
        // 可以用這種方式拿到用加號分隔的參數，比如說下指令 /receipt 2020 01 02，那 text 就會印出 2020+01+02
        String text = sReq.getPayload().getText();
        System.out.println(text);

        String aa = sReq.getRequestBodyAsString();
        System.out.println(aa);
        return "Coming soon";
    }
}
