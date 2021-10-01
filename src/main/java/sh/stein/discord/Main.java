package sh.stein.discord;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import redis.clients.jedis.JedisPool;
import sh.stein.carbon.CarbonService;
import sh.stein.carbon.PlaywrightCarbonService;
import sh.stein.config.Config;
import sh.stein.discord.commands.CommandFactory;
import sh.stein.discord.text.Parser;
import sh.stein.settings.RedisSettingsService;
import sh.stein.settings.SettingsService;

public class Main {

    public static void main(String[] args) throws IOException, LoginException, URISyntaxException {
        Config config = Config.fromFile("config.properties");

        CarbonService carbon = new PlaywrightCarbonService();
        JedisPool pool = new JedisPool(new URI(config.getRedisURI()));
        SettingsService settings = new RedisSettingsService(pool);

        Bot bot = getBot(carbon, new Parser(), settings, config);
        JDA jda = login(config.getDiscordBotToken(), bot);
        addCommands(jda);
    }

    private static Bot getBot(CarbonService carbon, Parser parser, SettingsService settings,
            Config config) {
        return new Bot(carbon, parser, settings, config);
    }

    private static JDA login(String botToken, Bot bot) throws LoginException {
        return JDABuilder.createLight(botToken, EnumSet.noneOf(GatewayIntent.class))
                .addEventListeners(bot)
                .setActivity(Activity.playing("use /help"))
                .build();
    }

    private static void addCommands(JDA jda) {
        CommandListUpdateAction commands = jda.updateCommands();
        CommandFactory commandFactory = new CommandFactory();
        commands.addCommands(commandFactory.getAllCommands());
        commands.queue();
    }
}
