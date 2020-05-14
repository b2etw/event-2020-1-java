package tw.b2e.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tw.b2e.news.ptt.entity.Board;
import tw.b2e.news.ptt.entity.PttCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class PttCrawlerService {

  // todo - I will refactor it. 人肉爬蟲 XD
  private static final String PTT_BASE_URL = "https://www.ptt.cc/bbs/";
  private static final String INDEX = "/index.html";

  // persisted
  private static final List<Board> BOARDS = new ArrayList<>();

  static {
    BOARDS.add(new Board("gossiping", 26432, "綜合", "◎【八卦】版主投票開始囉！", pttUrl("gossiping")));
    BOARDS.add(new Board("baseball", 4325, "棒球", "◎[棒球] 中職31年開幕", pttUrl("baseball")));
    BOARDS.add(new Board("c_chat", 3685, "閒談", "◎[希洽] 系列文監控中= =", pttUrl("c_chat")));
    BOARDS.add(new Board("stock", 4325, "學術", "◎3/30 0：00起 板規處分提高一級", pttUrl("stock")));
    BOARDS.add(new Board("beauty", 696, "聊天", "◎《表特板》發文附圖", pttUrl("beauty")));
  }

  private static String pttUrl(final String code) {
    return PTT_BASE_URL + code + INDEX;
  }

  /**
   * 指令說明.
   *
   * @return
   */
  String help(PttCommand command) {
    // todo
    return "";
  }

  /**
   * 列出看板.
   *
   * @return
   */
  List<Board> list(PttCommand command) {
    return BOARDS;
  }

  /**
   * 熱門文章,
   *
   * @return
   */
  List<String> hots(PttCommand command) {
    // todo
    return Collections.emptyList();
  }
}
