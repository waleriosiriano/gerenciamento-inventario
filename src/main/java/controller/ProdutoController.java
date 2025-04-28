package controller;

import dao.ProdutoDAO;
import model.Produto;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }

    public void adicionarProduto(String nome, int quantidade, double preco) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produtoDAO.adicionarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listarProdutos();
    }

    public void atualizarProduto(int id, String nome, int quantidade, double preco) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);
        produtoDAO.atualizarProduto(produto);
    }

    public void excluirProduto(int id) {
        produtoDAO.excluirProduto(id);
    }
}
