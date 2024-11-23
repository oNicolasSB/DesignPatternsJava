# Definição

O **Visitor** é um padrão comportamental que permite adicionar novos comportamentos a um conjunto de objetos existentes sem modificar suas classes. Ele separa os algoritmos (comportamentos) dos objetos nos quais eles operam, promovendo a extensibilidade do sistema.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Visitor/structure.png)

1. **Interface `Visitor`:**
    - Declara um conjunto de métodos específicos para processar diferentes tipos de elementos.
    - Exemplo: `VisitCity(City city)` ou `VisitFactory(Factory factory)`.
2. **Classe `ConcreteVisitor`:**
    - Implementa os métodos definidos na interface `Visitor`, fornecendo a lógica para cada tipo de elemento.
    - Por exemplo, um `XmlExporterVisitor` pode implementar métodos que geram XML para diferentes tipos de nós.
3. **Interface `Element`:**
    - Define o método `Accept(Visitor visitor)`, que aceita um objeto Visitor como argumento.
4. **Classe `ConcreteElement`:**
    - Representa os nós concretos no grafo (como `City` ou `Factory`).
    - Cada classe implementa o método `Accept`, chamando o método apropriado no Visitor com base em sua própria classe.
5. **Cliente:**
    - Percorre a estrutura (como um grafo ou lista) e chama o método `Accept` em cada elemento, passando o Visitor como argumento.

# Problema

Imagine que você está desenvolvendo um sistema que manipula informações geográficas organizadas em um grafo. Cada nó pode representar algo complexo como uma cidade ou algo mais específico, como uma fábrica ou ponto turístico. Esses nós são conectados por arestas, representando estradas no mundo real.

Os nós são implementados por diferentes classes, e cada nó específico é uma instância dessas classes.

Agora, suponha que você precisa exportar o grafo para XML. Inicialmente, você pode pensar em adicionar um método de exportação a cada classe de nó. Com isso, bastaria percorrer o grafo e chamar o método correspondente em cada nó, aproveitando o polimorfismo.

Porém, surgem dois grandes problemas:

1. **Restrições no sistema:** O arquiteto do projeto não permite modificar as classes de nó, pois elas já estão em produção, e qualquer alteração pode introduzir bugs.
2. **Baixa coesão:** Exportar para XML não é uma responsabilidade dos nós, que deveriam se preocupar apenas com dados geográficos. Adicionar tal funcionalidade tornaria o código dessas classes menos coeso.

Além disso, o sistema pode precisar suportar outros formatos de exportação no futuro (JSON, CSV, etc.), o que obrigaria novas alterações frequentes nas mesmas classes.

# Solução

O **Visitor** resolve esse problema ao transferir os novos comportamentos para uma classe separada. Em vez de implementar o comportamento dentro das classes de nó, você cria um *Visitor* que executa essas operações. O objeto Visitor "visita" cada nó do grafo e realiza a ação correspondente, acessando os dados do nó sem precisar modificar sua classe.

Para lidar com diferentes tipos de nós (por exemplo, cidades, fábricas, etc.), o Visitor usa um conjunto de métodos especializados, cada um responsável por um tipo específico de nó. A técnica de **Double Dispatch** permite que o nó chame o método apropriado no Visitor, eliminando a necessidade de grandes estruturas condicionais ou verificações de tipo.

# Exemplo de funcionamento

Suponha que você tenha os seguintes nós:

- `City`
- `Factory`

E deseja exportá-los para XML usando o Visitor:

1. **`City` e `Factory` implementam `Accept`:**
    - O método `Accept` redireciona a chamada para `VisitCity` ou `VisitFactory` no Visitor.
2. **`XmlExporterVisitor`:**
    - Implementa `VisitCity` para exportar informações específicas da cidade.
    - Implementa `VisitFactory` para exportar informações específicas da fábrica.
3. **Cliente:**
    - Percorre todos os nós no grafo e chama `Accept(visitor)` em cada um. O nó seleciona o método apropriado no Visitor.

# Benefícios do padrão Visitor

- **Extensibilidade:** Adicionar novos comportamentos (visitors) é fácil e não requer alterações nas classes existentes.
- **Separa responsabilidades:** As classes de nó permanecem focadas em seu propósito principal, enquanto os Visitors tratam de comportamentos adicionais.
- **Facilidade de manutenção:** O código de novos comportamentos fica centralizado nas classes Visitor.

# Quando evitar o padrão Visitor?

- Se o sistema é altamente dinâmico e as classes dos elementos mudam com frequência, o Visitor pode se tornar difícil de gerenciar.
- Caso o número de classes concretas cresça muito, adicionar métodos no Visitor pode se tornar trabalhoso.