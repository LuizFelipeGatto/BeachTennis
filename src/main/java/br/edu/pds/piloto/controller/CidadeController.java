package br.edu.pds.piloto.controller;

import br.edu.pds.piloto.model.Cidade;
import br.edu.pds.piloto.repository.CidadeRepositorio;
import br.edu.pds.piloto.repository.EstadoRepositorio;
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

//    @Override
//    public List<Categoria> getCategorias(String url){
//
//        String accessToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZWxsZXJJZCI6Niwic2NvcGUiOlsibWFy";
//
//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("Authorization", accessToken);
//
//        RequestEntity<Object> request = new RequestEntity<>(
//                headers, HttpMethod.GET, URI.create(url));
//
//
//        ResponseEntity<Categoria[]> response = restTemplate.exchange(request, Categoria[].class);
//
//
//        return Arrays.asList(response.getBody());
//
//    }

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
        cidadeRepositorio.delete(cidade.get());
        return new ModelAndView("redirect:/listarCidade");
    }
}
