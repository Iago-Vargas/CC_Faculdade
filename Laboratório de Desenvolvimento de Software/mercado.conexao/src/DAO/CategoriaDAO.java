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
    
    public List<Categoria> getCategorias() {
    String sql = "SELECT * FROM categorias";
    List<Categoria> listaCategorias = new ArrayList<>();

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setId_cat(rs.getInt("id"));
            categoria.setNomecat(rs.getString("nome"));
            listaCategorias.add(categoria);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao listar categorias: " + ex.getMessage());
    }
    return listaCategorias;
}

}
