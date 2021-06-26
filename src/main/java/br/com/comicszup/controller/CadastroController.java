package br.com.comicszup.controller;

import br.com.comicszup.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class CadastroController {

    @Autowired
    CadastroRepository cadastroRepository;

    @PostMapping
    Usuario usuario(@PathVariable  Usuario usuario){
        return cadastroRepository.save(usuario);
    }

    @GetMapping
    List<Usuario> listarTodos(){
        return cadastroRepository.findAll();
    }
}
