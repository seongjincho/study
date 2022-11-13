package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; // viewResolver가 화면을 찾아서 처리
        //  lsof -i :8080
    }

    @GetMapping("hello-mvc") // cmd + p 옵션 넣어줄수 있음
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @RequestMapping("hello-string")
    @ResponseBody // http의 바디부에 이 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // cmd + shift + enter
        hello.setName(name);
        return hello; //json 방식으로 반환
    }
// 테스트
    static class Hello{
        private String name;  // ctrl + enter 로 getter, setter 

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
