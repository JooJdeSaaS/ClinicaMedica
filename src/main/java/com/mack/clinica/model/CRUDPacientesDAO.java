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

public class CRUDPacientesDAO {

    private String realPathBase;

    public CRUDPacientesDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public boolean criarUasuario(int CPF, int celular, String senha, String email, String nome) {
        String sql = "INSERT INTO usuarios (nome, email, cpf, celular, tipo, senha, created_at) VALUES (?, ?, ?, ?, 'paciente', ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, CPF);
            stmt.setInt(4, celular);
            stmt.setString(5, senha);
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = agora.format(formato);
            stmt.setString(6, dataHora);
            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("entrou aqui");
            e.printStackTrace();
            return false;
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email, cpf, celular, created_at FROM usuarios WHERE tipo = 'paciente'";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setCPF(rs.getString("cpf"));
                u.setCelular(rs.getLong("celular"));
                u.setCreated_at(rs.getString("created_at"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médicos: " + e.getMessage());
        }

        return usuarios;
    }

    public Usuario buscarPorCampo(String campo, String valor) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE " + campo + " = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1,valor);
            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setCPF(rs.getString("cpf"));
                    u.setEmail(rs.getString("email"));
                    u.setCelular(rs.getLong("celular"));
                    u.setCreated_at(rs.getString("created_at"));
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
}