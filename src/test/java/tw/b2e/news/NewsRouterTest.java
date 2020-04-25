package tw.b2e.news;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import tw.b2e.MockResponsePayload;
import tw.b2e.news.ptt.entity.Board;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class NewsRouterTest {

  private NewsRouter newsRouter;

  @Mock
  private ReplyService replyService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    newsRouter = new NewsRouter(replyService);
  }

  @Test
  @DisplayName("" +
      "GIVEN a [/news gossiping] command " +
      "WHEN news router handle it " +
      "THEN response [ptt gossiping] message.")
  void assert_handle_ptt_gossiping() {
    // arrange
    String body = MockResponsePayload.builder()
        .command("/news")
        .text("gossiping")
        .build()
        .toBodyString();

    // act
    List<LayoutBlock> act = newsRouter.handle(new SlashCommandRequest(body, null));

    // assert
    assertThat(act.size()).isEqualTo(1);
    PlainTextObject actText = (PlainTextObject) ReflectionTestUtils.getField(act.get(0), "text");
    assertEquals("ptt gossiping", actText.getText());
  }

  @Test
  @DisplayName("" +
      "GITVEN a [/news ptt list] command " +
      "WHEN new router handle it " +
      "THEN response [A PTT board] message.")
  void assert_handler_ptt_list() {
    // arrange
    String body = MockResponsePayload.builder()
        .command("/news")
        .text("ptt list")
        .build()
        .toBodyString();

    // mock
    Board simpleBoard = new Board(
        "gossiping",
        26432,
        "綜合",
        "◎【八卦】版主投票開始囉！",
        "https://www.ptt.cc/bbs/gossiping/index.html");
    SectionBlock sectionBlock = new SectionBlock();
    String msg = String.format("*`%s`* [%s] - %s, %s人\n%s\n",
        simpleBoard.getCode(),
        simpleBoard.getCategory(),
        simpleBoard.getTitle(),
        simpleBoard.getHot(),
        simpleBoard.getUrl());
    sectionBlock.setText(new MarkdownTextObject(msg, false));

    when(replyService.reply(anyList()))
        .thenReturn(Collections.singletonList(sectionBlock));

    // act
    List<LayoutBlock> act = newsRouter.handle(new SlashCommandRequest(body, null));
    assertThat(act.size()).isEqualTo(1);
    MarkdownTextObject actText = (MarkdownTextObject) ReflectionTestUtils.getField(act.get(0), "text");
    assertEquals(msg, actText.getText());
  }
}