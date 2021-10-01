package sh.stein.discord.text;

import static sh.stein.carbon.ImageOptions.Language;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CodeBlock {

    private Map<String, Language> DISCORD_CARBON_LANGUAGE_MAP = new HashMap<>() {{
        put("apache", Language.Apache);
        put("apacheconf", Language.Apache);
        put("bash", Language.Bash);
        put("sh", Language.Bash);
        put("zsh", Language.Bash);
        put("text", Language.PlainText);
        put("c", Language.C);
        put("h", Language.C);
        put("cpp", Language.CPlusPlus);
        put("hpp", Language.CPlusPlus);
        put("cc", Language.CPlusPlus);
        put("hh", Language.CPlusPlus);
        put("c++", Language.CPlusPlus);
        put("h++", Language.CPlusPlus);
        put("cxx", Language.CPlusPlus);
        put("hxx", Language.CPlusPlus);
        put("csharp", Language.CSharp);
        put("cs", Language.CSharp);
        put("clojure", Language.Clojure);
        put("clj", Language.Clojure);
        put("coffeescript", Language.CoffeeScript);
        put("coffee", Language.CoffeeScript);
        put("cson", Language.CoffeeScript);
        put("iced", Language.CoffeeScript);
        put("crystal", Language.Crystal);
        put("cr", Language.Crystal);
        put("css", Language.CSS);
        put("d", Language.D);
        put("dart", Language.Dart);
        put("diff", Language.Diff);
        put("patch", Language.Diff);
        put("django", Language.Django);
        put("jinja", Language.Django);
        put("dockerfile", Language.Docker);
        put("docker", Language.Docker);
        put("elixir", Language.Elixir);
        put("elm", Language.Elm);
        put("erlang", Language.Erlang);
        put("erl", Language.Erlang);
        put("fortran", Language.Fortran);
        put("f90", Language.Fortran);
        put("f95", Language.Fortran);
        put("gherkin", Language.Gherkin);
        put("go", Language.Go);
        put("golang", Language.Go);
        put("groovy", Language.Groovy);
        put("handlebars", Language.Handlebars);
        put("hbs", Language.Handlebars);
        put("html.hbs", Language.Handlebars);
        put("html.handlebars", Language.Handlebars);
        put("haskell", Language.Haskell);
        put("hs", Language.Haskell);
        put("xml", Language.HTMLXML);
        put("html", Language.HTMLXML);
        put("xhtml", Language.HTMLXML);
        put("rss", Language.HTMLXML);
        put("atom", Language.HTMLXML);
        put("xjb", Language.HTMLXML);
        put("xsd", Language.HTMLXML);
        put("xsl", Language.HTMLXML);
        put("plist", Language.HTMLXML);
        put("svg", Language.HTMLXML);
        put("java", Language.Java);
        put("jsp", Language.Java);
        put("javascript", Language.JavaScript);
        put("js", Language.JavaScript);
        put("json", Language.JSON);
        put("jsx", Language.JSX);
        put("julia", Language.Julia);
        put("julia-repl", Language.Julia);
        put("kotlin", Language.Kotlin);
        put("kt", Language.Kotlin);
        put("tex", Language.LaTeX);
        put("lisp", Language.Lisp);
        put("lua", Language.Lua);
        put("markdown", Language.Markdown);
        put("md", Language.Markdown);
        put("mkdown", Language.Markdown);
        put("mkd", Language.Markdown);
        put("mathematica", Language.Mathematica);
        put("mma", Language.Mathematica);
        put("wl", Language.Mathematica);
        put("matlab", Language.MATLABOctave);
        put("sql", Language.MySQL);
        put("nignx", Language.NGINX);
        put("nignxconf", Language.NGINX);
        put("nim", Language.Nim);
        put("nimrod", Language.Nim);
        put("objectivec", Language.ObjectiveC);
        put("mm", Language.ObjectiveC);
        put("objc", Language.ObjectiveC);
        put("obj-c", Language.ObjectiveC);
        put("obj-c++", Language.ObjectiveC);
        put("objective-c++", Language.ObjectiveC);
        put("ocaml", Language.OCamlFSharp);
        put("ml", Language.OCamlFSharp);
        put("fharp", Language.OCamlFSharp);
        put("fs", Language.OCamlFSharp);
        put("dpr", Language.Pascal);
        put("dfm", Language.Pascal);
        put("pas", Language.Pascal);
        put("pascal", Language.Pascal);
        put("perl", Language.Perl);
        put("pl", Language.Perl);
        put("pm", Language.Perl);
        put("php", Language.PHP);
        put("powershell", Language.PowerShell);
        put("ps", Language.PowerShell);
        put("ps1", Language.PowerShell);
        put("python", Language.Python);
        put("py", Language.Python);
        put("gyp", Language.Python);
        put("r", Language.R);
        put("ruby", Language.Ruby);
        put("rb", Language.Ruby);
        put("gemspec", Language.Ruby);
        put("podspec", Language.Ruby);
        put("thor", Language.Ruby);
        put("irb", Language.Ruby);
        put("rust", Language.Rust);
        put("rs", Language.Rust);
        put("SAS", Language.Sass);
        put("sas", Language.Sass);
        put("scala", Language.Scala);
        put("smalltalk", Language.Smalltalk);
        put("st", Language.Smalltalk);
        put("solidity", Language.Solidity);
        put("st", Language.Solidity);
        put("sql", Language.SQL);
        put("stylus", Language.Stylus);
        put("styl", Language.Stylus);
        put("swift", Language.Swift);
        put("tcl", Language.TCL);
        put("tk", Language.TCL);
        put("ini", Language.TOML);
        put("toml", Language.TOML);
        put("typescript", Language.TypeScript);
        put("ts", Language.TypeScript);
        put("tsx", Language.TSX);
        put("twig", Language.Twig);
        put("craftcms", Language.Twig);
        put("vbnet", Language.VBDOTNET);
        put("vb", Language.VBDOTNET);
        put("verilog", Language.Verilog);
        put("v", Language.Verilog);
        put("vhdl", Language.VHDL);
        put(" ", Language.Vue);
        put("xquery", Language.XQuery);
        put("xpath", Language.XQuery);
        put("xq", Language.XQuery);
        put("yml", Language.YAML);
        put("yaml", Language.YAML);
    }};

    private Optional<String> language;
    private String code;

    public CodeBlock(Optional<String> language, String code) {
        this.language = language;
        this.code = code;
    }

    public Optional<String> getLanguage() {
        return language;
    }

    public String getCode() {
        return code;
    }

    public Language getCarbonLanguage() {
        Language carbonLanguage = Language.Auto;
        if (language.isPresent()) {
            carbonLanguage = DISCORD_CARBON_LANGUAGE_MAP.get(language.get());
        }

        return carbonLanguage == null ? Language.Auto : carbonLanguage;
    }
}
