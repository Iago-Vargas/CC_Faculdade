/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import escola.conexao.Conexao;
import java.sql.Connection;
import beans.Aluno;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author iagov
 */
public class AlunoDAO {
    private Conexao conexao;
    private Connection conn;
    public AlunoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    public void inserir(Aluno aluno){
        String sql = "INSERT INTO alunos (nome, idade, curso) VALUES (?,?,?);";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement (sql);
            stmt.setString (1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());   
            stmt.setString (3, aluno.getCurso());
            
            stmt.execute();
        }
        catch (SQLException ex){
            System.out.println ("Erro ao inserir a pessoa: "+ex.getMessage());
        }
    }
    public Aluno getAluno(int id){ //Esse é para aparecer a pessoa dentro do programa
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement (sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //O parametro é o tipo do ResultSet, esse tipo de ResultSet  é sensivel a alterações 
            //feitas no banco de dados, ou seja, as modificações feitas no banco refletem no resultSet
            //o outro paramentro é sobre os parametros de concorrencia - pode ser "read only" ou atualizavel
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery(); //Obtenho o retorno da consulta e armazena no ResultSet
            Aluno p = new Aluno();//Preparo do objeto que vou armazenar a consulta
            //Primeiramente, vamos posicionar o retorno da consulta (ResultSet) na primeira posição de consulta
            //Em alguns casos, a consulta terá mais de um resultado de retorno
            rs.first();
            p.setId_aluno(id);
            p.setNome(rs.getString("nome"));
            p.setIdade(rs.getInt("idade"));
            p.setCurso(rs.getString("Curso"));
            return p;
        }catch (SQLException ex){
            System.out.println ("ERRO AO CONSULTAR PESSOA: "+ex.getMessage());
            return null;
        }
    }
    public void editar (Aluno aluno){ //Esse vamos editar os dados    
        try{
            String sql = "UPDATE alunos set nome=?, idade=?, curso=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString (1, aluno.getNome());
            stmt.setInt (2, aluno.getIdade());
            stmt.setString (3, aluno.getCurso());
            stmt.setInt (4, aluno.getId_aluno());
            stmt.execute();
        }catch (SQLException ex){
            System.out.println ("Erro ao atualizar pessoa:"+ex.getMessage());
        }
    }
    public void excluir (int id){ //Excluir a pessoa 
        try{
            String sql = "delete from alunos WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt (1, id);
            stmt.execute();
        }catch (SQLException ex){
            System.out.println ("ERRO AO ENCONTRAR PESSOA: "+ex.getMessage());
        }
    }
}
