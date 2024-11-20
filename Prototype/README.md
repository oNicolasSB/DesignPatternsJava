# **Definição**

O **Prototype** é um padrão de projeto criacional que permite criar **cópias de objetos existentes** de forma eficiente, sem que o código dependa diretamente de suas classes específicas.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Prototype/structure.png)

# **Problema**

Imagine que você precisa criar uma cópia exata de um objeto. Para isso:

1. Você precisa criar um novo objeto da mesma classe.
2. Em seguida, copiar todos os valores dos campos do objeto original para o novo.

Embora pareça simples, surgem alguns problemas:

- **Acesso a campos privados**: Se o objeto original tiver campos privados, você não conseguirá copiá-los diretamente de fora da classe.
- **Dependência de classes concretas**: Você precisaria conhecer a classe específica do objeto para criar sua cópia. Isso aumenta o acoplamento e dificulta a manutenção.
- **Trabalhando apenas com interfaces**: Muitas vezes, você só conhece a interface do objeto, não sua classe concreta. Por exemplo, ao receber um parâmetro em um método que aceita objetos genéricos. Nesse caso, como criar uma cópia sem saber a classe exata?

Além disso, quando os objetos têm muitos campos e múltiplas configurações possíveis, criar novas instâncias pode ser ineficiente.

# **Solução**

O **Prototype** resolve esses problemas ao **delegar o processo de clonagem ao próprio objeto**.

1. **Interface de clonagem**: É criada uma interface comum para todos os objetos que suportam clonagem. Normalmente, essa interface contém um único método, como `Clonar()`.
    
    ```csharp
    public interface IPrototype {
        IPrototype Clonar();
    }
    
    ```
    
2. **Implementação do método `Clonar()`**: Cada classe que implementa a interface é responsável por definir como o objeto será copiado. Isso permite que até campos privados sejam clonados, já que o método opera no contexto da própria classe.
    
    ```csharp
    public class Documento : IPrototype {
        public string Titulo { get; set; }
        public string Conteudo { get; set; }
    
        public IPrototype Clonar() {
            return new Documento {
                Titulo = this.Titulo,
                Conteudo = this.Conteudo
            };
        }
    }
    
    ```
    
3. **Uso dos protótipos**: Em vez de criar objetos do zero, você trabalha com "protótipos" — objetos preexistentes configurados como deseja. Quando precisa de uma nova instância, basta **clonar o protótipo**.
    
    ```csharp
    var docOriginal = new Documento {
        Titulo = "Proposta Comercial",
        Conteudo = "Conteúdo da proposta..."
    };
    
    var docCopia = (Documento)docOriginal.Clonar();
    docCopia.Titulo = "Proposta Revisada";
    
    Console.WriteLine(docOriginal.Titulo); // Saída: Proposta Comercial
    Console.WriteLine(docCopia.Titulo);   // Saída: Proposta Revisada
    
    ```
    

# **Vantagens do Prototype**

1. **Menor acoplamento**: O código que clona o objeto não precisa conhecer sua classe concreta, apenas a interface de clonagem.
2. **Eficiência**: Criar um objeto clonando um protótipo pode ser mais rápido do que configurá-lo do zero, especialmente se ele tiver muitos campos ou configurações complexas.
3. **Extensibilidade**: É fácil adicionar novos tipos de objetos clonáveis. Basta implementar o método `Clonar()` na classe correspondente.
4. **Acesso a campos privados**: Como o método de clonagem está dentro da própria classe, ele pode acessar campos privados sem restrições.

# **Quando usar o Prototype?**

- **Criação de objetos complexos**: Quando o processo de configuração inicial de um objeto é caro ou envolve muitas etapas.
- **Edição de objetos preexistentes**: Quando você precisa criar variações de objetos já existentes sem alterá-los diretamente.
- **Desacoplamento de classes concretas**: Quando o código que cria os objetos não deve depender diretamente de suas classes.

# **Exemplo prático**

No caso de um editor gráfico, imagine que você tem várias formas (círculos, retângulos, etc.) com propriedades como cor, tamanho e posição. Criar cada forma do zero pode ser trabalhoso, especialmente se muitas delas compartilham configurações semelhantes.

Com o **Prototype**, você cria um "protótipo base" para cada forma e o utiliza para gerar novas instâncias:

```csharp
public class Circulo : IPrototype {
   public int Raio { get; set; }
   public string Cor { get; set; }

   public IPrototype Clonar() {
       return new Circulo { Raio = this.Raio, Cor = this.Cor };
   }
}

var circuloBase = new Circulo { Raio = 10, Cor = "Vermelho" };
var novoCirculo = (Circulo)circuloBase.Clonar();
novoCirculo.Cor = "Azul";

Console.WriteLine(circuloBase.Cor); // Vermelho
Console.WriteLine(novoCirculo.Cor); // Azul

```

Assim, o **Prototype** proporciona flexibilidade, reutilização e economia de tempo ao criar objetos personalizados.