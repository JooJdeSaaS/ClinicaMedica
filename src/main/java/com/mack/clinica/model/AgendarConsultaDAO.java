package com.mack.clinica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mack.clinica.util.DatabaseConnection;

public class AgendarConsultaDAO {

    private String nomePaciente;
    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }
    private String realPathBase;

    public AgendarConsultaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public boolean agendarConsulta(int pacienteId, int profissionalId, String dataHora) {
        String sql = "INSERT INTO consultas (paciente_id, profissional_id, data_hora, status, observacoes) VALUES (?, ?, ?, 'agendada', '')";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pacienteId);
            stmt.setInt(2, profissionalId);
            stmt.setString(3, dataHora);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("entrou aqui");
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> listarMedicos() {
        List<Usuario> medicos = new ArrayList<>();
        String sql = "SELECT id, nome FROM usuarios WHERE tipo = 'medico'";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                medicos.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médicos: " + e.getMessage());
        }

        return medicos;
    }
    public List<Consulta> listarTodasConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT c.id, c.data_hora, c.status, " +
                "p.nome AS nomePaciente, m.nome AS nomeMedico " +
                "FROM consultas c " +
                "JOIN usuarios p ON c.paciente_id = p.id " +
                "JOIN usuarios m ON c.profissional_id = m.id";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setDataHora(rs.getString("data_hora"));
                consulta.setStatus(rs.getString("status"));
                consulta.setNomePaciente(rs.getString("nomePaciente"));
                consulta.setNomeMedico(rs.getString("nomeMedico"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas: " + e.getMessage());
        }

        return consultas;
    }
    public List<Consulta> buscarConsultasFiltradas(Integer medicoId, String data) {
        List<Consulta> consultas = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT c.id, c.paciente_id, p.nome AS nome_paciente, c.profissional_id, m.nome AS nome_medico, c.data_hora, c.status, c.observacoes ");
        sql.append("FROM consultas c ");
        sql.append("JOIN usuarios p ON c.paciente_id = p.id ");
        sql.append("JOIN usuarios m ON c.profissional_id = m.id ");
        sql.append("WHERE 1=1 ");

        if (medicoId != null) {
            sql.append("AND c.profissional_id = ? ");
        }
        if (data != null && !data.isEmpty()) {
            sql.append("AND DATE(c.data_hora) = ? ");
        }

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (medicoId != null) {
                stmt.setInt(index++, medicoId);
            }
            if (data != null && !data.isEmpty()) {
                stmt.setString(index, data);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Consulta c = new Consulta(
                        rs.getInt("id"),
                        rs.getInt("paciente_id"),
                        rs.getString("nome_medico"),
                        rs.getString("data_hora"),
                        rs.getString("status"),
                        rs.getString("observacoes")
                );
                c.setNomePaciente(rs.getString("nome_paciente"));
                c.setProfissionalId(rs.getInt("profissional_id"));
                consultas.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao filtrar consultas: " + e.getMessage());
        }

        return consultas;
    }

    public List<Consulta> buscarTodasConsultas() {
        return buscarConsultasFiltradas(null, null);
    }

    public void cancelarConsulta(int consultaId) {
        String sql = "UPDATE consultas SET status = 'cancelada' WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, consultaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cancelar consulta: " + e.getMessage());
        }
    }

}
