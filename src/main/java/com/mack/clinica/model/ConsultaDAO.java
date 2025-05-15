<<<<<<<< HEAD:src/main/java/com/mack/clinica/model/ConsultaDAO.java
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

public class ConsultaDAO {

    private String realPathBase;

    public ConsultaDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    //Minha agenda
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

    //Agendar Consulta
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

    //Agendar Consulta, Consultar agenda, ficha clinica
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

    //agendar consulta e consultar agenda
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
    //consultar agenda
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
========
>>>>>>>> origin/main:src/main/java/com/mack/clinica/model/AgendarConsultaDAO.java
