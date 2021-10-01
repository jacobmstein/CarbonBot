package sh.stein.discord.commands.impl;

import java.util.Map;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import sh.stein.discord.commands.BaseSlashCommand;
import sh.stein.discord.text.SplitTable;
import sh.stein.settings.CarbonSetting;
import sh.stein.settings.SettingsService;

public class MySettingsCommand extends BaseSlashCommand {

    private final SettingsService settings;

    public MySettingsCommand(SettingsService settings) {
        this.settings = settings;
    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.deferReply(true).queue();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("To get more information on the `set` command use `/help set`.\n");

        long snowflake = getUserSnowflake(event);
        Map<CarbonSetting, String> userSettings;
        try {
            userSettings = settings.getAllUserCarbonSettings(snowflake);
        } catch (Exception ex) {
            sendErrorEmbed(event, "Please try again later.", true);
            ex.printStackTrace();
            return;
        }

        if (userSettings.isEmpty()) {
            sendErrorEmbed(event, "You have no saved settings.", true);
            return;
        }

        Map<String, String> tableContents = userSettings.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> {
                    CarbonSetting setting = entry.getKey();
                    return setting.getKey();
                }, entry -> String.format("`%s`", entry.getValue())));

        SplitTable table = new SplitTable(tableContents);
        stringBuilder.append(table);

        sendEmbed(event, "Your Settings", stringBuilder.toString(), true);
    }
}
