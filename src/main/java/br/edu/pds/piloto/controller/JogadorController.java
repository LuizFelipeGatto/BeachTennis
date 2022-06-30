package br.edu.pds.piloto.controller;

import br.edu.pds.piloto.model.Jogador;
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
public class JogadorController {

    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastrarJogador")
    public ModelAndView cadastrar(Jogador jogador){
        ModelAndView mv = new ModelAndView("jogador");
        mv.addObject("jogador", jogador);
        mv.addObject("usuarios", usuarioRepository.findAllUsuarios());
        return mv;
    }

    @GetMapping("/listarJogador")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("jogadores");
        mv.addObject("jogadores", jogadorRepository.findAll());
        return mv;
    }


    @PostMapping("/salvarJogador")
    public ModelAndView salvar(@Valid Jogador jogador, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(jogador);
        }

        jogadorRepository.saveAndFlush(jogador);

        return new ModelAndView("redirect:/listarJogador");
    }

    @GetMapping("/editarJogador/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return cadastrar(jogador.get());
    }

    @GetMapping("/excluirJogador/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        jogadorRepository.delete(jogador.get());
        return new ModelAndView("redirect:/listarJogador");
    }
}
