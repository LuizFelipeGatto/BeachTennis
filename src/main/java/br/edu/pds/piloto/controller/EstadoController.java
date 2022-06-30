package br.edu.pds.piloto.controller;

import br.edu.pds.piloto.model.Estado;
import br.edu.pds.piloto.repository.EstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EstadoController {

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @GetMapping("/cadastrarEstado")
    public ModelAndView cadastrar(Estado estado){
        ModelAndView mv = new ModelAndView("estado");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/listarEstado")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("estados");
        mv.addObject("estados", estadoRepositorio.findAll());
        return mv;
    }


    @PostMapping("/salvarEstado")
    public ModelAndView salvar(@Valid Estado estado, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(estado);
        }

        estadoRepositorio.saveAndFlush(estado);

        return new ModelAndView("redirect:/listarEstado");
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Estado> estado = estadoRepositorio.findById(id);
        return cadastrar(estado.get());
    }

    @GetMapping("/excluirEstado/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Estado> estado = estadoRepositorio.findById(id);
        estadoRepositorio.delete(estado.get());
        return new ModelAndView("redirect:/listarEstado");
    }

}
