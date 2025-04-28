package com.mack.clinica.model;

public class Consulta {
    private int id;
    private int pacienteId;
    private int profissionalId;
    private String dataHora;
    private String status;
    private String observacoes;

    public Consulta(int id, int pacienteId, int profissionalId, String dataHora, String status, String observacoes) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.profissionalId = profissionalId;
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

    public String getDataHora() {
        return dataHora;
    }

    public String getStatus() {
        return status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
    public String toString() {
        return "Consulta [ID=" + id +
               ", Paciente ID=" + pacienteId +
               ", Profissional ID=" + profissionalId +
               ", Data e Hora=" + dataHora +
               ", Status=" + status +
               ", Observações=" + observacoes + "]";
    }
}
