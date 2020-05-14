package tw.b2e.news;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tw.b2e.news.ptt.entity.PttCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandServiceTest {

  private CommandService commandService;

  @BeforeEach
  void setUp() {
    this.commandService = new CommandService();
  }

  @Test
  @DisplayName("" +
      "GIVEN a [help] text " +
      "WHEN wrap the command " +
      "THEN the command main operation is [help].")
  void assert_help_operation_command() {
    PttCommand act = this.commandService.wrapCommand("help");
    assertEquals("help", act.getMainOption());
  }

  @Test
  @DisplayName("" +
      "GIVEN a [list] text " +
      "WHEN wrap the command " +
      "THEN the command main operation is [list].")
  void assert_list_operation_command() {
    PttCommand act = this.commandService.wrapCommand("list");
    assertEquals("list", act.getMainOption());
  }

  @Test
  @DisplayName("" +
      "GIVEN a [list gossiping] text " +
      "WHEN wrap the command " +
      "THEN the command main operation is [list] " +
      "AND sub operation is [gossiping].")
  void assert_list_gossiping_command() {
    PttCommand act = this.commandService.wrapCommand("list gossiping");
    assertEquals("list", act.getMainOption());
    assertEquals("gossiping", act.getSubOperation());
  }

  @Test
  @DisplayName("" +
      "GIVEN a [list gossiping hot] text " +
      "WHEN wrap the command " +
      "THEN the command main operation is [list] " +
      "AND sub operation is [gossiping]" +
      "AND args have one is [hot].")
  void assert_list_gossiping_hot_command() {
    PttCommand act = this.commandService.wrapCommand("list gossiping hot");
    assertEquals("list", act.getMainOption());
    assertEquals("gossiping", act.getSubOperation());
    assertEquals("hot", act.getArgs().get(0));
  }

  @Test
  @DisplayName("" +
      "GIVEN a [list gossiping hot 10] text " +
      "WHEN wrap the command " +
      "THEN the command main operation is [list] " +
      "AND sub operation is [gossiping]" +
      "AND args have two are [hot 10].")
  void assert_list_gossiping_hot_10_command() {
    PttCommand act = this.commandService.wrapCommand("list gossiping hot 10");
    assertEquals("list", act.getMainOption());
    assertEquals("gossiping", act.getSubOperation());
    assertEquals("hot", act.getArgs().get(0));
    assertEquals("10", act.getArgs().get(1));
  }
}