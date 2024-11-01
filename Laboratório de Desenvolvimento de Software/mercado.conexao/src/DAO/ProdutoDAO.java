/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import beans.Produto;
import mercado.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author iagov
 */
public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProdutoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir (Produto produto){
        String sql = "INSERT INTO Produtos (nome, preco, quantidade, categoria_id) VALUES (?, ?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getIdCat());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
    }
  }
    public Produto buscarProduto(int id) {
    String sql = "SELECT * FROM produtos WHERE id = ?";
    Produto produto = null;

    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            produto = new Produto();
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setIdCat(rs.getInt("categoria_id"));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar produto: " + ex.getMessage());
    }
    return produto;
}
    public void atualizarProduto(Produto produto) {
    String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ?, categoria_id = ? WHERE id = ?";

    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setInt(3, produto.getQuantidade());
        stmt.setInt(4, produto.getIdCat());
        stmt.setInt(5, produto.getId()); // adiciona o ID do produto para identificar qual atualizar
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + ex.getMessage());
    }
}
 
    public void deletarProduto(int id) {
    String sql = "DELETE FROM produtos WHERE id = ?";

    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao deletar produto: " + ex.getMessage());
    }
}

    public List<Produto> listarProdutosComCategoria() {
         String sql = "SELECT p.id, p.nome, p.preco, p.quantidade, c.nome AS categoria " +
                 "FROM produtos p " +
                 "JOIN categorias c ON p.categoria_id = c.id";
    List<Produto> produtos = new ArrayList<>();

    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setNomeCategoria(rs.getString("categoria")); // Adicione isso no Produto.java
            
            produtos.add(produto);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
    }

    return produtos;
    }
    
    public List<Produto> buscarProdutoPorNome(String nome) {
    String sql = "SELECT p.id, p.nome, p.preco, p.quantidade, c.nome AS categoria " +
                 "FROM produtos p " +
                 "JOIN categorias c ON p.categoria_id = c.id " +
                 "WHERE p.nome LIKE ?";
    List<Produto> produtos = new ArrayList<>();

    try {
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, "%" + nome + "%"); // Usamos % para busca parcial
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setNomeCategoria(rs.getString("categoria")); // Nome da categoria obtido pelo JOIN

            produtos.add(produto);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar produto: " + ex.getMessage());
    }

    return produtos;
}
   public List<Produto> filtrarProdutosPorCategoria(int idCategoria) {
    List<Produto> produtos = new ArrayList<>();
    String sql;

    if (idCategoria == 0) { // 0 representa "Todas" as categorias
        sql = "SELECT produtos.*, categorias.nome AS categoria_nome FROM produtos "
            + "JOIN categorias ON produtos.categoria_id = categorias.id";
    } else {
        sql = "SELECT produtos.*, categorias.nome AS categoria_nome FROM produtos "
            + "JOIN categorias ON produtos.categoria_id = categorias.id WHERE produtos.categoria_id = ?";
    }

    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        // Se uma categoria específica foi selecionada, adicione o parâmetro
        if (idCategoria != 0) {
            stmt.setInt(1, idCategoria);
        }
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setIdCat(rs.getInt("categoria_id"));
            produto.setNomeCategoria(rs.getString("categoria_nome")); // para exibir o nome da categoria

            produtos.add(produto);
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao filtrar produtos por categoria: " + ex.getMessage());
    }

    return produtos;
}

}
