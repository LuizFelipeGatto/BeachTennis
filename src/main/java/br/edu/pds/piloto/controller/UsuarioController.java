package br.edu.pds.piloto.controller;

import br.edu.pds.piloto.model.Usuario;
import br.edu.pds.piloto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastrarUsuario")
    public ModelAndView cadastrar(Usuario usuario){
        ModelAndView mv = new ModelAndView("usuario");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("/listarUsuario")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("usuarios");
        mv.addObject("usuarios", usuarioRepository.findAll());
        return mv;
    }


    @PostMapping("/salvarUsuario")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result){
        if(result.hasErrors()){
//            usuario.setSenha(usuario.getSenha()); criptografar
            return cadastrar(usuario);
        }

        if(usuario.getSenha().length() < 20){
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        }

        usuarioRepository.saveAndFlush(usuario);

        return new ModelAndView("redirect:/listarUsuario");
    }

    @GetMapping("/editarUsuario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return cadastrar(usuario.get());
    }

    @GetMapping("/excluirUsuario/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuarioRepository.delete(usuario.get());
        return new ModelAndView("redirect:/listarUsuario");
    }
}
