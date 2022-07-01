package br.edu.pds.piloto.controller;

import br.edu.pds.piloto.model.Dupla;
import br.edu.pds.piloto.repository.DuplaRepository;
import br.edu.pds.piloto.repository.JogadorRepository;
import br.edu.pds.piloto.repository.UsuarioRepository;
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
public class DuplaController {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private DuplaRepository duplaRepository;

    @GetMapping("/cadastrarDupla")
    public ModelAndView cadastrar(Dupla dupla){
        ModelAndView mv = new ModelAndView("dupla");
        mv.addObject("dupla", dupla);
        mv.addObject("jogadores", jogadorRepository.findAll());
        return mv;
    }

    @GetMapping("/listarDupla")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("duplas");
        mv.addObject("duplas", duplaRepository.findAll());
        return mv;
    }


    @PostMapping("/salvarDupla")
    public ModelAndView salvar(@Valid Dupla dupla, BindingResult result){
        dupla.setNomeDupla(dupla.getPrimeiro().getNome() + "/" + dupla.getSegundo().getNome());
        if(result.hasErrors()){
            return cadastrar(dupla);
        }

        duplaRepository.saveAndFlush(dupla);

        return new ModelAndView("redirect:/listarDupla");
    }

    @GetMapping("/editarDupla/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Dupla> dupla = duplaRepository.findById(id);
        return cadastrar(dupla.get());
    }

    @GetMapping("/excluirDupla/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Dupla> dupla = duplaRepository.findById(id);
        duplaRepository.delete(dupla.get());
        return new ModelAndView("redirect:/listarDupla");
    }
}
