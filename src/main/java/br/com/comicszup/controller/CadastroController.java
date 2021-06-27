package br.com.comicszup.controller;

import br.com.comicszup.client.ComicsFeing;
import br.com.comicszup.dto.response.ResultComicsResponseDTO;
import br.com.comicszup.entity.Comics;
import br.com.comicszup.entity.Usuario;
import br.com.comicszup.repository.ComicsRepository;
import br.com.comicszup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class CadastroController {
    final String API_KEY = "fe85115da60362e08df5381e3935ce2d";
    final String PRIVATE_KEY = "62d96f85d2f93f84a754b3e297afd18f3d6b9179";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ComicsRepository comicsRepository;

    @Autowired
    ComicsFeing comicsFeing;

    //Cadastrar usuario
    @PostMapping("/usuario")
    public Usuario cadastroUsuario(@RequestBody Usuario user) {
        return usuarioRepository.save(user);
    }

    //Retornar usuario com a lista de seus comics cadastrados.
    @GetMapping("/usuario/{id}")
    public Usuario findById(@PathVariable Long id){
        return usuarioRepository.findById(id).get();
    }


    //Cadastrar comics
    @PostMapping("usuario/{id}/comics")
    public Comics adicionar(@PathVariable Long id, @RequestBody Comics comics){
        Usuario usuario = usuarioRepository.findById(id).get();
        comics.setUsuario(usuario);

        //Buscar informações na API da Marvel sobre o comic
        Long ts = gerarTimeStamp();
        String hash = gerarHash(ts, PRIVATE_KEY, API_KEY);
        List<ResultComicsResponseDTO> resultado = comicsFeing.getByTitle(ts, API_KEY, hash, comics.getTitulo())
                .getData()
                .getResults();

        //Adicionar as informações na entidade Comics
        for (ResultComicsResponseDTO item : resultado){
            comics.setIdRevista(item.getId());
            comics.setUpc(item.getUpc());
            comics.setFormato(item.getFormat());
            comics.setQuantidadePaginas(item.getPageCount());
        }
        return comicsRepository.save(comics);
    }










    public Long gerarTimeStamp() {
        return new Date().getTime();
    }

    public String gerarHash(Long timeStamp, String apikey, String privatekey) {
        try {
            String ts = Long.toString(timeStamp);
            String hd;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(ts.getBytes());
            md5.update(apikey.getBytes());
            md5.update(privatekey.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            hd = hash.toString(16);
            while ( hd.length() < 32 ) { hd = "0" + hd; } // pad with leading 0's
            return hd;

        } catch (Exception e) {
            System.out.println(e);

        }

        return apikey;
    }
}



/*
    @GetMapping("/usuario")
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


*/
