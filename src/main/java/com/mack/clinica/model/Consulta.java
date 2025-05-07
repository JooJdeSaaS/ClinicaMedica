package com.mack.clinica.model;

public class Consulta {
    private int id;
    private int pacienteId;
    private int profissionalId;
    private String nomeProfissional;
    private String dataHora;
    private String status;
    private String observacoes;


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

    public String getNomeProfissional() {return nomeProfissional;}

    public String getDataHora() {
        return dataHora;
    }

    public String getStatus() {
        return status;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
