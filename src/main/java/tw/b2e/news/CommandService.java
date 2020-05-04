package tw.b2e.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tw.b2e.news.ptt.entity.PttCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Slf4j
@Service
class CommandService {

  /**
   * 打包為 PttCommand.
   *
   * @param text
   * @return
   */
  PttCommand wrapCommand(final String text) {
    PttCommand pttCommand = new PttCommand();
    String[] commands = text.split(" ");

    if (1 == commands.length) {
      pttCommand.setMainOption(commands[0]);
      return pttCommand;
    }

    if (2 == commands.length) {
      pttCommand.setMainOption(commands[0]);
      pttCommand.setSubOperation(commands[1]);
      return pttCommand;
    }

    if (2 < commands.length) {
      pttCommand.setMainOption(commands[0]);
      pttCommand.setSubOperation(commands[1]);
      pttCommand.setArgs(Arrays.asList(Arrays.copyOfRange(commands, 2, commands.length)));
    }

    return pttCommand;
  }
}
