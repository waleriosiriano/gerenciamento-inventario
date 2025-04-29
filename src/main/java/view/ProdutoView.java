package view;

import controller.ProdutoController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Produto;

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
        adicionarButton.setOnAction(e -> {
            try {
                String nome = nomeField.getText();
                int quantidade = Integer.parseInt(quantidadeField.getText());
                // Modificar o preço para aceitar vírgula
                String precoTexto = precoField.getText().replace(',', '.');  // Troca a vírgula por ponto
                double preco = Double.parseDouble(precoTexto);  // Converte para double
                controller.adicionarProduto(nome, quantidade, preco);
                limparCampos(nomeField, quantidadeField, precoField);
                atualizarTabela();
            } catch (Exception ex) {
                ex.printStackTrace();
                mostrarErro("Erro ao adicionar produto. Verifique os campos.");
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

        VBox cadastroBox = new VBox(10, nomeField, quantidadeField, precoField, adicionarButton);
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
