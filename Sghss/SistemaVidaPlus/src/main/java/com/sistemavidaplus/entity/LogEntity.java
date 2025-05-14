package com.sistemavidaplus.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Log")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLog;

    @NotNull(message = "A data e hora do log é obrigatória")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotBlank(message = "A ação é obrigatória")
    @Column(length = 100)
    private String acao;

    @NotNull(message = "O usuário que realizou a açao é obrigatório")
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private UsuarioEntity usuarioEntity;


    public LogEntity() { }


    public LogEntity(LocalDateTime dataHora, String descricao, String acao, UsuarioEntity usuarioEntity) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.acao = acao;
        this.usuarioEntity = usuarioEntity;
    }


    public int getIdLog() {
        return idLog;
    }
    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getAcao() {
        return acao;
    }
    public void setAcao(String acao) {
        this.acao = acao;
    }
    public UsuarioEntity getUsuario() {
        return usuarioEntity;
    }
    public void setUsuario(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
}