package br.com.comicszup.service;

import br.com.comicszup.client.ComicsFeing;
import br.com.comicszup.client.response.ItemsComicsResponseFeing;
import br.com.comicszup.client.response.ResultComicsResponseFeing;
import br.com.comicszup.dto.response.ComicsResponseDTO;
import br.com.comicszup.entity.Comics;
import br.com.comicszup.entity.User;
import br.com.comicszup.Exception.MessageException;
import br.com.comicszup.repository.ComicsRepository;
import br.com.comicszup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
            if (userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByCPF(user.getCPF()).isPresent()){
                return new ResponseEntity<>(new MessageException("Email ou CPF já cadastrado"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageException("Erro ao cadastrar o usuário"), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> adicionar(Long id, Comics comics){
        try {
            User user = userRepository.findById(id).get();
            comics.setUser(user);

            //Buscar informações na API da Marvel sobre o comic
            Long timestamp = new Date().getTime();
            String hash = gerarHash(timestamp, PRIVATE_KEY, API_KEY);
            List<ResultComicsResponseFeing> resultado = comicsFeing.getByComicId(timestamp, API_KEY, hash, comics.getComicId())
                    .getData()
                    .getResults();

            //Adicionar as informações na entidade Comics
            comics.setTitulo(resultado.get(0).getTitle());
            comics.setPreco(resultado.get(0).getPrices().get(0).getPrice());
            comics.setISBN(resultado.get(0).getIsbn());

            //Salvar autores dentro de uma String
            List<ItemsComicsResponseFeing> autores = resultado.get(0).getCreators().getItems();
            String nomeAutores = "";
            for (ItemsComicsResponseFeing autor : autores){
                nomeAutores += autor.getName()+", ";
            }
            nomeAutores = nomeAutores.substring(0, nomeAutores.length()-2);
            comics.setAutores(nomeAutores);

            //Limitar quantidade dos caracteres devido ao SQL VARCHAR(255)
            comics.setDescricao(resultado.get(0).getDescription().substring(0, 254));

            //Verificando descontos
            comics.setDiaDoDesconto(verificarDiaDoDesconto(comics.getISBN()));
            comics.setDescontoAtivo(verificarDescontoAtivo(comics.getDiaDoDesconto()));
            if (comics.getDescontoAtivo() == true) {
                comics.setPreco(Math.abs((10 * comics.getPreco() / 100) - comics.getPreco()));
            }
            comicsRepository.save(comics);
            return new ResponseEntity<>(new ComicsResponseDTO(comics), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageException("Erro ao cadastrar o comic"), HttpStatus.BAD_REQUEST);
        }
    }

    public String gerarHash(Long timeStamp, String apikey, String privatekey) {
        try {
            String ts = Long.toString(timeStamp);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(ts.getBytes());
            md5.update(apikey.getBytes());
            md5.update(privatekey.getBytes());
            BigInteger hash = new BigInteger(1, md5.digest());
            return hash.toString(16);

        } catch (Exception e) {
            System.out.println(e);
        }
        return apikey;
    }


    public ResponseEntity<Object> findById(Long id){
        try{
            return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new MessageException("Usuário não encontrado."),HttpStatus.NOT_FOUND);
        }
    }


    public String verificarDiaDoDesconto(String isbn){

        String ultimoDigitoIsbn = isbn.substring(isbn.length()-1);

        switch (ultimoDigitoIsbn){
            case "0": case "1":
                return "segunda-feira";

            case "2": case "3":
                return "terça-feira";

            case "4": case "5":
                return "quarta-feira";

            case "6": case "7":
                return "quinta-feira";

            case "8": case "9":
                return "sexta-feira";

            default:
                return "inválido";
        }
    }

    public Boolean verificarDescontoAtivo(String diaDoDesconto){
        Locale portugues = new Locale("pt", "BR");
        LocalDate dataAtual = LocalDate.now();
        dataAtual = dataAtual.plusDays(5);
        String diaDaSemanaAtual = dataAtual
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, portugues);
        if(diaDaSemanaAtual.equals(diaDoDesconto)){
            return true;
        }else{
            return false;
        }
    }
}