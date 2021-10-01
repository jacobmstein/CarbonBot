package sh.stein.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private String discordBotToken;
    private String discordInviteURI;
    private String redisURI;

    private Config() {
    }

    public String getDiscordBotToken() {
        return discordBotToken;
    }

    public String getDiscordInviteURI() {
        return discordInviteURI;
    }

    public String getRedisURI() {
        return redisURI;
    }

    public static Config fromFile(String name) throws IOException {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(name)) {
            properties.load(input);

            Config config = new Config();
            config.discordBotToken = properties.getProperty("discord.bot.token");
            config.discordInviteURI = properties.getProperty("discord.invite.uri");
            config.redisURI = properties.getProperty("redis.uri");

            return config;
        }
    }
}
