/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import escola.conexao.Conexao;
import beans.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private Conexao conexao;
    private Connection conn;

    public DisciplinaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    // Método para inserir uma nova disciplina
    public void inserir(Disciplina disciplina) {
        String sql = "INSERT INTO Disciplina (nome, carga_horaria, professor_id) VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getProfessorId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir disciplina: " + ex.getMessage());
        }
    }

    // Método para listar disciplinas
    public List<Disciplina> listarDisciplinas() {
        List<Disciplina> lista = new ArrayList<>();
        String sql = "SELECT * FROM Disciplina";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("carga_horaria"));
                disciplina.setProfessorId(rs.getInt("professor_id"));
                lista.add(disciplina);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar disciplinas: " + ex.getMessage());
        }
        return lista;
    }
}

