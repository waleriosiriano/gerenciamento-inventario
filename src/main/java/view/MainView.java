package view;
import javax.swing.*;
import dao.ProdutoDAO;
import model.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTextField nomeField, quantidadeField, precoField;
    private JButton adicionarButton;
    private ProdutoDAO produtoDAO;

    public MainView() {
        setTitle("Gerenciamento de Inventário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        produtoDAO = new ProdutoDAO();

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(20, 20, 100, 25);
        add(nomeLabel);
        
        nomeField = new JTextField();
        nomeField.setBounds(130, 20, 200, 25);
        add(nomeField);

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidadeLabel.setBounds(20, 60, 100, 25);
        add(quantidadeLabel);

        quantidadeField = new JTextField();
        quantidadeField.setBounds(130, 60, 200, 25);
        add(quantidadeField);

        JLabel precoLabel = new JLabel("Preço:");
        precoLabel.setBounds(20, 100, 100, 25);
        add(precoLabel);

        precoField = new JTextField();
        precoField.setBounds(130, 100, 200, 25);
        add(precoField);

        adicionarButton = new JButton("Adicionar Produto");
        adicionarButton.setBounds(100, 150, 200, 30);
        add(adicionarButton);

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                int quantidade = Integer.parseInt(quantidadeField.getText());
                double preco = Double.parseDouble(precoField.getText());

                Produto produto = new Produto(0, nome, quantidade, preco);
                produtoDAO.adicionarProduto(produto);

                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                nomeField.setText("");
                quantidadeField.setText("");
                precoField.setText("");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
