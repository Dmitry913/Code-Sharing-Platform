package code.sharing.demo.presentationLayer;


import code.sharing.demo.models.Code;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class CodeController {


    HttpHeaders responseHeaders = new HttpHeaders();

    List<Code> allCodes = new ArrayList<>();


    @GetMapping("/api/code/{N}")
    public ResponseEntity<Code> getJsonCode(@PathVariable int N) {
//        System.out.println("\n\n\nAPI CODE");
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(allCodes.get(N - 1));
    }

    @GetMapping("/code/{N}")
    public ResponseEntity<String> getHtmlCode(@PathVariable int N, Model model) {
//        System.out.println("\n\n\nCODE");
        responseHeaders.set("Content-Type", "text/html");
        model.addAttribute("code", allCodes.get(N - 1));
        return ResponseEntity.ok().headers(responseHeaders).body("codePresent");
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<JSONObject> postApiCode(@RequestBody Code code) {
        allCodes.add(code);
        responseHeaders.set("Content-Type", "application/json");
        JSONObject responseBody = new JSONObject();
        responseBody.put("id", allCodes.size());
        return ResponseEntity.ok().headers(responseHeaders).body(responseBody);
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> postHtmlCode() {
        responseHeaders.set("Content-Type", "text/html");
        return ResponseEntity.ok().headers(responseHeaders).body(Code.returnHtmlForInput());
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Code>> latestCodes() {
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(getLatestItems());
    }

    @GetMapping("/code/latest")
    public ResponseEntity<String> latestCodesHtml(Model model) {
        model.addAttribute("allCodes", getLatestItems());
        responseHeaders.set("Content-Type", "text/html");
        return ResponseEntity.ok().headers(responseHeaders).body("allCodes");
    }

    public List<Code> getLatestItems() {
        List<Code> sortedList = new ArrayList<>(allCodes);
        Collections.sort(sortedList);
        if (sortedList.size() > 10) {
            return sortedList.subList(0, 10);
        } else {
            return sortedList;
        }
    }
}



