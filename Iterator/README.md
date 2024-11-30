O iterator é um padrão comportamental que te permite percorrer elementos de uma coleção sem expor sua representação (lista, pilha, árvore, etc.).

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Iterator/structure.png)

1. A interface `Iterator` declara as operações necessárias para percorrer uma coleção: buscar o próximo elemento, pegar a posição atual, reiniciar a iteração, etc.
2. `ConcreteIterator` s implementam algoritmos específicos para percorrer a coleção. Um objeto iterator deve rastrear o progresso de travessia por si mesmo. Isso permite diversos iterators a percorrerem a mesma coleção independentemente uns dos outros.
3. A interface `Collection` declara um ou mais métodos para recuperar iterators compatíveis com a coleção. Perceba que o tipo de retorno dos métodos deve ser definido como a interface do iterator de modo que as coleções concretas possam retornar vários tipos de iterators.
4. `ConcreteCollection` s retornam novas instâncias de uma classe iterator específica cada vez que o cliente pede uma. Você pode estar pensando, onde está o resto do código da coleção? Não se preocupe, deve ficar na mesma classe. Acontece apenas que esses detalhes não são necessários para o padrão em si, portanto, estamos omitindo-os.
5. O `Client` funciona com ambos iterators e coleções através de suas interfaces. Desse modo o cliente não está acoplado com a classe concreta, te permitindo usar várias coleções e iterators com o mesmo código cliente.
Normalmente, os clientes não criam iterators por si próprios, mas ao invés disso, recebem eles das coleções. Ainda assim, em certos casos, o cliente pode criar um diretamente. Por exemplo, quando o cliente define seu próprio iterator especial.

# Problema

Coleções são um dos tipos de dados mais usados na programação. Apesar disso, uma coleção é apenas um contêiner para um grupo de objetos.

A maioria das coleções armazenam seus elementos em listas simples. Contudo, algumas delas são baseadas em pilhas, árvores, grafos e outras estruturas de dados complexas.

Mas independente de como uma coleção é estruturada, ela deve fornecer alguma forma de acessar seus elementos de forma que outro código possa usar esses elementos. Deve existir alguma forma de passar por cada elemento da coleção sem acessar o mesmo elemento várias vezes.

Isso pode parecer um trabalho simples se você tem uma coleção baseada em uma lista. Você só faz um loop em todos os elementos. Mas como você percorre sequencialmente elementos em uma estrutura de dados complexa, como em árvores? Por exemplo, um dia você pode estar satisfeito com percorrer uma árvore em depth-first (percorrer em profundidade). No outro, você pode precisar percorrer em breadth-first (percorrer em amplitude). E na semana seguinte, você pode precisar de algo diferente, como um acesso aleatório aos elementos da árvore.

Adicionar cada vez mais algoritmos de travessia para uma coleção gradualmente ofusca sua responsabilidade primária, que é o armazenamento eficiente de dados. Adicionalmente, alguns algoritmos podem ser adaptados para um caso específico, portanto, incluí-lo em uma classe de coleção genérica seria estranho.

Em contra partida, o código do cliente que deveria trabalhar com várias coleções pode não se importar com a maneira que eles armazenam seus elementos. Entretanto, como todas as coleções provém diferentes maneiras de acessar seus elementos, você não tem outra opção a não ser acoplar seu código com as classes de coleções específicas.

# Solução

A ideia principal do padrão iterator é extrair o comportamento de travessia de uma coleção em um objeto separado denominado *iterator*.

Além de implementar o algoritmo em si, um objeto iterator encapsula todos os detalhes da travessia, como a posição atual e quantos elementos estão faltando para o fim. Por conta disso, diversos iterators podem percorrer a mesma coleção ao mesmo tempo, independentes uns dos outros.

Comumente, iterators fornecem um método principal para buscar elementos da coleção. O cliente pode continuar rodando esse método até que ele não retorne nada, o que significa que o iterator percorreu por todos os elementos.

Todos os iterators devem implementar a mesma interface. Isso faz o código do cliente compatível com quaisquer tipos de coleções ou algoritmos de travessia desde que exista um iterator apropriado.  Se você precisar de uma maneira especial de percorrer uma coleção, você apenas cria uma nova classe iterator, sem precisar alterar a coleção ou o cliente.