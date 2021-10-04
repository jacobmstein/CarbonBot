package sh.stein.carbon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sh.stein.carbon.ImageOptions.Language;

/**
 * Nested classes within ImageOptions CarbonService functional interface Handling default value
 * Language constant map ImageOptionsBuilder naming Consumer map/nested functional interface in
 * discord.impl.CarbonCommand
 */

public class Example {

    public static void main(String[] args) throws IOException {
        CarbonService carbon = new PlaywrightCarbonService();
        ImageOptions options = new ImageOptions.ImageOptionsBuilder()
                .language(Language.Markdown)
                .theme("hopscotch")
                .fontFamily("Monoid")
                .dropShadow(false)
                .paddingHorizontal("10px")
                .paddingVertical("10px")
                .build();

        byte[] image = carbon.getImage(new File("README.md"), options);

        try (FileOutputStream stream = new FileOutputStream("out.png")) {
            stream.write(image);
        }
    }

}
