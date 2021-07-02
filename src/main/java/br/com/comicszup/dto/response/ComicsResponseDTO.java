package br.com.comicszup.dto.response;

import br.com.comicszup.entity.Comics;

public class ComicsResponseDTO {
    private String comicId;
    private String titulo;
    private Float preco;
    private String autores;
    private String ISBN;
    private String descricao;

    public ComicsResponseDTO() {
    }

    public ComicsResponseDTO(Comics comics){
        this.comicId = comics.getComicId();
        this.titulo = comics.getTitulo();
        this.preco = comics.getPreco();
        this.autores = comics.getAutores();
        this.ISBN = comics.getISBN();
        this.descricao = comics.getDescricao();
    }

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
}
