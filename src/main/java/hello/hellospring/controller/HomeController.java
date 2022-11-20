package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {  //controller에 매핑된 페이지가 있으면 먼저 화면출력 없으면 정적페이지 index.html
        return "home";
    }
}
