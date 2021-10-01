package sh.stein.settings;

import java.util.Map;
import java.util.Optional;

public interface SettingsService {

    Optional<String> getUserCarbonSetting(long snowflake, CarbonSetting setting);

    Map<CarbonSetting, String> getAllUserCarbonSettings(long snowflake);

    void setUserCarbonSetting(long snowflake, CarbonSetting setting, String value);

    void unsetUserCarbonSetting(long snowflake, CarbonSetting setting);
}
