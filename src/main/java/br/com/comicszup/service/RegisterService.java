package br.com.comicszup.service;

import br.com.comicszup.client.ComicsFeing;
import br.com.comicszup.response.ItemsComicsResponse;
import br.com.comicszup.response.ResultComicsResponse;
import br.com.comicszup.entity.Comics;
import br.com.comicszup.entity.User;
import br.com.comicszup.erros.Mensagem;
import br.com.comicszup.repository.ComicsRepository;
import br.com.comicszup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

@Service
public class RegisterService {
    final String API_KEY = "fe85115da60362e08df5381e3935ce2d";
    final String PRIVATE_KEY = "62d96f85d2f93f84a754b3e297afd18f3d6b9179";

    @Autowired
    UserRepository userRepository;

    @Autowired
    ComicsRepository comicsRepository;

    @Autowired
    ComicsFeing comicsFeing;

    public ResponseEntity<Object> cadastroUsuario(User user) {
        try {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensagem("Erro ao cadastrar o usuário"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> findById(Long id){
        try{
            return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensagem("Usuário não encontrado."),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> adicionar(Long id, Comics comics){
        try {
            User user = userRepository.findById(id).get();
            comics.setUser(user);

            //Buscar informações na API da Marvel sobre o comic
            Long ts = gerarTimeStamp();
            String hash = gerarHash(ts, PRIVATE_KEY, API_KEY);
            List<ResultComicsResponse> resultado = comicsFeing.getByComicId(ts, API_KEY, hash, comics.getComicId())
                    .getData()
                    .getResults();

            //Adicionar as informações na entidade Comics
            comics.setTitulo(resultado.get(0).getTitle());
            comics.setPreco(resultado.get(0).getPrices().get(0).getPrice());
            comics.setISBN(resultado.get(0).getIsbn());

            //Salvar autores dentro de uma String
            List<ItemsComicsResponse> autores = resultado.get(0).getCreators().getItems();
            String nomeAutores = "";
            for (ItemsComicsResponse autor : autores){
                nomeAutores += autor.getName()+", ";
            }
            nomeAutores = nomeAutores.substring(0, nomeAutores.length()-2);
            comics.setAutores(nomeAutores);

            //Limitar quantidade dos caracteres devido ao SQL VARCHAR(255)
            comics.setDescricao(resultado.get(0).getDescription().substring(0, 254));

            //Verificando descontos
            comics.setDiaDoDesconto(comics.verificarDiaDoDesconto(comics.getISBN()));
            comics.setDescontoAtivo(comics.verificarDescontoAtivo());
            if (comics.getDescontoAtivo() == true) {
                comics.setValorComDesconto(Math.abs((10 * comics.getPreco() / 100) - comics.getPreco()));
            }

            return new ResponseEntity<>(comicsRepository.save(comics), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new Mensagem("Erro ao cadastrar o comic"), HttpStatus.BAD_REQUEST);
        }
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