package sh.stein.settings;

public enum CarbonSetting {
    BackgroundColor("background-color"),
    DropShadow("drop-shadow"),
    DropShadowBlurRadius("drop-shadow-blur-radius"),
    DropShadowOffsetY("drop-shadow-offset-y"),
    FontFamily("font-family"),
    FontSize("font-size"),
    FirstLineNumber("first-line-number"),
    LineHeight("line-height"),
    LineNumbers("line-numbers"),
    PaddingHorizontal("padding-horizontal"),
    PaddingVertical("padding-vertical"),
    Theme("theme"),
    Watermark("watermark"),
    WidthAdjustment("width-adjustment"),
    WindowControls("window-controls"),
    WindowTheme("window-theme");

    private String key;

    CarbonSetting(String key) {
        this.key = key;
    }

    public static CarbonSetting fromKey(String key) {
        for (CarbonSetting setting : CarbonSetting.values()) {
            if (setting.getKey().equalsIgnoreCase(key)) {
                return setting;
            }
        }

        throw new IllegalArgumentException();
    }

    public String getKey() {
        return this.key;
    }
}
