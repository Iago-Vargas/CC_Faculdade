/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import escola.conexao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import beans.Professor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iagov
 */
public class ProfessorDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProfessorDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir (Professor professor){
        String sql = "INSERT INTO professores (nome, idade, disciplina) VALUES (?,?,?);";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString (1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getDisciplina());
            
            stmt.execute();
        }
        catch (SQLException ex){
            System.out.println ("Erro ao inserir pessoa: "+ex.getMessage());
        }
    }
     public Professor getProfessor(int id){ //Esse é para aparecer a pessoa dentro do programa
        String sql = "SELECT * FROM professores WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement (sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //O parametro é o tipo do ResultSet, esse tipo de ResultSet  é sensivel a alterações 
            //feitas no banco de dados, ou seja, as modificações feitas no banco refletem no resultSet
            //o outro paramentro é sobre os parametros de concorrencia - pode ser "read only" ou atualizavel
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); //Obtenho o retorno da consulta e armazena no ResultSet
            Professor p = new Professor();//Preparo do objeto que vou armazenar a consulta
            //Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição de consulta
            //Em alguns casos, a consulta terá mais de um resultado de retorno
            rs.first();
            p.setId_professor(id);
            p.setNome(rs.getString("nome"));
            p.setIdade(rs.getInt("idade"));
            p.setDisciplina(rs.getString("Disciplina"));
            return p;
        }catch (SQLException ex){
            System.out.println ("ERRO AO CONSULTAR PESSOA: "+ex.getMessage());
            return null;
        }
    }
     public void editar (Professor professor){
         try{
             String sql = "UPDATE professores set nome=?, idade=?, disciplina=? WHERE id=?";
             
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setString(1, professor.getNome());
             stmt.setInt(2, professor.getIdade());
             stmt.setString(3, professor.getDisciplina());
             stmt.setInt(4, professor.getId_professor());
             stmt.execute();
         }catch (SQLException ex){
             System.out.println ("Erro ao atualizar pessoa: "+ex.getMessage());
         }
     }
     public void excluir (int id){
         try{
             String sql = "delete from professores WHERE id=?";
             
             PreparedStatement stmt = conn.prepareStatement(sql);
             stmt.setInt(1, id);
             stmt.execute();
         }catch(SQLException ex){
             System.out.println ("Erro ao atualizar pessoa: "+ex.getMessage());
         }
     }

    public List<Professor> listarProfessores() {
     List<Professor> lista = new ArrayList<>();
     String sql = "SELECT * FROM professores";
     try {
         PreparedStatement stmt = this.conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         while (rs.next()) {
             Professor professor = new Professor();
             professor.setId_professor(rs.getInt("id"));
             professor.setNome(rs.getString("nome"));
             lista.add(professor);
         }
     } catch (SQLException ex) {
         System.out.println("Erro ao listar professores: " + ex.getMessage());
     }
     return lista;
 }

}
