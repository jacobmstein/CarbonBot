package sh.stein.discord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public abstract class BaseSlashCommand implements SlashCommand {

    public abstract void execute(SlashCommandEvent event);

    protected long getUserSnowflake(SlashCommandEvent event) {
        return event.getUser().getIdLong();
    }

    protected void sendEmbed(SlashCommandEvent event, String title, String message,
            boolean followUp) {
        MessageEmbed embed = new EmbedBuilder()
                .setTitle(title)
                .setDescription(message)
                .build();

        if (followUp) {
            event.getHook().sendMessageEmbeds(embed).setEphemeral(true).queue();
            return;
        }

        event.replyEmbeds(embed).setEphemeral(true).queue();
    }

    protected void sendErrorEmbed(SlashCommandEvent event, String message, boolean followUp) {
        sendEmbed(event, "Error", message, followUp);
    }

    protected void sendSuccessEmbed(SlashCommandEvent event, String message, boolean followUp) {
        sendEmbed(event, "Success", message, followUp);
    }
}
