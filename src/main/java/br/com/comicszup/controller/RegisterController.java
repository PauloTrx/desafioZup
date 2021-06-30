package br.com.comicszup.controller;

import br.com.comicszup.entity.Comics;
import br.com.comicszup.entity.User;
import br.com.comicszup.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RegisterController {

    @Autowired
    RegisterService registerService;

    //Cadastrar user
    @PostMapping("/user")
    public ResponseEntity<Object> cadastroUsuario(@RequestBody User user){
       return registerService.cadastroUsuario(user);
    }

    //Retornar usuario com a lista de seus comics cadastrados.
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        return registerService.findById(id);
    }

    //Cadastrar comics
    @PostMapping("user/{id}/comics")
    public ResponseEntity<Object> adicionar(@PathVariable Long id, @RequestBody Comics comics){
        return registerService.adicionar(id, comics);
    }
}
