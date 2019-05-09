package maven.controller.pageJump;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageJumpController {
    @GetMapping(value = {"/"})
    public String index() {
        return "index";
    }


}
