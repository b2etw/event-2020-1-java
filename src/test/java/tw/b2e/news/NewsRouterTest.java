package tw.b2e.news;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.PlainTextObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tw.b2e.MockResponsePayload;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    List<LayoutBlock> act = newsRouter.handle(new SlashCommandRequest(body, null));

    // assert
    assertThat(act.size()).isEqualTo(1);
    PlainTextObject actText = (PlainTextObject) ReflectionTestUtils.getField(act.get(0), "text");
    assertEquals("ptt gossiping", actText.getText());
  }

}