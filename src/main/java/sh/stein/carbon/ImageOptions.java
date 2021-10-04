package sh.stein.carbon;

/**
 * The set of image options currently supported by Carbon.
 */
public class ImageOptions {

    private Language language = Language.Auto;
    private String backgroundColor = "rgba(171, 184, 195, 1)";
    private boolean dropShadow = true;
    private String dropShadowBlurRadius = "68px";
    private String dropShadowOffsetY = "20px";
    private String fontSize = "14px";
    private String fontFamily = "Hack";
    private boolean lineNumbers = false;
    private int firstLineNumber = 1;
    private String lineHeight = "133%";
    private String paddingHorizontal = "56px";
    private String paddingVertical = "56px";
    private int scaleFactor = 2;
    private String theme = "seti";
    private boolean watermark = false;
    private boolean widthAdjustment = true;
    private boolean windowControls = true;
    private WindowTheme windowTheme = WindowTheme.RoundedEdges;

    private ImageOptions() {
    }

    public static ImageOptions getDefault() {
        return new ImageOptions();
    }

    public Language getLanguage() {
        return language;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public boolean getDropShadow() {
        return dropShadow;
    }

    public String getDropShadowBlurRadius() {
        return dropShadowBlurRadius;
    }

    public String getDropShadowOffsetY() {
        return dropShadowOffsetY;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public String getFontSize() {
        return fontSize;
    }

    public boolean getLineNumbers() {
        return lineNumbers;
    }

    public int getFirstLineNumber() {
        return firstLineNumber;
    }

    public String getLineHeight() {
        return lineHeight;
    }

    public String getPaddingHorizontal() {
        return paddingHorizontal;
    }

    public String getPaddingVertical() {
        return paddingVertical;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public String getTheme() {
        return theme;
    }

    public boolean getWatermark() {
        return watermark;
    }

    public boolean getWidthAdjustment() {
        return widthAdjustment;
    }

    public boolean getWindowControls() {
        return windowControls;
    }

    public WindowTheme getWindowTheme() {
        return windowTheme;
    }

    /**
     * Represents one of the three window styles supported by Carbon.
     */
    public enum WindowTheme {
        RoundedEdges,
        SharpEdges,
        Outlined
    }

    /**
     * The languages currently supported by Carbon.
     */
    public enum Language {
        Auto, Apache, Bash, C, CPlusPlus, CSharp, Clojure, COBOL, CoffeeScript, Crystal, CSS, D,
        Dart, Diff, Django, Docker, Elixir, Elm, Erlang, Fortran, Gherkin, GraphQL, Go, Groovy,
        Handlebars, Haskell, HTMLXML, Java, JavaScript, JSON, JSX, Julia, Kotlin, LaTeX, Lisp, Lua,
        Markdown, Mathematica, MATLABOctave, MySQL, NTriples, NGINX, Nim, ObjectiveC, OCamlFSharp,
        Pascal, Perl, PHP, PlainText, PowerShell, Python, R, RISCV, Ruby, Rust, Sass, Scala,
        Smalltalk, Solidity, SPARQL, SQL, Stylus, Swift, TCL, TOML, Turtle, TypeScript, TSX, Twig,
        VBDOTNET, Verilog, VHDL, Vue, XQuery, YAML
    }

    /**
     * Used to build an instance of {@code ImageOptions} fluently.
     */
    public static class ImageOptionsBuilder {

        private final ImageOptions options = new ImageOptions();

        public ImageOptionsBuilder language(Language language) {
            options.language = language;
            return this;
        }

        public ImageOptionsBuilder backgroundColor(String backgroundColor) {
            options.backgroundColor = backgroundColor;
            return this;
        }

        public ImageOptionsBuilder dropShadow(boolean dropShadow) {
            options.dropShadow = dropShadow;
            return this;
        }

        public ImageOptionsBuilder dropShadowBlurRadius(String dropShadowBlurRadius) {
            options.dropShadowBlurRadius = dropShadowBlurRadius;
            return this;
        }

        public ImageOptionsBuilder dropShadowOffsetY(String dropShadowOffsetY) {
            options.dropShadowOffsetY = dropShadowOffsetY;
            return this;
        }

        public ImageOptionsBuilder fontFamily(String fontFamily) {
            options.fontFamily = fontFamily;
            return this;
        }

        public ImageOptionsBuilder fontSize(String fontSize) {
            options.fontSize = fontSize;
            return this;
        }

        public ImageOptionsBuilder lineNumbers(boolean lineNumbers) {
            options.lineNumbers = lineNumbers;
            return this;
        }

        public ImageOptionsBuilder firstLineNumber(int firstLineNumber) {
            options.firstLineNumber = firstLineNumber;
            return this;
        }

        public ImageOptionsBuilder lineHeight(String lineHeight) {
            options.lineHeight = lineHeight;
            return this;
        }

        public ImageOptionsBuilder paddingHorizontal(String paddingHorizontal) {
            options.paddingHorizontal = paddingHorizontal;
            return this;
        }

        public ImageOptionsBuilder paddingVertical(String paddingVertical) {
            options.paddingVertical = paddingVertical;
            return this;
        }

        public ImageOptionsBuilder scaleFactor(int scaleFactor) {
            options.scaleFactor = scaleFactor;
            return this;
        }

        public ImageOptionsBuilder theme(String theme) {
            options.theme = theme;
            return this;
        }

        public ImageOptionsBuilder watermark(boolean watermark) {
            options.watermark = watermark;
            return this;
        }

        public ImageOptionsBuilder widthAdjustment(boolean widthAdjustment) {
            options.widthAdjustment = widthAdjustment;
            return this;
        }

        public ImageOptionsBuilder windowControls(boolean windowControls) {
            options.windowControls = windowControls;
            return this;
        }

        public ImageOptionsBuilder windowTheme(WindowTheme windowTheme) {
            options.windowTheme = windowTheme;
            return this;
        }

        public ImageOptions build() {
            return options;
        }
    }
}
