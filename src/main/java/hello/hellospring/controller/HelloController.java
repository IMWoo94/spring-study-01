package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        /*
        * 웹 브라우저에서 url 요청
        * Dispatcher Servlet 에서 HandlerMapping 에게 Requset에 대해 매핑할 controller 검색을 요청
        * HandlerMapping으로 부터 controller 정보를 반환 받아 매핑한다.
        * HandlerAdapter를 통해서 찾아낸 controller를 직접 실행한다.
        * HandlerInterceptor는 필요에 의해서 필터의 역할로 사용된다.
        * controller 단에서 서비스 및 로직이 처리되고 view 정보를 리턴한다.
        * Dispatcher Servlet은 반환 받은 view 정보를 ViewResolver에게 넘겨주고 해당 view를 탐색한다.
        * view 렌더링 처리
        */

        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
    * ResponseBody를 사용하게 되면 view Resolver 대신 httpMessageConverter가 동작한다.
    * 기본문자는 StringHttpMessageConverter
    * 객체는 MappingJackson2HttpMessageConverter
    * */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
