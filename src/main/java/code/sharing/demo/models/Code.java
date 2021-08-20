package code.sharing.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;



public class Code implements Comparable<Code>{

    private String code = """
            public static void main(String[] args) {
                    SpringApplication.run(CodeSharingPlatform.class, args);
                }""";

    private LocalDateTime date = LocalDateTime.now().withNano(0);

    public String returnHtmlCode() {
        String result = """
                <html>
                <head>
                    <title>Code</title>
                </head>
                <body>
                    <span id="load_date">%s</span>
                    <pre id="code_snippet">%s</pre>
                </body>
                </html>""";
        return String.format(result, date.toString(), code);
    }

    public static String returnHtmlForInput() {
        return """
                <html>
                <head>
                    <title>Create</title>
                </head>
                <body>
                    <script src="static/converter.js"></script>
                    <textarea id="code_snippet"></textarea>
                    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
                </body>
                </html>
                
                
                """;
    }

    public Code returnJsonCode() {
        return this;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setCode(String code) {
        this.code = code;
        date = LocalDateTime.now().withNano(0);
    }


    @Override
    public int compareTo(Code otherObject) {
        return date.compareTo(otherObject.getDate()) * -1;
    }
}
