package com.sistemavidaplus.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O login é obrigatório")
    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @NotBlank(message = "A senha é obrigatória")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Recebe a senha, mas não mostra no JSON
    @Column(nullable = false, length = 255)
    private String senha;

    @NotBlank(message = "O tipo de usuário é obrigatório")
    @Column(length = 50)
    private String tipoUsuario; // "ADMIN" ou "PROFISSIONAL"

    @NotBlank(message = "O contato ou especialidade é obrigatório")
    @Column(length = 255)
    private String contatoEspecialidade;


    public UsuarioEntity() { }


    public UsuarioEntity(String nome, String login, String senha, String tipoUsuario, String contatoEspecialidade) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.contatoEspecialidade = contatoEspecialidade;
    }


    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public String getContatoEspecialidade() {
        return contatoEspecialidade;
    }
    public void setContatoEspecialidade(String contatoEspecialidade) {
        this.contatoEspecialidade = contatoEspecialidade;
    }
}