Gerenciamento de Inventário.

🗃️ Sistema de Gerenciamento de Inventário:

Este é um sistema simples de gerenciamento de inventário desenvolvido em Java, utilizando JavaFX para a interface gráfica e MySQL para persistência dos dados.

💡 Funcionalidades:

- ✅ Cadastro de produtos com nome, quantidade e preço
- ✅ Atualização e exclusão de produtos diretamente pela interface
- ✅ Geração de relatório de estoque em arquivo .txt
- ✅ Campo de busca por nome de produto

🛠️ Tecnologias Utilizadas
- Java 17+
- JavaFX
- MySQL
- JDBC

📂 Estrutura do Projeto:

src/ 
├── controller/ │ 
└── ProdutoController.java 
├── dao/ │ 
└── ProdutoDAO.java 
├── model/ │ 
└── Produto.java 
├── util/ │ 
└── Conexao.java 
└── view/ 
└── ProdutoView.java

Exemplo de Uso👨‍💻 :

Cadastre um novo produto preenchendo os campos à esquerda e clicando em "Adicionar Produto"

Clique sobre um produto na tabela para carregá-lo nos campos e atualizá-lo ou excluí-lo

Use o campo "Buscar por nome" para filtrar produtos em tempo real

Gere um relatório de estoque clicando em "Gerar Relatório"

____________________________________________________________________________________________

![Image](https://github.com/user-attachments/assets/c61f3797-d694-4959-8845-23cefbf3546f)

Relatorio.txt

![Image](https://github.com/user-attachments/assets/e4754621-a4d5-4526-95ac-f642fdef21c9)



