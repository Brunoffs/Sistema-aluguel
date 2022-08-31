package model;

import util.Contador;

public abstract class Pessoa {
    private String nome;
    private String email;
    private String cidade;
    private String senha;
    private Integer id;
    
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
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Pessoa(String nome, String email, String cidade, String senha) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
        this.senha = senha;
        this.id = Contador.ProximoId();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    
}
