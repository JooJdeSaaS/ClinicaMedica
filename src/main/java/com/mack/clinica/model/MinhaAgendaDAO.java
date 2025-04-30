package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MinhaAgendaDAO {

    private String realPathBase;

    public MinhaAgendaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public List<Consulta> buscarConsultasPorPacienteId(int pacienteId) {
        List<Consulta> consultas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(realPathBase)) {
            String sql = "SELECT c.paciente_id, c.profissional_id, c.data_hora, c.status, c.observacoes, u.nome " +
                    "FROM consultas c " +
                    "JOIN usuarios u ON c.profissional_id = u.id " +
                    "WHERE c.paciente_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pacienteId);

            ResultSet rs = stmt.executeQuery();
            int count = 1;
            while (rs.next()) {
                int id = count;
                count++;
                String nomeProfissional = rs.getString("nome"); // agora pega o campo 'nome'
                String dataHora = rs.getString("data_hora");
                LocalDateTime dataHora1 = LocalDateTime.parse(dataHora);
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
                String dataFormatada = dataHora1.format(formatador);
                String status = rs.getString("status");
                String observacoes = rs.getString("observacoes");

                Consulta consulta = new Consulta(id, pacienteId, nomeProfissional, dataFormatada, status, observacoes);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar consultas no banco de dados.", e);
        }

        return consultas;
    }
}
