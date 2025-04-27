package com.mack.clinica.model;

/**
 * Modelo que representa o usuário do sistema.
 */
public class Usuario {
    private int id;
    private String nome;
    private String tipo; // paciente ou admin
    private String CPF;
    private String celular;



    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCPF(String CPF) {this.CPF = CPF;}

    public String getCPF() {return CPF;}

    public void setCelular(String celular) {this.celular = celular;}

    public String getCelular() {return celular;}
}
