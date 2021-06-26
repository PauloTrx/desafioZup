package br.com.comicszup.controller;

import br.com.comicszup.entity.Usuario;
import br.com.comicszup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class CadastroController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario cadastroUsuario(@RequestBody Usuario user){
        return usuarioRepository.save(user);
    }

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

}
