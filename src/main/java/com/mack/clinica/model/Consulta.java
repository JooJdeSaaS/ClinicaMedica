package com.mack.clinica.model;

public class Consulta {
    private int id;
    private int pacienteId;
    private int profissionalId;
    private String nomePaciente;
    private String nomeMedico;
    private String nomeProfissional; // opcional: se quiser manter
    private String dataHora;
    private String status;
    private String observacoes;

    // Construtor vazio necessário para uso com setters
    public Consulta() {}


    public Consulta(int id, int pacienteId, String nomeProfissional, String dataHora, String status, String observacoes) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.nomeProfissional = nomeProfissional;
        this.dataHora = dataHora;
        this.status = status;
        this.observacoes = observacoes;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public int getProfissionalId() {
        return profissionalId;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getStatus() {
        return status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public void setProfissionalId(int profissionalId) {
        this.profissionalId = profissionalId;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}