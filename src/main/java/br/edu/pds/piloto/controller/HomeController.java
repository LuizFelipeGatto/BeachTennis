package br.edu.pds.piloto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("index.html");
        mv.addObject("mensagem", "Futuro Dashboard");
        return mv;
    }
}
