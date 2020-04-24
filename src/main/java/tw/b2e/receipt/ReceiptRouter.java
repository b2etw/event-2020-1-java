package tw.b2e.receipt;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

@Component
public class ReceiptRouter implements Router<SlashCommandRequest> {

    @Override
    public String handle(SlashCommandRequest sReq)
    {
        // 可以用這種方式拿到用加號分隔的參數，比如說下指令 /receipt 2020 01 02，那 text 就會印出 2020+01+02
        String text = "";
        if(sReq != null){
            text = sReq.getPayload().getText();
        }

        PrizeNumberObject pnobj = null;

//        if(text == null || "".equals(text)){
            Calendar col = Calendar.getInstance();
            col.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));

            if((col.get(Calendar.MONTH) +1) % 2 == 0){
                col.add(Calendar.MONTH,-2);
            }else{
                if(col.get(Calendar.DAY_OF_MONTH) > 25){
                    col.add(Calendar.MONTH,-2);
                }else{
                    col.add(Calendar.MONTH,-4);
                }
            }
            pnobj = new PrizeNumberObject(Integer.toString(col.get(Calendar.YEAR)), Integer.toString(col.get(Calendar.MONTH) +1));
//        }

        if(pnobj.jsoupRes){
            return pnobj.getAllPrizeNumberDesc() + text;
        }else{
            return "Coming soon! \r\n" + text;
        }

    }
}
