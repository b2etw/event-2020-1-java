package tw.b2e.config;

import com.slack.api.bolt.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

    @Bean
    public App initSlackApp() {
        App app = new App();
        app.command("/hello", (req, ctx) -> {
            return ctx.ack("What's up?");
        });

        return app;
    }

}
