package tw.b2e;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * SLACK properties.
 * Can ref: https://slack.dev/java-slack-sdk/guides/getting-started-with-bolt
 */
@Data
@Validated
@ConfigurationProperties(value = "slack")
public class SlackProperties {

  @NotEmpty(message = "Missing [slack.bot-token], please check [application.yml] or sys env variables.")
  private String botToken = System.getProperty("SLACK_BOT_TOKEN");

  @NotEmpty(message = "Missing [slack.signing-secret], please check [application.yml] or sys env variables.")
  private String signingSecret = System.getProperty("SLACK_SIGNING_SECRET");
}
