package com.mack.clinica.model;

public class Medico {
    private int id;
    private String nome;
    private String email;
    private String CPF;
    private String celular;
    private String tipo; // paciente ou admin
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

    //salvar do banco de dados e formatando para jsp
    public void setCPF(String CPF) {
        this.CPF = String.format("%s.%s.%s-%s",
                CPF.substring(0, 3),
                CPF.substring(3, 6),
                CPF.substring(6, 9),
                CPF.substring(9));
    }
    //salvar do jsp (formatado)
    public void setCPFformatado(String CPF) {
        this.CPF = CPF;
    }
    //formatando o CPF para o banco de dados
    public String getCPFformatado() {
        return CPF.replaceAll("\\D", "");

    
}
    public String getCelularFormatado() {
        return celular.replaceAll("\\D", "");
    }
}
