package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FichaClinicaDAO {

    private String realPathBase;

    public FichaClinicaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public void salvarFichaClinica(String nome, String anotacoes, String prescricao) {
        String sql = "INSERT INTO prontuarios (paciente_id, anotacoes_medicas, prescricoes) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, anotacoes_medicas);
            stmt.setString(3, prescricoes);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar ficha clínica no banco de dados.", e);
        }
    }
}
