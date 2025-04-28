package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidade, preco) VALUES (?, ?, ?)";
        Connection conexao = null;
        try {
            conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar(conexao);
        }
                
    }
 // Atualizar produto
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, quantidade = ?, preco = ? WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar(conexao);
        }
    }

    // Excluir produto
    public void excluirProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        Connection conexao = null;
        try {
            conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar(conexao);
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        Connection conexao = null;
        try {
            conexao = Conexao.conectar();
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar(conexao);
        }
        return produtos;
    }
}
