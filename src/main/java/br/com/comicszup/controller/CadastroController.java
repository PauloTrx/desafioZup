package br.com.comicszup.controller;

import br.com.comicszup.client.ComicsFeing;
import br.com.comicszup.dto.response.ComicsResponseDTO;
import br.com.comicszup.entity.Comics;
import br.com.comicszup.entity.Usuario;
import br.com.comicszup.repository.ComicsRepository;
import br.com.comicszup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping
public class CadastroController {
    final String API_KEY = "fe85115da60362e08df5381e3935ce2d";
    final String PRIVATE_KEY = "62d96f85d2f93f84a754b3e297afd18f3d6b9179";
    final String HASH = "D7C2126256E219656F347863F1A63B84";

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ComicsRepository comicsRepository;

    @Autowired
    ComicsFeing comicsFeing;

    @PostMapping("/usuario")
    public Usuario cadastroUsuario(@RequestBody Usuario user) {
        return usuarioRepository.save(user);
    }

    @GetMapping("/usuario")
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuario/{id}")
    public Usuario findById(@PathVariable Long id){
        return usuarioRepository.getById(id);
    }


/*
    @GetMapping("/comics")
    public ComicsResponseDTO getAll() {
        Long ts = gerarTimeStamp();
        String hash = gerarHash(ts, PRIVATE_KEY, API_KEY);
        return comicsFeing.getAll(ts, API_KEY, hash);
    }
*/

    @GetMapping("/comics")
    public List<Comics> findAllComics(){
        return comicsRepository.findAll();
    }

    @PostMapping("/comics")
    public Comics adicionar(@RequestBody Comics comics){
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
