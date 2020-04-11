package tw.b2e.news;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tw.b2e.MockResponsePayload;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewsRouterTest {

  private NewsRouter newsRouter;

  @BeforeEach
  void setUp() {
    newsRouter = new NewsRouter();
  }

  @Test
  @DisplayName("" +
      "GIVEN a [/news ptt gossiping] command " +
      "WHEN news router handle it " +
      "THEN response [ptt gossiping] message.")
  void assert_handle_ptt_gossiping() {
    // arrange
    String body = MockResponsePayload.builder()
        .command("/news")
        .text("ptt gossiping")
        .build()
        .toBodyString();

    // act
    String act = newsRouter.handle(new SlashCommandRequest(body, null));

    // assert
    assertEquals("ptt gossiping", act);
  }

}