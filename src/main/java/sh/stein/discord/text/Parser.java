package sh.stein.discord.text;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Matches Discord message URIs with a named {@code snowflake} capture group.
     */
    private final static Pattern MESSAGE_URI_PATTERN = Pattern.compile(
            "^https:\\/\\/discord.com\\/channels\\/\\d{18}\\/\\d{18}\\/(?<snowflake>\\d{18})$");

    /**
     * Matches Markdown-style code blocks with named {@code language} (optional) and {@code code}
     * capture groups.
     */
    private final static Pattern CODE_BLOCK_PATTERN = Pattern.compile(
            "```(?<language>[\\w\\.]+)?\\n(?<code>.*\\n?)```",
            Pattern.DOTALL);

    /**
     * Matches one or more back tick ({@code `}) at the beginning or end of a string.
     */
    private final static Pattern BACK_TICK_PATTERN = Pattern.compile("(^`+|`+$)");

    /**
     * Parses a Discord message link for the message's snowflake.
     *
     * @param link a URI to a Discord message
     */
    public OptionalLong getSnowflakeFromURI(String link) {
        Matcher matcher = MESSAGE_URI_PATTERN.matcher(link);
        if (matcher.find()) {
            long snowflake = Long.parseLong(matcher.group("snowflake"));
            return OptionalLong.of(snowflake);
        }

        return OptionalLong.empty();
    }

    /**
     * Parses a message for a code block. A code block is a block of text delimited by three back
     * ticks ({@code ```}), optionally with a specified language after the opening back-ticks.
     */
    public Optional<CodeBlock> getCodeBlock(String message) {
        Matcher matcher = CODE_BLOCK_PATTERN.matcher(message);
        if (!matcher.find()) {
            return Optional.empty();
        }

        String group = matcher.group("language");
        Optional<String> language = group == null ? Optional.empty() : Optional.of(group);

        return Optional.of(new CodeBlock(language, matcher.group("code")));
    }

    /**
     * Removes any back-ticks surrounding the message.
     */
    public String stripSurroundingBackTicks(String message) {
        return BACK_TICK_PATTERN.matcher(message).replaceAll("");
    }
}
