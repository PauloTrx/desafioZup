package br.com.comicszup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class Comics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String comicId;
    //Informações coletadas via API
    private String titulo;
    private Float preco;
    private String autores;
    private String ISBN;
    private String descricao;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    //Informações do desconto
    private String diaDoDesconto;
    private Boolean descontoAtivo;
    private Float valorComDesconto;

    public Comics() { }

    public String getComicId() { return comicId; }

    public void setComicId(String comicId) { this.comicId = comicId; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Float getPreco() { return preco; }

    public void setPreco(Float preco) { this.preco = preco; }

    public String getAutores() { return autores; }

    public void setAutores(String autores) { this.autores = autores; }

    public String getISBN() { return ISBN; }

    public void setISBN(String ISBN) { this.ISBN = ISBN; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getDiaDoDesconto() { return diaDoDesconto; }

    public void setDiaDoDesconto(String diaDoDesconto) { this.diaDoDesconto = diaDoDesconto; }

    public Boolean getDescontoAtivo() { return descontoAtivo; }

    public void setDescontoAtivo(Boolean descontoAtivo) { this.descontoAtivo = descontoAtivo; }

    public Float getValorComDesconto() { return valorComDesconto; }

    public void setValorComDesconto(Float valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
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
    public Boolean verificarDescontoAtivo(){
        Locale portugues = new Locale("pt", "BR");
        LocalDate dataAtual = LocalDate.now();
        String diaDaSemanaAtual = dataAtual.getDayOfWeek().getDisplayName(TextStyle.FULL, portugues);
        if(diaDaSemanaAtual.equals(diaDoDesconto)){
            return true;
        }else{
            return false;
        }
    }
}