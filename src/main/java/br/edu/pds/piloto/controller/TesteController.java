package br.edu.pds.piloto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TesteController {
    String msg = "Bem vindo, olá mundo!";

    @GetMapping("/olaMundo")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("index.html");
        mv.addObject("mensagem", msg);
        return mv;
    }
}
