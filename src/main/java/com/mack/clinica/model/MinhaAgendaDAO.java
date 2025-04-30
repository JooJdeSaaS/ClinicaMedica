package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MinhaAgendaDAO {

    private String realPathBase;

    public MinhaAgendaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

     public List<Consulta> buscarConsultasPorPacienteId(int pacienteId) {
        List<Consulta> consultas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(realPathBase)) {
            String sql = "SELECT id, paciente_id, profissional_id, data_hora, status, observacoes FROM consultas WHERE paciente_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pacienteId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int profissionalId = rs.getInt("profissional_id");
                String dataHora = rs.getString("data_hora");
                String status = rs.getString("status");
                String observacoes = rs.getString("observacoes");

                Consulta consulta = new Consulta(id, pacienteId, profissionalId, dataHora, status, observacoes);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar consultas no banco de dados.", e);
        }

        return consultas;
    }
}
