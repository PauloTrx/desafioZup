package br.com.comicszup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class Comics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private BigDecimal preco;
    @Column(nullable = false)
    private String autores;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String descricao;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    //Informações coletadas via API
    @Column(name = "id_revista")
    private Integer idRevista;
    private String upc;
    private String formato;
    @Column(name = "quantidade_paginas")
    private String quantidadePaginas;

    //Informações do desconto
    private String diaDoDesconto;
    private Boolean descontoAtivo;

    public Comics() { }

    public Long getId() { return id; }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdRevista() { return idRevista; }

    public void setIdRevista(Integer idRevista) { this.idRevista = idRevista; }

    public String getUpc() { return upc; }

    public void setUpc(String upc) { this.upc = upc; }

    public String getFormato() { return formato; }

    public void setFormato(String formato) { this.formato = formato; }

    public String getQuantidadePaginas() { return quantidadePaginas; }

    public void setQuantidadePaginas(String quantidadePaginas) { this.quantidadePaginas = quantidadePaginas; }

    public String getDiaDoDesconto() {
        return diaDoDesconto;
    }

    public void setDiaDoDesconto(String diaDoDesconto) {
        this.diaDoDesconto = diaDoDesconto;
    }

    public Boolean getDescontoAtivo() {
        return descontoAtivo;
    }

    public void setDescontoAtivo(Boolean descontoAtivo) {
        this.descontoAtivo = descontoAtivo;
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

        LocalDate dataAtual = LocalDate.now();
        LocalDate later = dataAtual.plusDays(1);
        Locale portugues = new Locale("pt", "BR");
        String diaDaSemanaAtual = later.getDayOfWeek().getDisplayName(TextStyle.FULL, portugues);
        System.out.println(diaDaSemanaAtual);
        if(diaDaSemanaAtual.equals(diaDoDesconto)){
            return true;
        }else{
            return false;
        }
    }
}