package tw.b2e.slack;

import com.slack.api.bolt.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.b2e.drink.DrinkRouter;
import tw.b2e.game.GameRouter;
import tw.b2e.news.NewsRouter;
import tw.b2e.receipt.ReceiptRouter;
import tw.b2e.stock.StockRouter;

import javax.annotation.Resource;

@Configuration
public class SlackConfig {

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
    public App initSlackApp() {
        App app = new App();
        app.command("/stock", (req, ctx) -> {
            return ctx.ack(stockRouter.handle(req));
        });
        app.command("/news", (req, ctx) -> {
            return ctx.ack(newsRouter.handle(req));
        });
        app.command("/game", (req, ctx) -> {
            return ctx.ack(gameRouter.handle(req));
        });
        app.command("/receipt", (req, ctx) -> {
            return ctx.ack(receiptRouter.handle(req));
        });
        app.command("/drink", (req, ctx) -> {
            return ctx.ack(drinkRouter.handle(req));
        });

        return app;
    }

}
