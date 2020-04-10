package tw.b2e;

import lombok.Builder;
import org.apache.logging.log4j.util.Strings;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder for testing.
 * It's a SlashCommandPayload builder follow {@link com.slack.api.app_backend.slash_commands.payload.SlashCommandPayload}.
 */
@Builder
public class MockResponsePayload {

  private String token;

  private String teamId;

  private String teamDomain;

  private String channelId;

  private String channelName;

  private String userId;

  private String userName;

  private String command;

  private String text;

  private String responseUrl;

  private String triggerId;

  /**
   * The SLACK response example:
   * token=abc123
   *  &team_id=abc123
   *  &team_domain=abc_workspace
   *  &channel_id=abc123
   *  &channel_name=message-bot
   *  &user_id=abc123
   *  &user_name=abc123
   *  &command=%2Fnews
   *  &text=hi_message
   *  &response_url=https%3A%2F%2Fhooks.slack.com%2Fcommands%2FT01110T081M%2F1043022379861%2FzUfJnMU3XhKeomDAy3rN1AYH
   *  &trigger_id=1043.1035.hashcode
   *
   * @return String
   */
  public String toBodyString() {
    List<String> body = new ArrayList<>();
    if (Strings.isNotBlank(token)) {
      body.add("token=" + URLEncoder.encode(token.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(teamId)) {
      body.add("team_id=" + URLEncoder.encode(teamId.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(teamDomain)) {
      body.add("team_domain=" + URLEncoder.encode(teamDomain.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(channelId)) {
      body.add("channel_id=" + URLEncoder.encode(channelId.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(channelName)) {
      body.add("channel_name=" + URLEncoder.encode(channelName.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(userId)) {
      body.add("user_id=" + URLEncoder.encode(userId.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(userName)) {
      body.add("user_name=" + URLEncoder.encode(userName.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(command)) {
      body.add("command=" + URLEncoder.encode(command.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(text)) {
      body.add("text=" + URLEncoder.encode(text.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(responseUrl)) {
      body.add("response_url=" + URLEncoder.encode(responseUrl.trim(), StandardCharsets.UTF_8));
    }
    if (Strings.isNotBlank(triggerId)) {
      body.add("trigger_id=" + URLEncoder.encode(triggerId.trim(), StandardCharsets.UTF_8));
    }

    return String.join("&", body);
  }
}
