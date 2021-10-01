package sh.stein.discord.text;

import java.util.Arrays;
import java.util.Map;

public class SplitTable {

    private final Map<String, String> contents;

    public SplitTable(Map<String, String> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int keyColumnLength = getLongestKeyLength();
        for (Map.Entry<String, String> entry : contents.entrySet()) {
            String key = entry.getKey();
            String description = entry.getValue();

            stringBuilder
                    .append('`')
                    .append(getPadArray(keyColumnLength - key.length()))
                    .append(key)
                    .append('`')
                    .append(' ')
                    .append(description)
                    .append('\n');
        }

        return stringBuilder.toString();
    }

    private int getLongestKeyLength() {
        return contents.keySet()
                .stream()
                .mapToInt(String::length)
                .max()
                .getAsInt();
    }

    private char[] getPadArray(int length) {
        char[] padding = new char[length];
        Arrays.fill(padding, ' ');
        return padding;
    }
}
