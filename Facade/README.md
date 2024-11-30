# Definição

O Facade é um padrão estrutural que fornece uma interface simplificada para uma biblioteca, um framework ou qualquer outro conjunto complexo de classes.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Facade/structure.png)

1. O `Facade` fornece acesso facilitado para uma parte em particular de uma funcionalidade do subsistema. Ele sabe para onde direcionar a requisição do cliente e como operar todas as partes móveis.
2. Uma classe `AdditionalFacade` pode ser criada para evitar poluir um único facade com funcionalidades não relacionadas que podem torná-lo outra estrutura complexa. Facades adicionais podem ser utilizados por ambos os clientes e outros facades.
3. O Subsistema complexo consiste em dúzias de objetos diversos. Para fazê-los todos fazerem alguma coisa com significado, você tem que mergulhar fundo nos detalhes da implementação do subsistema, tal como iniciar objetos na ordem correta e alimentá-los com dados no formato correto.
Classes do subsistema não sabem da existência do facade. Elas operam dentro do sistema e trabalham diretamente uns com os outros.
4. O `Client` usa o facade ao invés de chamar os objetos do subsistema diretamente.

# Problema

Imagine que você precisa fazer seu código funcionar com um conjunto amplo de objetos que pertencem a uma biblioteca ou framework sofisticado. Normalmente, você precisaria iniciar todos os objetos, rastrear as dependências, executar métodos na ordem correta e tudo mais.

Como resultado, a lógica de negócio da suas classes ficariam altamente acopladas com a implementação detalhada de classes de terceiros, tornando difícil de compreender e manter.

# Solução

Um facade é uma classe que fornece uma interface simples para um subsistema complexo que contém várias partes móveis. Um facade pode fornecer funcionalidades limitadas comparando a trabalhar diretamente com o subsistema. Contudo, ele inclui apenas as funcionalidades que o cliente realmente precisa.

Possuir um facade é útil quando você precisa integrar seus aplicativo com uma biblioteca sofisticada que possui dúzias de funcionalidades, mas você só precisa de uma pequena parte delas.

Por exemplo, um aplicativo que faz upload de vídeos curtos e divertidos com gatos para redes sociais poderia provavelmente utilizar uma biblioteca de conversão de vídeo profissional. Contudo, tudo que ele realmente precisa é uma classe com o único método `encode(fileName, format)` . Após criar tal classe e conectar com a biblioteca de conversão de vídeo, você vai ter seu primeiro facade.