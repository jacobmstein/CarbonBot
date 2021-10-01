package sh.stein.discord.commands.impl;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import sh.stein.discord.commands.BaseSlashCommand;
import sh.stein.settings.CarbonSetting;
import sh.stein.settings.SettingsService;

public class UnsetCommand extends BaseSlashCommand {

    private final SettingsService settings;

    public UnsetCommand(SettingsService settings) {
        this.settings = settings;
    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.deferReply(true).queue();
        String key = event.getSubcommandName();
        try {
            CarbonSetting setting = CarbonSetting.fromKey(key);
            settings.unsetUserCarbonSetting(getUserSnowflake(event), setting);
            sendSuccessEmbed(event, String.format("`%s` unset.", key), true);
        } catch (IllegalArgumentException ex) {
            sendErrorEmbed(event, "Illegal argument, see `/help` for more informtation.", true);
        } catch (Exception ex) {
            sendErrorEmbed(event, "Please try again later.", true);
        }
    }
}
