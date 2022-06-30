package br.edu.pds.piloto.controller;

import br.edu.pds.piloto.model.Role;
import br.edu.pds.piloto.repository.RoleRepository;
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
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/cadastrarRole")
    public ModelAndView cadastrar(Role role){
        ModelAndView mv = new ModelAndView("role");
        mv.addObject("role", role);
        return mv;
    }

    @GetMapping("/listarRole")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("roles");
        mv.addObject("roles", roleRepository.findAll());
        return mv;
    }


    @PostMapping("/salvarRole")
    public ModelAndView salvar(@Valid Role role, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(role);
        }

        roleRepository.saveAndFlush(role);

        return new ModelAndView("redirect:/listarRole");
    }

    @GetMapping("/editarRole/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Role> role = roleRepository.findById(id);
        return cadastrar(role.get());
    }

    @GetMapping("/excluirRole/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Role> role = roleRepository.findById(id);
        roleRepository.delete(role.get());
        return new ModelAndView("redirect:/listarRole");
    }
}
