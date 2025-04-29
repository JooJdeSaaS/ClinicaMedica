package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MinhaAgendaDAO {

    /**
     * Busca uma consulta pelo ID no banco de dados.
     * @param consultaId ID da consulta.
     * @param realPathBase Caminho real da aplicação para localizar o banco.
     * @return Objeto Consulta encontrado ou null se não encontrado.
     */
    public static Consulta buscarConsultaPorId(int consultaId, String realPathBase) {
        try (Connection conn = DatabaseConnection.getConnection(realPathBase)) {
            String sql = "SELECT id, paciente_id, profissional_id, data_hora, status, observacoes FROM consultas WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, consultaId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int pacienteId = rs.getInt("paciente_id");
                int profissionalId = rs.getInt("profissional_id");
                String dataHora = rs.getString("data_hora");
                String status = rs.getString("status");
                String observacoes = rs.getString("observacoes");

                return new Consulta(id, pacienteId, profissionalId, dataHora, status, observacoes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar consulta no banco de dados.", e);
        }

        return null;
    }
}
