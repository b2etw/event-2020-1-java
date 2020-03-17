package tw.b2e.common;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;

public interface Router<T> {

    default String handle(T req) {
        if (req instanceof SlashCommandRequest) {
            return ((SlashCommandRequest) req).getRequestBodyAsString();
        } else {
            return "Not implement !!";
        }
    }

}
