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

    public String getCPF() {
        return CPF;
    }


    //esse aqui é o que vem do banco de dados e vai formatado para o jsp
    public void setCelularFormatado(long celular) {
        String s = String.format("%011d", celular);
        this.celular = String.format("(%s) %s-%s",
                s.substring(0, 2),
                s.substring(2, 7),
                s.substring(7));
    }
    //salvar do jsp
    public void setCelularFormatado(String celular) {
        if (celular.equals("")){
            this.celular = "";
            return;
        }
        String digits = celular.replaceAll("\\D", "");
        long num = 0L;
        if (!digits.isEmpty()) {
            num = Long.parseLong(digits);
        }
        String s = String.format("%011d", num);
        this.celular = String.format("(%s) %s-%s",
                s.substring(0, 2),
                s.substring(2, 7),
                s.substring(7));
    }
    //formatar para salvar no banco de dados
    public long getCelularFormatado() {
        if (celular == null || celular.equals("")) {
            return 0;
        }
        String onlyDigits = celular.replaceAll("\\D", "");
        return Long.parseLong(onlyDigits);
    }

    public String getCelular() {
        return celular;
    }

    public void setCreated_at(String created_at) {this.created_at = created_at;}

    public String getCreated_at() {return created_at;}
}
