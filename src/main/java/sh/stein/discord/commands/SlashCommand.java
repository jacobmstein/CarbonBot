package sh.stein.discord.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

@FunctionalInterface
public interface SlashCommand {

    void execute(SlashCommandEvent event);
}
