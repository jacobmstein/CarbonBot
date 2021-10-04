package sh.stein.discord.commands.impl;

import static sh.stein.carbon.ImageOptions.ImageOptionsBuilder;
import static sh.stein.carbon.ImageOptions.WindowTheme;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.function.BiConsumer;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import sh.stein.carbon.CarbonService;
import sh.stein.discord.commands.BaseSlashCommand;
import sh.stein.discord.text.CodeBlock;
import sh.stein.discord.text.Parser;
import sh.stein.settings.CarbonSetting;
import sh.stein.settings.SettingsService;

public class CarbonCommand extends BaseSlashCommand {

    private static final Map<CarbonSetting, CarbonSettingConsumer> CARBON_SETTING_CONSUMER_MAP = new HashMap<>() {{
        put(CarbonSetting.BackgroundColor, (b, v) -> b.backgroundColor(v));
        put(CarbonSetting.DropShadow, (b, v) -> b.dropShadow(Boolean.parseBoolean(v)));
        put(CarbonSetting.DropShadowBlurRadius, (b, v) -> b.dropShadowBlurRadius(v));
        put(CarbonSetting.DropShadowOffsetY, (b, v) -> b.dropShadowOffsetY(v));
        put(CarbonSetting.FontFamily, (b, v) -> b.fontFamily(v));
        put(CarbonSetting.FontSize, (b, v) -> b.fontSize(v));
        put(CarbonSetting.FirstLineNumber, (b, v) -> b.firstLineNumber(Integer.parseInt(v)));
        put(CarbonSetting.LineHeight, (b, v) -> b.lineHeight(v));
        put(CarbonSetting.LineNumbers, (b, v) -> b.lineNumbers(Boolean.parseBoolean(v)));
        put(CarbonSetting.PaddingHorizontal, (b, v) -> b.paddingHorizontal(v));
        put(CarbonSetting.PaddingVertical, (b, v) -> b.paddingVertical(v));
        put(CarbonSetting.Theme, (b, v) -> b.theme(v));
        put(CarbonSetting.Watermark, (b, v) -> b.watermark(Boolean.parseBoolean(v)));
        put(CarbonSetting.WidthAdjustment, (b, v) -> b.widthAdjustment(Boolean.parseBoolean(v)));
        put(CarbonSetting.WindowControls, (b, v) -> b.windowControls(Boolean.parseBoolean(v)));
        put(CarbonSetting.WindowTheme, (b, v) -> b.windowTheme(getWindowThemeFromString(v)));
    }};

    private final CarbonService carbon;
    private final Parser parser;
    private final SettingsService settings;

    public CarbonCommand(CarbonService carbon, Parser parser, SettingsService settings) {
        this.carbon = carbon;
        this.parser = parser;
        this.settings = settings;
    }

    @Override
    public void execute(SlashCommandEvent event) {
        String key = event.getOption("message_id").getAsString();

        OptionalLong snowflake;
        try {
            snowflake = OptionalLong.of(Long.parseLong(key));
        } catch (NumberFormatException ex) {
            snowflake = parser.getSnowflakeFromURI(key);
        }

        if (key.length() > 85) // length of Discord message uri
        {
            key = key.substring(0, 84) + "...";
        }

        String errorMessage = String.format("Failed to find a message with ID `%s`.", key);
        if (snowflake.isEmpty()) {
            sendErrorEmbed(event, errorMessage, false);
            return;
        }

        event.deferReply().queue();

        event.getChannel()
                .retrieveMessageById(snowflake.getAsLong())
                .queue(message -> onMessageFound(message, event),
                        ex -> sendErrorEmbed(event, errorMessage, true));
    }

    private void onMessageFound(Message message, SlashCommandEvent event) {
        ImageOptionsBuilder optionsBuilder = getUserOptionsBuilder(event.getUser().getIdLong());

        String code;
        Optional<CodeBlock> codeBlockOptional = parser.getCodeBlock(message.getContentRaw());
        if (codeBlockOptional.isPresent()) {
            CodeBlock codeBlock = codeBlockOptional.get();
            code = codeBlock.getCode();
            optionsBuilder.language(codeBlock.getCarbonLanguage());
        } else {
            code = parser.stripSurroundingBackTicks(message.getContentRaw());
        }

        byte[] image;
        try {
            image = carbon.getImage(code, optionsBuilder.build());
        } catch (Exception ex) {
            sendErrorEmbed(event, "Failed to reach Carbon. Please try again later.", true);
            return;
        }

        event.getHook().sendFile(image, "carbon.png").queue();
    }

    private ImageOptionsBuilder getUserOptionsBuilder(long snowflake) {
        Map<CarbonSetting, String> userSettings;
        ImageOptionsBuilder optionsBuilder = new ImageOptionsBuilder();
        try {
            userSettings = settings.getAllUserCarbonSettings(snowflake);
        } catch (Exception ex) { // settings could not be retrieved
            ex.printStackTrace();
            return optionsBuilder;
        }

        for (Map.Entry<CarbonSetting, String> entry : userSettings.entrySet()) {
            CARBON_SETTING_CONSUMER_MAP.get(entry.getKey())
                    .accept(optionsBuilder, entry.getValue());
        }

        return optionsBuilder;
    }

    private static WindowTheme getWindowThemeFromString(String themeString) {
        try {
            return WindowTheme.valueOf(themeString);
        } catch (IllegalArgumentException ex) {
        }

        WindowTheme theme;
        switch (themeString.toLowerCase()) {
            case "sharp":
                theme = WindowTheme.SharpEdges;
                break;
            case "bw":
            case "outlined":
                theme = WindowTheme.Outlined;
                break;
            case "none":
            case "rounded":
            default:
                theme = WindowTheme.RoundedEdges;
        }

        return theme;
    }

    @FunctionalInterface
    private interface CarbonSettingConsumer extends BiConsumer<ImageOptionsBuilder, String> {

    }
}
