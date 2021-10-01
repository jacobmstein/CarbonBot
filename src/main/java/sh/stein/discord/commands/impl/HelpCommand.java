package sh.stein.discord.commands.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import sh.stein.discord.commands.BaseSlashCommand;
import sh.stein.discord.text.SplitTable;
import sh.stein.settings.CarbonSetting;

public class HelpCommand extends BaseSlashCommand {

    private static final Map<CarbonSetting, String> SETTINGS_DESCRIPTION_MAP = new HashMap<>() {{
        put(CarbonSetting.BackgroundColor, "hex or rgba, e.g. `rgba(248, 232, 28, 1)`");
        put(CarbonSetting.DropShadow, "boolean, e.g. `true` or `false`");
        put(CarbonSetting.DropShadowBlurRadius, "px value, e.g. `20px`");
        put(CarbonSetting.DropShadowOffsetY, "px value");
        put(CarbonSetting.FontFamily, "*");
        put(CarbonSetting.FontSize, "px value");
        put(CarbonSetting.FirstLineNumber, "number");
        put(CarbonSetting.LineHeight, "percentage, e.g. `200%`");
        put(CarbonSetting.LineNumbers, "boolean");
        put(CarbonSetting.PaddingHorizontal, "px");
        put(CarbonSetting.PaddingVertical, "px");
        put(CarbonSetting.Theme, "*");
        put(CarbonSetting.Watermark, "boolean");
        put(CarbonSetting.WidthAdjustment, "boolean");
        put(CarbonSetting.WindowControls, "boolean");
        put(CarbonSetting.WindowTheme, "`rounded`, `sharp` (sharp edges), or `outlined`");
    }};

    @Override
    public void execute(SlashCommandEvent event) {
        MessageEmbed helpEmbed;
        OptionMapping option = event.getOption("command");

        if (option == null) {
            helpEmbed = getHelpEmbed();
        } else {
            String page = option.getAsString();
            switch (page.toLowerCase()) {
                case "set":
                case "unset":
                    helpEmbed = getSetUnsetHelpEmbed();
                    break;
                default:
                    helpEmbed = getHelpEmbed();
            }
        }

        event.replyEmbeds(helpEmbed).setEphemeral(true).queue();
    }

    private MessageEmbed getHelpEmbed() {
        return new EmbedBuilder()
                .setTitle("Help")
                .setDescription("To get more information on the `set` command use `/help set`.")
                .addField("Carbon Command", HelpMessage.CarbonCommand.getMessage(), false)
                .addField("Invite Command", HelpMessage.InviteCommand.getMessage(), false)
                .addField("My Settings Command", HelpMessage.MySettingsCommand.getMessage(), false)
                .addField("Set Command", HelpMessage.SetCommand.getMessage(), false)
                .addField("Unset Command", HelpMessage.UnsetCommand.getMessage(), false)
                .setFooter("Find Carbon Bot on GitHub, https://github.com/jacobmstein/CarbonBot")
                .build();
    }

    private MessageEmbed getSetUnsetHelpEmbed() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "The following settings are currently supported for the `set` and `unset` commands:\n");

        Map<String, String> tableContents = SETTINGS_DESCRIPTION_MAP.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> {
                    CarbonSetting setting = entry.getKey();
                    return setting.getKey();
                }, entry -> entry.getValue()));

        SplitTable table = new SplitTable(tableContents);
        stringBuilder.append(table);

        return new EmbedBuilder()
                .setTitle("Set/Unset Help")
                .addField("Set command", HelpMessage.SetCommand.getMessage(), false)
                .addField("Unset command", HelpMessage.UnsetCommand.getMessage(), false)
                .addField("Setting descriptions", stringBuilder.toString(), false)
                .setFooter(
                        "* possible values are available by viewing exported Carbon config files")
                .build();
    }

    private enum HelpMessage {
        CarbonCommand("`/carbon <message_id>` creates an image of the message contents using"
                + " Carbon"),
        InviteCommand("`/invite` displays buttons to invite Carbon Bot to your guild"),
        MySettingsCommand("`/mysettings` displays your settings"),
        SetCommand("`/set <setting> <value>` changes a default setting"),
        UnsetCommand("`/unset <setting>` restores a setting to default");

        private String message;

        HelpMessage(String message) {
            this.message = message;
        }

        String getMessage() {
            return this.message;
        }
    }
}
