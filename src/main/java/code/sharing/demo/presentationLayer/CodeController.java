package code.sharing.demo.presentationLayer;


import code.sharing.demo.models.Code;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
//@RestController
public class CodeController {


    HttpHeaders responseHeaders = new HttpHeaders();

    List<Code> allCodes = new ArrayList<>();

    @ResponseBody
    @GetMapping("/api/code/{N}")
    public ResponseEntity<Code> getJsonCode(@PathVariable int N) {
//        System.out.println("\n\n\nAPI CODE");
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(allCodes.get(N - 1));
    }

    @GetMapping("/code/{N}")
    public String getHtmlCode(@PathVariable int N, Model model) {
//        System.out.println("\n\n\nCODE");
        responseHeaders.set("Content-Type", "text/html");
        model.addAttribute("code", allCodes.get(N - 1));
//        return ResponseEntity.ok().headers(responseHeaders).body("codePresent");
        return "codePresent";
    }

    @ResponseBody
    @PostMapping("/api/code/new")
    public ResponseEntity<String> postApiCode(@RequestBody Code code) {
//        System.out.println("\n\n\n1");
        allCodes.add(code);
//        System.out.println("\n\n\n2");
        responseHeaders.set("Content-Type", "application/json");
        JSONObject responseBody = new JSONObject();
        responseBody.put("id", String.valueOf(allCodes.size()));
//        System.out.println("\n\n\n3");
        return ResponseEntity.ok().headers(responseHeaders).body(responseBody.toString());
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> postHtmlCode() {
        responseHeaders.set("Content-Type", "text/html");
        return ResponseEntity.ok().headers(responseHeaders).body(Code.returnHtmlForInput());
    }

    @ResponseBody
    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Code>> latestCodes() {
        responseHeaders.set("Content-Type", "application/json");
        return ResponseEntity.ok().headers(responseHeaders).body(getLatestItems());
    }

    @GetMapping("/code/latest")
    public String latestCodesHtml(Model model) {
        model.addAttribute("allCodes", getLatestItems());
        responseHeaders.set("Content-Type", "text/html");
//        return ResponseEntity.ok().headers(responseHeaders).body("allCodes");
        return "allCodes";
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



