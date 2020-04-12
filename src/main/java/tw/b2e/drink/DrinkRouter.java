package tw.b2e.drink;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.springframework.stereotype.Component;
import tw.b2e.common.Router;

import java.util.HashMap;
import java.util.Map;

@Component
public class DrinkRouter implements Router<SlashCommandRequest> {

    private static Map<String, String> drinkFunctionMap;

    private static Map<String,String> initDrinkFunctionMap(){
        if (drinkFunctionMap == null) {
            drinkFunctionMap = new HashMap<>();
            drinkFunctionMap.put("-help", "");
            drinkFunctionMap.put("-find", "");
            //TODO
        }
        return drinkFunctionMap;
    }

    @Override
    public String handle(SlashCommandRequest req) {
        //TODO
        return null;
    }
}
