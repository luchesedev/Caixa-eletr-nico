# 🏧 Sistema de Caixa Eletrônico (Java Swing)

Este projeto é uma simulação funcional de um terminal bancário desenvolvida em **Java** para consolidar conceitos de Algoritmos, Estrutura de Dados e Interface Gráfica. O foco principal é a lógica de distribuição de cédulas e a gestão de inventário em tempo real.

## 🚀 Funcionalidades

### 👤 Módulo do Cliente
*   **Saque Inteligente**: Realiza a retirada de valores otimizando a entrega das notas disponíveis.
*   **Gestão de Cédulas**: Suporte nativo para notas de R$ 100, 50, 20, 10, 5 e 2.
*   **Validação de Limites**: Bloqueio de saques superiores a R$ 3.000,00 ou que excedam 30 cédulas por operação para garantir a segurança do hardware simulado.
*   **Extrato Consolidado**: Ao encerrar o sistema, exibe um resumo detalhado com o valor inicial, valor final e o total de cada nota utilizada durante a sessão.

### ⚙️ Módulo Administrativo
*   **Relatório de Cédulas**: Visualização em tempo real da quantidade de cada nota no estoque.
*   **Valor Total Disponível**: Cálculo dinâmico do montante total presente no caixa.
*   **Reposição de Cédulas**: Interface para entrada manual de novas notas no sistema.
*   **Cota Mínima**: Define um limite de segurança; se o saldo total for inferior à cota, o sistema suspende o atendimento e solicita a intervenção do operador.

## 🛠️ Tecnologias Utilizadas

*   **Linguagem**: Java (JDK 17+)
*   **Interface Gráfica**: Java Swing / AWT
*   **Arquitetura**: Implementação baseada em Interfaces (`ICaixaEletronico`) para garantir o desacoplamento da lógica de negócio.

## 🧠 Destaques da Implementação Lógica

### Solução do Problema de Restos (Algoritmo Ambicioso)
Diferente de algoritmos de saque básicos, este projeto trata casos específicos que poderiam causar impasses:
*   **Lógica de Ajuste (Backtracking Simulado)**: O sistema utiliza um `while` e verificações de resto (como casos onde sobram R$ 8,00 ou R$ 6,00) para garantir que o uso de notas de R$ 5,00 não impossibilite o fechamento do valor com notas de R$ 2,00.
*   **Atomicidade**: O inventário de notas só é atualizado após a confirmação de que o saque é possível, evitando erros de conciliação de saldo.

## 📁 Estrutura do Código

*   `CaixaEletronico.java`: Inteligência de negócio e controle de estado do caixa.
*   `ICaixaEletronico.java`: Contrato de métodos para garantir a padronização do sistema.
*   `GUI.java`: Camada visual com estilização personalizada de botões e tratamento de eventos de mouse.

## 🎨 Interface e UX
*   **Feedback Visual**: Botões com efeitos de clique personalizados (troca de cores `azulEscuro` para `azulClaroClique`).
*   **Robustez**: Tratamento de `NumberFormatException` para garantir que entradas inválidas não travem a aplicação.

---
⭐ *Desenvolvido por **Pedro Luchese**,**Arthur Pereira**,**Felipe Diniz**,**Pedro Augusto**,**Lucas Oliveira** como projeto acadêmico de Análise e Desenvolvimento de Sistemas (ADS) - FATEC Guarulhos.*
