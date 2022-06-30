package br.edu.pds.piloto.controller;


import br.edu.pds.piloto.model.Permissao;
import br.edu.pds.piloto.repository.PermissaoRepository;
import br.edu.pds.piloto.repository.RoleRepository;
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
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastrarPermissao")
    public ModelAndView cadastrar(Permissao permissao){
        ModelAndView mv = new ModelAndView("permissao");
        mv.addObject("permissao", permissao);
        mv.addObject("usuarios", usuarioRepository.findAll());
        mv.addObject("roles", roleRepository.findAll());
        return mv;
    }

    @GetMapping("/listarPermissao")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("permissoes");
        mv.addObject("permissoes", permissaoRepository.findAll());
        return mv;
    }


    @PostMapping("/salvarPermissao")
    public ModelAndView salvar(@Valid Permissao permissao, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(permissao);
        }

        permissaoRepository.saveAndFlush(permissao);

        return new ModelAndView("redirect:/listarPermissao");
    }

    @GetMapping("/editarPermissao/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Permissao> permissao = permissaoRepository.findById(id);
        return cadastrar(permissao.get());
    }

    @GetMapping("/excluirPermissao/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Permissao> permissao = permissaoRepository.findById(id);
        permissaoRepository.delete(permissao.get());
        return new ModelAndView("redirect:/listarPermissao");
    }
}
