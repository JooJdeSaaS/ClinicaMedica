package com.mack.clinica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.mack.clinica.util.DatabaseConnection;

public class CRUDMedicosDAO {
    private String realPathBase;

    public CRUDMedicosDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    
    public boolean criarMedico(Medico medico) {
        String sql = "INSERT INTO medicos (nome, email, cpf, celular, tipo, senha, created_at) VALUES (?, ?, ?, ?, 'paciente', ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEmail());
            stmt.setString(3, medico.getCPFformatado());
           
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = agora.format(formato);
            stmt.setString(6, dataHora);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao criar médico: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Medico> listarMedicos() {
        // Implementar lógica para listar médicos do banco de dados
        return new ArrayList<>(); // Retornar a lista de médicos
    }


    public boolean atualizarMedico(Medico medico) {
        // Implementar lógica para atualizar um médico no banco de dados
        return false; // Retornar true se a operação for bem-sucedida, caso contrário, false
    }


    public boolean deletarMedico(int id) {
        // Implementar lógica para deletar um médico pelo ID no banco de dados
        return false; // Retornar true se a operação for bem-sucedida, caso contrário, false
    }

    public Medico buscarMedico (int id) {
        // Implementar lógica para buscar um médico pelo ID no banco de dados
        return null; // Retornar o médico encontrado ou null se não encontrado
    }
}


    

