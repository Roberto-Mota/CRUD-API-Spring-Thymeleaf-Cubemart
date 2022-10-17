package br.com.mvc.cubemart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(/*HttpServletRequest request*/ Model model) {
        model.addAttribute("atributo", "valor");
        return "hello";
        /*
         * Ao retornar apenas "hello" como string, retorna uma view para o usuario com o mesmo nome de um arquivo html na pasta resources/templates ("hello.html")
         */
    }
}
