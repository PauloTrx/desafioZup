package br.com.comicszup.controller;

import br.com.comicszup.entity.Usuario;
import br.com.comicszup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    Usuario usuario(@PathVariable  Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    List<Long> listarTodos(){
        return usuarioRepository.findAll();
    }
}
