# Definição

O Chain of Responsibility é um padrão de projeto comportamental que permite você passar requisições através de uma cadeia de handlers. Ao receber uma requisição, cada handler decide por processar a requisição ou passá-la para o próximo handler.

# Problema

Image que você está trabalhando em um sistema de encomendas online. Você quer restringir o acesso ao sistema para que somente usuários autenticados possam criar encomendas. Além disso, usuários que possuem permissões administrativas devem ter acesso total aos pedidos.

Após um pouco de planejamento, você percebe que essas verificações devem ser realizadas sequencialmente. A aplicação pode tentar autenticar um usuário ao sistema sempre que receber uma requisição que contém as credenciais do usuário. Contudo, se essas credenciais não estiverem corretas e a autenticação falhar, não tem motivo para prosseguir com outras validações.

Durante os próximos meses, você implementou muito mais validações sequenciais.

- Um de seus colegas sugere que é inseguro passar dados brutos direto para o sistema de encomendas. Então você adicionou uma validação extra para limpar os dados em uma requisição.
- Mais tarde, alguém notou que o sistema é vulnerável a quebra de senha por força bruta. Para evitar isso, você adicionou uma verificação que filtra múltiplas requisições falhas vindas do mesmo IP.
- Outra pessoa sugeriu que você poderia tornar o sistema mais rápido retornando resultados cacheados em requisições repetidas contendo os mesmos dados. Por isso, você adicionou outra validação que permite a requisição passar pelo sistema somente se não existir uma resposta em cache adequada.

O código das validações, o qual já era uma bagunça, ficou cada vez mais poluído a cada vez que uma nova função era adicionada. Alterar uma validação as vezes afetava outras. E pior de tudo, quando você tentava reutilizar as validações para proteger outros componentes do sistema, você tinha que duplicar parte do código visto que aqueles componentes precisavam de algumas validações.

O sistema ficou muito difícil de entender e caro de manter. Você lutou com o código por um tempo, até que um dia você decidiu refatorar a coisa toda.

# Solução

Assim como muitos outros padrões comportamentais, o **Chain of Responsibility** consiste em transformar certos comportamentos em objetos independentes chamados de handlers. Em nosso caso, cada validação deveria ser extraída em sua própria classe com um único método que executa a validação. Essa requisição, juntamente com seus dados, é passada para o método como um argumento.

O padrão sugere que você relacione esses handlers em uma cadeia como uma corrente. Cada handler conectado tem um campo para armazenar uma referência para o próximo handler na cadeia. Além de processar a requisição, handlers passam a requisição através da corrente. As requisições viajam pela corrente até que todos os handlers tenham a chance de processá-la.

Aqui está a melhor parte: um handler pode optar por não passar a requisição adiante pela cadeia e efetivamente para qualquer processo conseguinte.

Em nosso exemplo com o sistema de encomendas, um handler exerce a função de processar e depois decidir quando passar ou não a requisição adiante. Assumindo que a requisição contém os dados corretos, todos os handlers podem executar sua função primária, sejam validações de autenticação ou caching.

![line-chain.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/ChainOfResponsibility/line-chain.png)

Entretanto, existe uma aproximação ligeiramente diferente (e um pouco mais canônica) na qual, ao receber uma requisição, um handler decide quando pode processá-la. Se puder, não passa a requisição adiante. Portanto, ou um handler processa a requisição ou nenhum. Essa abordagem é muito comum quando trabalhando com eventos em pilhas de elementos dentro de uma interface gráfica de usuário.

For instance, quando um usuário clica em um botão, o evento se propaga pela cadeia de elementos GUI que começa com o botão, passa por todo seu contêiner (como formulários ou painéis), e termina com a janela final da aplicação. O evento é processado pelo primeiro elemento na cadeia que é capaz de lidar com ele. Esse exemplo também é notável pois mostra que uma cadeia também pode ser extraída de uma árvore de objetos.

![tree-chain.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/ChainOfResponsibility/tree-chain.png)

É crucial que todos as classes handler implementem a mesma interface. Cada handler concreto deve lidar apenas com o handler conseguinte e possuir o método `executar()` . Desse modo, você pode compor cadeias em tempo de execução, usando diversos handlers sem acoplar seu código com suas classes concretas.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/ChainOfResponsibility/structure.png)

1. O `Handler` declara a interface, comum para todos os handlers concretos. Normalmente contém apenas um único método para lidar com a requisição, mas às vezes pode também conter outro método para definir o próximo handler na cadeia.
2. O `BaseHandler` é uma classe opcional onde você pode colocar o código boilerplate (comum) que é compartilhado entre todos os handlers.
Normalmente, essa classe define um campo para armazenar a referência para o próximo handler. O cliente pode construir uma cadeia passando um handler para o construtor ou setter do handler anterior. Essa classe também pode implementar o comportamento padrão para lidar com as requisições: pode passar a execução para o próximo handler após verificar sua existência.
3. `ConcreteHandler` ’s contém o código em si para processar as requisições. Ao receber uma requisição, cada handler deve decidir quando processá-la e, adicionalmente, quando passá-la para o próximo.
Handlers normalmente são auto contidos e imutáveis, aceitando todos os dados necessários unicamente pelo construtor.
4. O `Client` pode compor as cadeias uma única vez ou compô-las dinamicamente, dependente da lógica da aplicação. Perceba que uma requisição pode ser passada para qualquer handler da cadeia — não necessariamente precisa ser o primeiro.