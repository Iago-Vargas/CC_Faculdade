/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import mercado.conexao.Conexao;
import java.sql.Connection;
import beans.Categoria;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;


/**
 *
 * @author laboratorio
 */
public class CategoriaDAO {
    private Conexao conexao;
    private Connection conn;

    public CategoriaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir (Categoria categoria){
        String sql = "INSERT INTO categorias (nome) VALUES (?);";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNomecat());
            stmt.execute();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "ERRO AO INSERIR CATEGORIA");
        }
    }
    
    public List<Categoria> getCategoria(int id){
        String sql = "SELECT * FROM categorias WHERE id=?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Categoria c = new Categoria();
        
        if (rs.first()) {
            c.setId_cat(rs.getInt("id"));
            c.setNomecat(rs.getString("nome"));
            CategoriaDAO cDAO = new CategoriaDAO();
            
            Categoria c = cDAO.getCategoria(rs.getInt("id"));
            c.setId_cat(id); // Associa a pessoa ao veículo
        } else {
            return null; // Caso não haja resultados, retorna null
        }
        return c;
        }catch (SQLException ex){
            System.out.println ("Erro ao consultar pessoa: "+ex.getMessage());
            return null;
        }
    }
}
