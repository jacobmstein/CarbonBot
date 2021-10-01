package sh.stein.settings;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisSettingsService implements SettingsService {

    private JedisPool pool;

    public RedisSettingsService(JedisPool jedis) {
        this.pool = jedis;
    }

    @Override
    public Optional<String> getUserCarbonSetting(long snowflake, CarbonSetting setting) {
        try (Jedis jedis = pool.getResource()) {
            String value = jedis.hget(getHashKey(snowflake), setting.getKey());
            return value == null ? Optional.empty() : Optional.of(value);
        }
    }

    @Override
    public Map<CarbonSetting, String> getAllUserCarbonSettings(long snowflake) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.hgetAll(getHashKey(snowflake))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(entry -> CarbonSetting.fromKey(entry.getKey()),
                            entry -> entry.getValue()));
        }
    }

    @Override
    public void setUserCarbonSetting(long snowflake, CarbonSetting setting, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hset(getHashKey(snowflake), setting.getKey(), value);
        }
    }

    @Override
    public void unsetUserCarbonSetting(long snowflake, CarbonSetting setting) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hdel(getHashKey(snowflake), setting.getKey());
        }
    }

    private String getHashKey(long snowflake) {
        return String.format("carbon-settings:users:%d", snowflake);
    }
}
