package io.github.gandrade.aggregator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
