# **Definição**

O **Bridge** é um padrão de projeto estrutural que divide uma classe grande ou um conjunto de classes intimamente relacionadas em **duas hierarquias separadas**: uma para a **abstração** e outra para a **implementação**. Essas hierarquias podem ser desenvolvidas e evoluídas de forma independente, promovendo flexibilidade e reduzindo o acoplamento.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Bridge/structure.png)

# **Problema**

Imagine que você está criando um sistema para representar formas geométricas. Começa com uma classe base `Forma` e cria subclasses como `Circulo` e `Quadrado`. Agora, deseja adicionar cores às formas. Sua primeira ideia é criar subclasses para cada combinação de forma e cor, como `CirculoVermelho` e `QuadradoAzul`.

À medida que novos tipos de forma e cores são adicionados, o número de subclasses cresce **exponencialmente**: uma nova forma exige subclasses para todas as cores, e uma nova cor exige subclasses para todas as formas. Isso torna o código difícil de manter e expandir.

# **Solução**

O problema surge porque você está tentando expandir a hierarquia de classes em **duas dimensões diferentes**: **forma** e **cor**. Essa abordagem baseada apenas em herança rapidamente se torna inviável.

O **Bridge** resolve esse problema substituindo a **herança** por **composição**. Em vez de combinar as duas dimensões diretamente, você as separa em hierarquias independentes:

1. **Abstração (Forma)**: Gerencia o comportamento e as operações gerais das formas.
2. **Implementação (Cor)**: Contém os detalhes relacionados às cores.

A classe `Forma` recebe uma referência a um objeto da hierarquia `Cor`. Isso permite que a `Forma` delegue operações relacionadas à cor para o objeto de `Cor` associado, agindo como uma ponte (ou *bridge*) entre as duas hierarquias. Agora, adicionar uma nova cor ou uma nova forma não requer alterações na outra hierarquia.

# **Exemplo prático: Formas e cores**

### Estrutura inicial:

1. **Interface `Cor`**:
    
    Define o comportamento comum para todas as cores.
    
    ```csharp
    public interface Cor {
        void Preencher();
    }
    
    ```
    
2. **Implementações de `Cor`**:
    
    Subclasses concretas como `Vermelho` e `Azul`.
    
    ```csharp
    public class Vermelho : Cor {
        public void Preencher() => Console.WriteLine("Cor: Vermelho");
    }
    
    public class Azul : Cor {
        public void Preencher() => Console.WriteLine("Cor: Azul");
    }
    
    ```
    
3. **Classe abstrata `Forma`**:
    
    Representa a abstração, com uma referência a um objeto `Cor`.
    
    ```csharp
    public abstract class Forma {
        protected Cor cor;
    
        public Forma(Cor cor) {
            this.cor = cor;
        }
    
        public abstract void Desenhar();
    }
    
    ```
    
4. **Subclasses de `Forma`**:
    
    Classes concretas como `Circulo` e `Quadrado`, que utilizam a cor associada.
    
    ```csharp
    public class Circulo : Forma {
        public Circulo(Cor cor) : base(cor) { }
    
        public override void Desenhar() {
            Console.Write("Desenhando um círculo com ");
            cor.Preencher();
        }
    }
    
    public class Quadrado : Forma {
        public Quadrado(Cor cor) : base(cor) { }
    
        public override void Desenhar() {
            Console.Write("Desenhando um quadrado com ");
            cor.Preencher();
        }
    }
    
    ```
    

### Uso no cliente:

```csharp
Cor vermelho = new Vermelho();
Forma circulo = new Circulo(vermelho);
circulo.Desenhar(); // Saída: Desenhando um círculo com Cor: Vermelho

Cor azul = new Azul();
Forma quadrado = new Quadrado(azul);
quadrado.Desenhar(); // Saída: Desenhando um quadrado com Cor: Azul

```

# **Conceitos de abstração e implementação**

- **Abstração**: Define o **comportamento de alto nível**. No exemplo, a abstração é a hierarquia de formas (`Forma`, `Circulo`, `Quadrado`). Ela delega operações específicas à implementação.
- **Implementação**: Define os **detalhes concretos** que a abstração utiliza. Aqui, é a hierarquia de cores (`Cor`, `Vermelho`, `Azul`).

O Bridge permite que ambas as hierarquias sejam desenvolvidas e modificadas separadamente. Por exemplo:

- Adicionar um novo tipo de forma (ex.: `Triangulo`) não requer alterar a hierarquia de cores.
- Adicionar uma nova cor (ex.: `Verde`) não exige mudanças na hierarquia de formas.

# **Vantagens do Bridge**

1. **Redução de acoplamento**: As hierarquias de abstração e implementação são independentes.
2. **Extensibilidade**: É fácil adicionar novos tipos em qualquer hierarquia sem afetar a outra.
3. **Manutenção simplificada**: Evita explosão de subclasses ao tratar dimensões independentes separadamente.

---

O **Bridge** é ideal em cenários onde é necessário expandir funcionalidades em várias dimensões sem criar um emaranhado de subclasses.