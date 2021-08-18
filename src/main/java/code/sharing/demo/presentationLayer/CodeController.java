package code.sharing.demo.presentationLayer;



import code.sharing.demo.models.Code;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CodeController {


    HttpHeaders responseHeaders = new HttpHeaders();

    Code code;

    @Autowired
    public CodeController(Code code) {
        this.code = code;
    }

    @GetMapping("/api/code")
    public ResponseEntity<Code> getJsonCode() {
        System.out.println("\n\n\nAPI CODE");
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(code.returnJsonCode());
    }
    @GetMapping("/code")
    public ResponseEntity<String> getHtmlCode() {
        System.out.println("\n\n\nCODE");
        responseHeaders.set("Content-Type", "text/html");
        return ResponseEntity.ok().headers(responseHeaders).body(code.returnHtmlCode());
    }

}

