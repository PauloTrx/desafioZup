package br.com.comicszup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

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

}