package sh.stein.discord;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sh.stein.carbon.CarbonService;
import sh.stein.config.Config;
import sh.stein.discord.commands.SlashCommand;
import sh.stein.discord.commands.impl.CarbonCommand;
import sh.stein.discord.commands.impl.HelpCommand;
import sh.stein.discord.commands.impl.InviteCommand;
import sh.stein.discord.commands.impl.MySettingsCommand;
import sh.stein.discord.commands.impl.SetCommand;
import sh.stein.discord.commands.impl.UnsetCommand;
import sh.stein.discord.text.Parser;
import sh.stein.settings.SettingsService;

public class Bot extends ListenerAdapter {

    private final CarbonService carbon;
    private final Parser parser;
    private final SettingsService settings;
    private final Config config;

    public Bot(CarbonService carbon, Parser parser, SettingsService settings, Config config) {
        this.carbon = carbon;
        this.parser = parser;
        this.settings = settings;
        this.config = config;
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        SlashCommand command = null;
        String commandName = event.getSubcommandGroup();
        if (commandName == null) {
            commandName = event.getName();
        }
        switch (commandName) {
            case "carbon":
                command = new CarbonCommand(carbon, parser, settings);
                break;
            case "help":
                command = new HelpCommand();
                break;
            case "invite":
                command = new InviteCommand(config);
                break;
            case "set":
                command = new SetCommand(settings);
                break;
            case "unset":
                command = new UnsetCommand(settings);
                break;
            case "mysettings":
                command = new MySettingsCommand(settings);
        }

        command.execute(event);
    }
}
