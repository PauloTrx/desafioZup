package br.com.comicszup.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String CPF;
    @Column(nullable = false)
    private LocalDate dataDeNascimento;
    @OneToMany(mappedBy = "usuario")
    List<Comics> comics;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String CPF, LocalDate dataDeNascimento, List<Comics> comics) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.CPF = CPF;
        this.dataDeNascimento = dataDeNascimento;
        this.comics = comics;
    }

    public Long getId(){
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public List<Comics> getComics() {
        return comics;
    }

}
