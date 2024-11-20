# **Definição**

O **Command** é um padrão de projeto comportamental que encapsula um pedido como um objeto independente. Este objeto contém todas as informações necessárias para executar o pedido. Essa abordagem permite que você:

- Parametrize métodos com diferentes pedidos.
- Atrasar ou enfileirar a execução de pedidos.
- Suporte operações que podem ser desfeitas.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Command/structure.png)

# **Problema**

Imagine que você está desenvolvendo um editor de texto e precisa criar uma barra de ferramentas com vários botões para executar diferentes funções, como copiar, colar e salvar. Para isso, você criou uma classe genérica chamada `Botao`, que pode ser usada tanto na barra de ferramentas quanto em outros locais, como caixas de diálogo.

Embora todos os botões sejam visualmente semelhantes, suas funções são diferentes. A pergunta que surge é: **onde implementar as ações específicas de cada botão?**

Uma solução simples seria criar subclasses específicas de `Botao` para cada funcionalidade. Essas subclasses conteriam o código a ser executado ao clicar no botão. No entanto, essa abordagem tem problemas:

1. **Alta complexidade e risco de erros**: O número de subclasses aumenta rapidamente, dificultando a manutenção.
2. **Dependência excessiva**: A lógica do negócio fica acoplada ao código da interface gráfica, tornando o sistema menos flexível e mais propenso a falhas.
3. **Reutilização limitada**: Algumas ações, como copiar/colar texto, precisam ser acessíveis de diferentes formas (botão, atalho de teclado, menus). Com essa abordagem, seria difícil reutilizar o código dessas ações.

# **Solução**

O padrão Command resolve esse problema separando responsabilidades entre a interface gráfica (GUI) e a lógica do negócio, seguindo o **princípio da separação de interesses**.

### Como funciona:

1. **Encapsular o pedido**:
    
    Em vez de o botão ou atalho chamar diretamente um método da lógica do negócio, ele cria um objeto **Command**. Esse objeto encapsula:
    
    - O destinatário do pedido (um objeto da lógica do negócio).
    - A operação a ser executada (um método).
    - Quaisquer dados necessários para a execução.
2. **Executar o comando**:
    
    O botão, ao ser clicado, apenas aciona o método `Executar` do objeto Command. O Command, por sua vez, sabe qual objeto de negócio deve receber o pedido e como processá-lo.
    
3. **Interface genérica**:
    
    Todos os comandos implementam a mesma interface (por exemplo, `ICommand`), que contém o método `Executar`. Isso permite que a interface gráfica seja desacoplada das classes concretas de comando.
    
4. **Flexibilidade**:
    
    Agora, você pode trocar dinamicamente os comandos associados a um botão, alterando seu comportamento sem modificar a estrutura base da interface gráfica.
    

# **Exemplo aplicado: Editor de texto**

No editor de texto, aplicamos o padrão Command da seguinte forma:

- A classe base `Botao` possui um campo para armazenar um objeto Command.
- Cada botão é configurado com um Command específico que encapsula uma operação, como copiar ou colar texto.
- Quando o botão é clicado, ele apenas chama o método `Executar` do Command, que cuida de todos os detalhes.

Com isso:

- Não é mais necessário criar subclasses específicas para cada botão.
- A interface gráfica e a lógica do negócio ficam completamente desacopladas.
- É possível reutilizar comandos em diferentes partes da aplicação (botões, atalhos, menus).

---

# **Benefícios do padrão Command**

- **Desacoplamento**: A interface gráfica não precisa conhecer os detalhes da lógica do negócio.
- **Reutilização**: Comandos podem ser reutilizados em diferentes contextos.
- **Flexibilidade**: Permite enfileirar, atrasar ou até mesmo desfazer pedidos.
- **Manutenção simplificada**: Modificações em uma camada não afetam diretamente a outra.

O padrão Command é uma solução poderosa para reduzir o acoplamento e melhorar a organização de sistemas que possuem interações complexas entre interface e lógica do negócio.