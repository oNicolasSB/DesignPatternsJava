# Definição

O **Flyweight** é um padrão de projeto estrutural que ajuda a reduzir o uso de memória ao **compartilhar partes comuns** de objetos semelhantes, em vez de armazenar todos os dados de forma duplicada em cada objeto.

1. **Quando usar?**
    - O Flyweight é uma **otimização** para situações em que você precisa lidar com **muitos objetos similares** na memória ao mesmo tempo.
    - Antes de usá-lo, avalie se o consumo excessivo de RAM pode ser resolvido de outra maneira.
2. **Estados do objeto:**
    - **Estado intrínseco**: Dados que não mudam e podem ser compartilhados, como cor, forma ou textura.
    - **Estado extrínseco**: Dados que variam entre os objetos, como posição, velocidade ou contexto de uso.
    - O Flyweight mantém apenas o **estado intrínseco** dentro do objeto, enquanto o **estado extrínseco** é passado como parâmetro quando necessário.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Flyweight/structure.png)

1. O padrão flyweight é meramente uma otimização. Antes de aplicá-lo, tenha certeza de que seu programa temo problema de consumo de RAM relacionado à possuir uma quantidade massiva de objetos similares em memória ao mesmo tempo. Certifique-se de que esse problema não pode ser resolvido de outra maneira.
2. A classe `Flyweight` contém a porção do objeto original que pode ser compartilhada entre múltiplos objetos. O mesmo objeto flyweight pode ser utilizado em diferentes contextos. O estado armazenado dentro do flyweight é chamado *intrínseco.* O estado passado para o método do flyweight é chamado *extrínseco.*
3. A classe `Context` contém o estado extrínseco, único em todos os objetos originais. Quando um contexto é pareado com um dos objetos flyweight, ele representa o estado completo do objeto original.
4. Normalmente, o comportamento do objeto original permanece na classe flyweight. Nesse caso, quem quer que chame o método flyweight deve também passar partes do estado extrínseco como parâmetros do método. Em contrapartida, o comportamento pode ser movido para a classe contexto, que utilizaria o flyweight conectado meramente como um objeto de dados.
5. O `Client` calcula ou armazena o estado extrínseco dos flyweights. Da perspectiva do cliente, um flyweight é um objeto template que pode ser configurado em tempo de execução passando alguns dados contextuais como parâmetros de seus métodos.
6. O `FlyweightFactory` administra uma coleção de flyweights existentes. Com o factory, clientes não precisam criar flyweights diretamente. Em vez disso, eles chamam o factory, passando partes do estado intrínseco do flyweight desejado. O factory procura por flyweights existentes e ou retorna um existente que coincide com os critérios ou cria um novo se não for encontrado.

# Problema

Imagine um jogo em que partículas, como balas, mísseis e estilhaços, se movem pelo mapa. Cada partícula é um objeto que contém:

- **Estado intrínseco**: Dados constantes, como cor e sprite.
- **Estado extrínseco**: Dados variáveis, como posição, direção e velocidade.

Se você criar um objeto separado para cada partícula, o consumo de memória pode ser enorme, especialmente quando milhares de partículas estão ativas ao mesmo tempo. Isso pode causar problemas, como **travamentos em máquinas com pouca RAM**.

# Solução

O padrão **Flyweight** resolve o problema ao separar os estados intrínseco e extrínseco:

1. **Compartilhar o estado intrínseco:**
    - Extraia dados constantes (como cor e sprite) para um **único objeto flyweight** que será compartilhado por todas as partículas semelhantes.
    - Por exemplo, todas as balas podem usar o mesmo flyweight que contém o sprite e a cor.
2. **Mover o estado extrínseco para outro local:**
    - Armazene os dados variáveis (como posição e velocidade) em uma estrutura separada, como uma lista ou classe contexto.
    - O contexto guarda a referência ao flyweight e combina o estado intrínseco com o extrínseco para formar o "objeto completo".
    
    Exemplo:
    
    ```csharp
    // Flyweight: Estado compartilhado
    public class ParticulaFlyweight {
        public string Cor { get; }
        public string Sprite { get; }
    
        public ParticulaFlyweight(string cor, string sprite) {
            Cor = cor;
            Sprite = sprite;
        }
    
        public void Renderizar(int x, int y) {
            Console.WriteLine($"Renderizando {Sprite} em {x},{y} com a cor {Cor}");
        }
    }
    
    // Contexto: Estado exclusivo
    public class ContextoParticula {
        public int X { get; }
        public int Y { get; }
        public ParticulaFlyweight Flyweight { get; }
    
        public ContextoParticula(int x, int y, ParticulaFlyweight flyweight) {
            X = x;
            Y = y;
            Flyweight = flyweight;
        }
    
        public void Renderizar() {
            Flyweight.Renderizar(X, Y);
        }
    }
    
    ```
    

## Flyweight e imutabilidade

Como o mesmo objeto flyweight pode ser usado em diferentes contextos, você deve garantir que seu estado não pode ser modificado. Um objeto flyweight deve iniciar seu estado apenas uma vez, através de parâmetros de construtor. Não devem ser expostos quaisquer setters ou campos públicos para outros objetos.

## Flyweight factory

Para facilitar o gerenciamento de flyweights, utiliza-se uma **fábrica (factory)**. Essa fábrica verifica se já existe um flyweight com o estado intrínseco desejado. Se existir, reutiliza-o; caso contrário, cria um novo.

Exemplo:

```csharp
public class FlyweightFactory {
    private readonly Dictionary<string, ParticulaFlyweight> _flyweights = new();

    public ParticulaFlyweight ObterFlyweight(string cor, string sprite) {
        string chave = $"{cor}-{sprite}";
        if (!_flyweights.ContainsKey(chave)) {
            _flyweights[chave] = new ParticulaFlyweight(cor, sprite);
        }
        return _flyweights[chave];
    }
}

```

# **Exemplo Prático no Jogo**

Imagine que o jogo usa apenas três objetos flyweight para representar partículas: uma para balas, outra para mísseis e outra para estilhaços.

Quando uma partícula é criada, seu estado extrínseco (posição, direção e velocidade) é armazenado em um objeto contexto. O flyweight correspondente é compartilhado, economizando memória.

```csharp
var factory = new FlyweightFactory();

// Criar flyweights
var balaFlyweight = factory.ObterFlyweight("Vermelho", "Bala.png");
var missilFlyweight = factory.ObterFlyweight("Cinza", "Missil.png");

// Criar contextos
var bala1 = new ContextoParticula(10, 20, balaFlyweight);
var bala2 = new ContextoParticula(15, 25, balaFlyweight);
var missil = new ContextoParticula(5, 10, missilFlyweight);

// Renderizar
bala1.Renderizar();
bala2.Renderizar();
missil.Renderizar();

```

Saída:

```
Renderizando Bala.png em 10,20 com a cor Vermelho
Renderizando Bala.png em 15,25 com a cor Vermelho
Renderizando Missil.png em 5,10 com a cor Cinza

```

# **Vantagens do Flyweight**

1. **Redução no uso de memória**: Objetos pesados são compartilhados, evitando duplicação.
2. **Flexibilidade**: Você pode combinar o mesmo flyweight com diferentes contextos.
3. **Otimização do desempenho**: Menos objetos na memória reduzem a carga de trabalho do garbage collector.

# **Resumo**

O padrão Flyweight é útil quando você precisa lidar com **grandes volumes de objetos semelhantes** que possuem muitos dados em comum. Ele promove a reutilização de objetos compartilhados e mantém os dados únicos separados, reduzindo significativamente o consumo de memória.