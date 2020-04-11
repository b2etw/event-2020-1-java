package tw.b2e.slack;

import com.slack.api.bolt.App;
import com.slack.api.bolt.AppConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.b2e.SlackProperties;
import tw.b2e.drink.DrinkRouter;
import tw.b2e.game.GameRouter;
import tw.b2e.news.NewsRouter;
import tw.b2e.receipt.ReceiptRouter;
import tw.b2e.stock.StockRouter;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(value = SlackProperties.class)
public class SlackConfig {

    @Resource
    private SlackProperties slackProperties;

    @Resource
    private StockRouter stockRouter;
    @Resource
    private NewsRouter newsRouter;
    @Resource
    private GameRouter gameRouter;
    @Resource
    private ReceiptRouter receiptRouter;
    @Resource
    private DrinkRouter drinkRouter;

    @Bean
    public AppConfig appConfig() {
        return new AppConfig().toBuilder()
            .singleTeamBotToken(slackProperties.getBotToken())
            .signingSecret(slackProperties.getSigningSecret())
            .build();
    }

    @Bean
    @ConditionalOnBean(value = AppConfig.class)
    public App initSlackApp(AppConfig appConfig) {
        App app = new App().toBuilder()
            .appConfig(appConfig)
            .build();
        app.command("/stock", (req, ctx) -> {
            ctx.respond(stockRouter.handle(req));
            return ctx.ack();
        });
        app.command("/news", (req, ctx) -> {
            ctx.respond(newsRouter.handle(req));
            return ctx.ack();
        });
        app.command("/game", (req, ctx) -> {
            ctx.respond(gameRouter.handle(req));
            return ctx.ack();
        });
        app.command("/receipt", (req, ctx) -> {
            ctx.respond(receiptRouter.handle(req));
            return ctx.ack();
        });
        app.command("/drink", (req, ctx) -> {
            ctx.respond(drinkRouter.handle(req));
            return ctx.ack();
        });

        return app;
    }

}
