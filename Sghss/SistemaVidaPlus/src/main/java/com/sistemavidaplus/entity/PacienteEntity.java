package com.sistemavidaplus.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "Paciente")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaciente;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(length = 14)
    private String cpf;

    @NotBlank(message = "O endereço é obrigatório")
    @Column(length = 255)
    private String endereco;

    @NotBlank(message = "O contato é obrigatório")
    @Column(length = 20)
    private String contato;


    public PacienteEntity() { }


    public PacienteEntity(String nome, LocalDate dataNascimento, String cpf, String endereco, String contato) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.contato = contato;
    }


    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
}