package sh.stein.discord.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.components.Button;
import sh.stein.config.Config;
import sh.stein.discord.commands.BaseSlashCommand;

public class InviteCommand extends BaseSlashCommand {

    private final Config config;

    public InviteCommand(Config config) {
        this.config = config;
    }

    @Override
    public void execute(SlashCommandEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Invite")
                .setDescription("Invite Carbon Bot to your guild using the button below.")
                .build();

        event.replyEmbeds(embed)
                .addActionRow(Button.link(config.getDiscordInviteURI(), "Invite me!"))
                .setEphemeral(true)
                .queue();
    }
}
