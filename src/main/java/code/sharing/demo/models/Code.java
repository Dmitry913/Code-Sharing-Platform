package code.sharing.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Code {
    private String code = """
            public static void main(String[] args) {
                    SpringApplication.run(CodeSharingPlatform.class, args);
                }""";

    public String returnHtmlCode() {
        String result = """
                <html>
                <head>
                    <title>Code</title>
                </head>
                <body>
                    <pre>%s</pre>
                </body>
                </html>""";
        return String.format(result, code);
    }

    public Code returnJsonCode() {
        return this;
    }

    public String getCode() {
        return code;
    }

}
