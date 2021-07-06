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
    private User user;

    //Informações do desconto
    private String diaDoDesconto;
    private Boolean descontoAtivo;

    public Comics() { }

    public Long getId() { return id; }

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

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getDiaDoDesconto() { return diaDoDesconto; }

    public void setDiaDoDesconto(String diaDoDesconto) { this.diaDoDesconto = diaDoDesconto; }

    public Boolean getDescontoAtivo() { return descontoAtivo; }

    public void setDescontoAtivo(Boolean descontoAtivo) { this.descontoAtivo = descontoAtivo; }


}