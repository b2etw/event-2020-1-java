package tw.b2e.common;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;

public interface Router<T> {

    default String handle(T req) {
        if (req instanceof SlashCommandRequest) {
            // 可以用這種方式拿到用加號分隔的參數，比如說下指令 /receipt 2020 01 02，那 text 就會印出 2020+01+02
            return ((SlashCommandRequest) req).getPayload().getText();
        } else {
            return "Not implement !!";
        }
    }

}
