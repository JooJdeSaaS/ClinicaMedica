package com.mack.clinica.model;

/**
 * Modelo que representa o usuário do sistema.
 */
public class Usuario {
    private int id;
    private String nome;
    private String tipo; // paciente ou admin
    private String email;
    private String CPF;
    private String celular;
    private String created_at;



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

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCPF(String CPF) {
        this.CPF = String.format("%s.%s.%s-%s",
                CPF.substring(0, 3),
                CPF.substring(3, 6),
                CPF.substring(6, 9),
                CPF.substring(9, 11));
    }

    public String getCPF() {
        return CPF;
    }

    public void setCelular(long celular) {
        String s = String.format("%011d", celular);
        this.celular = String.format("(%s) %s-%s",
                s.substring(0, 2),
                s.substring(2, 7),
                s.substring(7));
    }

    public String getCelular() {
        return celular;
    }

    public void setCreated_at(String created_at) {this.created_at = created_at;}

    public String getCreated_at() {return created_at;}
}
