package sh.stein.discord.commands.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import sh.stein.discord.commands.BaseSlashCommand;
import sh.stein.settings.CarbonSetting;
import sh.stein.settings.SettingsService;

public class SetCommand extends BaseSlashCommand {

    private final SettingsService settings;

    public SetCommand(SettingsService settings) {
        this.settings = settings;
    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.deferReply(true).queue();
        String key = event.getSubcommandName();
        String value = event.getOption("value").getAsString();
        try {
            CarbonSetting setting = CarbonSetting.fromKey(key);
            settings.setUserCarbonSetting(getUserSnowflake(event), setting, value);
            sendSuccessEmbed(event, String.format("`%s` set to `%s`.", key, value), true);
        } catch (IllegalArgumentException ex) {
            sendErrorEmbed(event, "Illegal argument, see `/help` for more information.", true);
        } catch (Exception ex) {
            sendErrorEmbed(event, "Please try again later.", true);
            ex.printStackTrace();
        }
    }
}
