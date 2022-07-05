package br.edu.pds.piloto.controller;

import br.edu.pds.piloto.model.Cidade;
import br.edu.pds.piloto.repository.CidadeRepositorio;
import br.edu.pds.piloto.repository.EstadoRepositorio;
import br.edu.pds.piloto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CidadeController {

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastrarCidade")
    public ModelAndView cadastrar(Cidade cidade){
        ModelAndView mv = new ModelAndView("cidade");
        mv.addObject("cidade", cidade);
        mv.addObject("estados", estadoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/listarCidade")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("cidades");
        mv.addObject("cidades", cidadeRepositorio.findAll());
        return mv;
    }


    @PostMapping("/salvarCidade")
    public ModelAndView salvar(@Valid Cidade cidade, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(cidade);
        }

        cidadeRepositorio.saveAndFlush(cidade);

        return new ModelAndView("redirect:/listarCidade");
    }

    @GetMapping("/editarCidade/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Cidade> cidade = cidadeRepositorio.findById(id);
        return cadastrar(cidade.get());
    }

    @GetMapping("/excluirCidade/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Cidade> cidade = cidadeRepositorio.findById(id);
        ModelAndView mv = new ModelAndView("redirect:/listarCidade");
        if(usuarioRepository.findByCidade(id) > 0) {
            mv.addObject("messageType", "error");
            mv.addObject("message", "Exitem dados que fazem o uso desse cidade.");
            return mv;
        }

        cidadeRepositorio.delete(cidade.get());
        mv.addObject("messageType", "success");
        mv.addObject("message", "Cidade removida com sucesso.");
        return new ModelAndView("redirect:/listarCidade");
    }
}
