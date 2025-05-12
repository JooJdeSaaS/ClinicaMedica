package com.mack.clinica.model;

import com.mack.clinica.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CRUDMedicosDAO {

    private String realPathBase;

    public CRUDMedicosDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, nome, email, cpf, celular, created_at FROM usuarios WHERE tipo = 'medico'";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setCPF(rs.getString("cpf"));
                u.setCelularFormatado(rs.getLong("celular"));
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
        String sql = "SELECT * FROM usuarios WHERE " + campo + " = ? AND tipo = 'medico'";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCPF(rs.getString("cpf"));
                u.setEmail(rs.getString("email"));
                u.setCelularFormatado(rs.getLong("celular"));
                u.setCreated_at(rs.getString("created_at"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u;
    }
    public boolean criarUsuario(Usuario usuario, String senha) {
        String sql = "INSERT INTO usuarios (nome, email, cpf, celular, tipo, senha, created_at) VALUES (?, ?, ?, ?, 'medico', ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCPFformatado());
            stmt.setLong(4, usuario.getCelularFormatado());
            stmt.setString(5, senha);
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = agora.format(formato);
            stmt.setString(6, dataHora);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean atualizarUsuario(Usuario usuario, String senha) {

        String sql = """
        UPDATE usuarios
           SET nome    = COALESCE(NULLIF(?, ''), nome),
               email   = COALESCE(NULLIF(?, ''), email),
               cpf     = COALESCE(NULLIF(?, ''), cpf),
               celular = COALESCE(NULLIF(?, 0), celular),
               senha   = COALESCE(NULLIF(?, ''), senha)
         WHERE id = ?
        """;
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCPFformatado());
            stmt.setLong(4, usuario.getCelularFormatado());
            stmt.setString(5, senha);
            stmt.setInt(6, usuario.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deletarUsuario(int id) {
        deletarUsuarioConsulta(id);
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean deletarUsuarioConsulta(int id) {
        String sql = "DELETE FROM consultas WHERE paciente_id = ?;";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
