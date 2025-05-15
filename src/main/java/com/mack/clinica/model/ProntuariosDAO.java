package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProntuariosDAO {

    private String realPathBase;

    public ProntuariosDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public void salvarFichaClinica(Integer paciente_id, Integer profissional_id, String anotacoes_medicas, String prescricoes, String data) {
        String sql = "INSERT INTO prontuarios (paciente_id, profissional_id, data, anotacoes_medicas, prescricoes) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, paciente_id);
            stmt.setInt(2, profissional_id);
            stmt.setString(3, data);
            stmt.setString(4, anotacoes_medicas);
            stmt.setString(5, prescricoes);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar ficha clínica no DAO");
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar ficha clínica no banco de dados.", e);
        }
    }


}
