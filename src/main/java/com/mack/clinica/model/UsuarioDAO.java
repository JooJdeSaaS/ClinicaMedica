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

public class UsuarioDAO {

    private String realPathBase;

    public UsuarioDAO(String realPathBase) {
        this.realPathBase = realPathBase;
    }

    //login
    public Usuario buscarUsuario(String email, String senha) {
        try (Connection conn = DatabaseConnection.getConnection(realPathBase)) {
            String sql = "SELECT id, nome, email, tipo, cpf, celular FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Se encontrou o usuário, cria um objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setCPF(rs.getString("cpf"));
                usuario.setCelularFormatado(rs.getLong("celular"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuário no banco de dados.", e);
        }
        return null;
    }

    //Meu Cadastro
    public boolean atualizarUsuario(Usuario u) {
        String sql = "UPDATE usuarios "
                + "SET nome = ?, celular = ?, cpf = ?, email = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCelular());
            ps.setString(3, u.getCPFformatado());
            ps.setString(4, u.getEmail());
            ps.setInt(5,    u.getId());

            int affected = ps.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    //CRUD paciente e CRUD medico
    public boolean criarUsuario(Usuario usuario, String senha, String tipo) {
        String sql = "INSERT INTO usuarios (nome, email, cpf, celular, tipo, senha, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCPFformatado());
            stmt.setLong(4, usuario.getCelularFormatado());
            stmt.setString(5, tipo);
            stmt.setString(6, senha);
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataHora = agora.format(formato);
            stmt.setString(7, dataHora);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //CRUD paciente e CRUD medico e consultar agenda
    public List<Usuario> listarUsuarios(String tipo) {
        List<Usuario> medicos = new ArrayList<>();
        String sql = "SELECT id, nome, email, cpf, celular, created_at FROM usuarios WHERE tipo = ?";

        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setCPF(rs.getString("cpf"));
                u.setCelularFormatado(rs.getLong("celular"));
                u.setCreated_at(rs.getString("created_at"));
                medicos.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar médicos: " + e.getMessage());
        }
        return medicos;
    }

    //CRUD paciente e CRUD medico
    public Usuario buscarPorCampo(String campo, String valor, String tipo) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE " + campo + " = ? AND tipo = ?";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, valor);
            stmt.setString(2, tipo);
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

    //CRUD paciente e CRUD medico
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

    //CRUD paciente e CRUD medico
    public boolean deletarUsuario(int id, String tipo) {
        deletarUsuarioConsulta(id, tipo);
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
    public boolean deletarUsuarioConsulta(int id, String tipo) {
        String coluna = null;

        if ("paciente".equals(tipo)) {
            coluna = "paciente_id";
        } else if ("medico".equals(tipo)) {
            coluna = "profissional_id";
        }
        String sql = "DELETE FROM consultas WHERE " + coluna + " = ?;";

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

    public List<Usuario> listarPacientes() {
        List<Usuario> pacientes = new ArrayList<>();
        String sql = "SELECT id, nome FROM usuarios WHERE tipo = 'paciente'";
        try (Connection conn = DatabaseConnection.getConnection(realPathBase);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                pacientes.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pacientes: " + e.getMessage());
        }
        return pacientes;
    }
}
