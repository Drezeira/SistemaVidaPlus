package com.sistemavidaplus.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConsulta;

    @NotNull(message = "A data e hora da consulta é obrigatória")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @NotBlank(message = "O status da consulta é obrigatório")
    @Column(length = 50)
    private String status; //  "AGENDADA", "CANCELADA" ou "REAGENDADA".

    @NotBlank(message = "As observações são obrigatórias")
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @NotNull(message = "O usuário responsável é obrigatório")
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private UsuarioEntity usuarioEntity;

    @NotNull(message = "O paciente é obrigatório")
    @ManyToOne
    @JoinColumn(name = "idPaciente", nullable = false)
    private PacienteEntity pacienteEntity;


    public ConsultaEntity() { }


    public ConsultaEntity(LocalDateTime dataHora, String status, String observacoes, UsuarioEntity usuarioEntity, PacienteEntity pacienteEntity) {
        this.dataHora = dataHora;
        this.status = status;
        this.observacoes = observacoes;
        this.usuarioEntity = usuarioEntity;
        this.pacienteEntity = pacienteEntity;
    }


    public int getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public UsuarioEntity getUsuario() {
        return usuarioEntity;
    }
    public void setUsuario(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
    public PacienteEntity getPaciente() {
        return pacienteEntity;
    }
    public void setPaciente(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
    }
}