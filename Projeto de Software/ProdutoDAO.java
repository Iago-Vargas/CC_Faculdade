package DAO;
import beans.Produto;
import java.io.*;
import java.util.*;

public class ProdutoDAO {
    private static final String FILE_NAME = "produtos.csv";

    public void inserir(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(produto.getCodigo() + "," + produto.getNome() + "," + produto.getDescricao() + "," +
                         produto.getPreco() + "," + produto.getQuantidade());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                Produto produto = new Produto();
                produto.setCodigo(dados[0]);
                produto.setNome(dados[1]);
                produto.setDescricao(dados[2]);
                produto.setPreco(Double.parseDouble(dados[3]));
                produto.setQuantidade(Integer.parseInt(dados[4]));
                produtos.add(produto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto buscarProduto(String codigo) {
        for (Produto p : listarProdutos()) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void deletarProduto(String codigo) {
        List<Produto> produtos = listarProdutos();
        produtos.removeIf(p -> p.getCodigo().equals(codigo));
        salvarTodos(produtos);
    }

    public void atualizarProduto(Produto produtoAtualizado) {
        List<Produto> produtos = listarProdutos();
        for (Produto p : produtos) {
            if (p.getCodigo().equals(produtoAtualizado.getCodigo())) {
                p.setNome(produtoAtualizado.getNome());
                p.setDescricao(produtoAtualizado.getDescricao());
                p.setPreco(produtoAtualizado.getPreco());
                p.setQuantidade(produtoAtualizado.getQuantidade());
                break;
            }
        }
        salvarTodos(produtos);
    }

    private void salvarTodos(List<Produto> produtos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Produto p : produtos) {
                writer.write(p.getCodigo() + "," + p.getNome() + "," + p.getDescricao() + "," +
                             p.getPreco() + "," + p.getQuantidade());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
