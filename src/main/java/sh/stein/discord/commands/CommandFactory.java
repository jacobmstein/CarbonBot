package sh.stein.discord.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import sh.stein.settings.CarbonSetting;

public class CommandFactory {

    public CommandData[] getAllCommands() {
        return new CommandData[]{
                getCarbonCommand(), getHelpCommand(), getInviteCommand(), getMySettingsCommand(),
                getSetCommand(), getUnsetCommand()
        };
    }

    private CommandData getCarbonCommand() {
        return new CommandData("carbon", "Creates an image of the message contents")
                .addOption(OptionType.STRING, "message_id", "Message ID or link", true);
    }

    private CommandData getHelpCommand() {
        return new CommandData("help", "Displays the help menu for all commands")
                .addOption(OptionType.STRING, "command",
                        "Display the help menu for the set command", false);
    }

    private CommandData getInviteCommand() {
        return new CommandData("invite", "Displays buttons to invite Carbon Bot");
    }

    private CommandData getMySettingsCommand() {
        return new CommandData("mysettings", "Displays your settings");
    }

    private CommandData getSetCommand() {
        CommandData command = new CommandData("set", "Changes a default setting");
        command.addSubcommands(getCarbonSettingSubcommands(true));
        return command;
    }

    private CommandData getUnsetCommand() {
        CommandData command = new CommandData("unset", "Restores a setting to default");
        command.addSubcommands(getCarbonSettingSubcommands(false));
        return command;
    }

    private List<SubcommandData> getCarbonSettingSubcommands(boolean withValueOption) {
        OptionData valueOption = new OptionData(OptionType.STRING, "value",
                "The specified setting's new value")
                .setRequired(true);

        return Arrays.stream(CarbonSetting.values())
                .map(setting -> {
                    String key = setting.getKey();
                    String description = String.format("Change your %s setting", key);
                    return new SubcommandData(setting.getKey(), description);
                })
                .map(subcommand -> withValueOption ? subcommand.addOptions(valueOption)
                        : subcommand)
                .collect(Collectors.toList());
    }
}
