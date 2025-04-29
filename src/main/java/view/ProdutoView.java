package view;

import controller.ProdutoController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Produto;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProdutoView extends Application {

    private ProdutoController controller = new ProdutoController();
    private TableView<Produto> tabela = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gerenciamento de Inventário");

        // Campos de cadastro
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome do Produto");

        TextField quantidadeField = new TextField();
        quantidadeField.setPromptText("Quantidade");

        TextField precoField = new TextField();
        precoField.setPromptText("Preço (use vírgula)");

        Button adicionarButton = new Button("Adicionar Produto");
        Button atualizarButton = new Button("Atualizar Produto");

        Button excluirButton = new Button("Excluir Produto");

        Button gerarRelatorioButton = new Button("GErar Relatório");
        
        atualizarButton.setOnAction(e -> {
            Produto selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                try {
                    String nome = nomeField.getText();
                    int quantidade = Integer.parseInt(quantidadeField.getText());
                    double preco = Double.parseDouble(precoField.getText().replace(',', '.'));

                    controller.atualizarProduto(selecionado.getId(), nome, quantidade, preco);
                    limparCampos(nomeField, quantidadeField, precoField);
                    atualizarTabela();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    mostrarErro("Erro ao atualizar produto. Verifique os campos.");
                }
            } else {
                mostrarErro("Selecione um produto na tabela para atualizar.");
            }
        });
        
        excluirButton.setOnAction(e -> {
            Produto selecionado = tabela.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                controller.excluirProduto(selecionado.getId());
                limparCampos(nomeField, quantidadeField, precoField);
                atualizarTabela();
            } else {
                mostrarErro("Selecione um produto para excluir.");
            }
        });
        
        gerarRelatorioButton.setOnAction(e -> {
            List<Produto> produtos = controller.listarProdutos();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar Relatório");
            fileChooser.setInitialFileName("relatorio_estoque.txt");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try (PrintWriter writer = new PrintWriter(file)) {
                    writer.println("RELATÓRIO DE ESTOQUE");
                    writer.println("=====================");
                    for (Produto p : produtos) {
                        writer.printf("ID: %d | Nome: %s | Qtd: %d | Preço: R$ %.2f\n",
                                p.getId(), p.getNome(), p.getQuantidade(), p.getPreco());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    mostrarErro("Erro ao salvar o relatório.");
                }
            }
        });



        // Tabela de produtos
        TableColumn<Produto, Integer> idColuna = new TableColumn<>("ID");
        idColuna.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));

        TableColumn<Produto, String> nomeColuna = new TableColumn<>("Nome");
        nomeColuna.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nome"));

        TableColumn<Produto, Integer> quantidadeColuna = new TableColumn<>("Quantidade");
        quantidadeColuna.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("quantidade"));

        TableColumn<Produto, Double> precoColuna = new TableColumn<>("Preço");
        precoColuna.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("preco"));

        tabela.getColumns().addAll(idColuna, nomeColuna, quantidadeColuna, precoColuna);
        atualizarTabela();
        
        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomeField.setText(newSelection.getNome());
                quantidadeField.setText(String.valueOf(newSelection.getQuantidade()));
                precoField.setText(String.valueOf(newSelection.getPreco()).replace('.', ','));
            }
        });


        VBox cadastroBox = new VBox(10, nomeField, quantidadeField, precoField, adicionarButton, atualizarButton, excluirButton,  gerarRelatorioButton);
        cadastroBox.setPadding(new Insets(10));

        VBox tabelaBox = new VBox(10, tabela);
        tabelaBox.setPadding(new Insets(10));

        HBox root = new HBox(20, cadastroBox, tabelaBox);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void limparCampos(TextField nome, TextField quantidade, TextField preco) {
        nome.clear();
        quantidade.clear();
        preco.clear();
    }

    private void atualizarTabela() {
        List<Produto> produtos = controller.listarProdutos();
        tabela.getItems().setAll(produtos);
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
