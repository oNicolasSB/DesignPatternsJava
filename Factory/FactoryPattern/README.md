# **Definição**

O **Factory Method** é um padrão de projeto criacional que define uma interface para criar objetos, delegando às subclasses a decisão sobre qual tipo de objeto será criado. Isso permite maior flexibilidade e desacoplamento no código, já que a lógica de criação de objetos não está diretamente vinculada ao cliente.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Factory/FactoryPattern/structure.png)

# **Problema**

Imagine que você está desenvolvendo uma aplicação para **gerenciamento de logística**. Inicialmente, ela lida apenas com transporte terrestre, representado por uma classe `Caminhao`. Toda a lógica da aplicação depende diretamente dessa classe.

Com o sucesso da aplicação, empresas de transporte marítimo começam a solicitar suporte para navios. No entanto, adicionar a classe `Navio` à aplicação exige alterações em várias partes do código que atualmente estão fortemente acopladas à classe `Caminhao`.

O problema não para por aí: sempre que um novo tipo de transporte for adicionado, você precisará modificar novamente a base de código, introduzindo condicionais que aumentam a complexidade e dificultam a manutenção.

# **Solução**

O padrão **Factory Method** propõe uma abordagem mais flexível: substituir as chamadas diretas de construção de objetos (uso do operador `new`) por chamadas a um método fábrica especializado. Esse método é responsável por criar os objetos necessários e pode ser sobrescrito em subclasses para determinar qual tipo de objeto será instanciado.

# **Como funciona**

1. **Interface ou classe base**:
    
    Crie uma interface ou classe base comum para os objetos que serão criados. Por exemplo, `Transporte`, que declara um método genérico como `entregar`.
    
2. **Subclasses concretas**:
    
    Implemente essa interface em classes concretas, como `Caminhao` e `Navio`. Cada uma define sua própria versão do método `entregar`.
    
3. **Classe base com método fábrica**:
    
    A classe base, como `Logistica`, contém um método fábrica que retorna um objeto do tipo `Transporte`. Esse método tem um tipo de retorno genérico (a interface ou classe base).
    
4. **Subclasses que especializam o método fábrica**:
    
    Subclasses específicas, como `LogisticaViaria` e `LogisticaMaritima`, sobrescrevem o método fábrica para criar e retornar objetos `Caminhao` ou `Navio`, respectivamente.
    
5. **Cliente desacoplado**:
    
    O código cliente usa apenas a classe base ou interface (`Transporte`), sem precisar saber qual implementação concreta está sendo utilizada. Assim, ele pode chamar o método `entregar` independentemente do tipo de transporte.
    

# **Exemplo aplicado**

### Cenário: Transporte de carga

1. Defina uma interface `Transporte` com o método `entregar`.
    
    ```csharp
    public interface Transporte {
        void Entregar();
    }
    
    ```
    
2. Crie implementações concretas, como `Caminhao` e `Navio`.
    
    ```csharp
    public class Caminhao : Transporte {
        public void Entregar() => Console.WriteLine("Entrega por terra.");
    }
    
    public class Navio : Transporte {
        public void Entregar() => Console.WriteLine("Entrega por mar.");
    }
    
    ```
    
3. Crie uma classe base `Logistica` com o método fábrica.
    
    ```csharp
    public abstract class Logistica {
        public abstract Transporte CriarTransporte();
    }
    
    ```
    
4. Subclasses especializadas sobrescrevem o método fábrica.
    
    ```csharp
    public class LogisticaViaria : Logistica {
        public override Transporte CriarTransporte() => new Caminhao();
    }
    
    public class LogisticaMaritima : Logistica {
        public override Transporte CriarTransporte() => new Navio();
    }
    
    ```
    
5. O código cliente usa a classe base sem se preocupar com os detalhes.
    
    ```csharp
    Logistica logistica = new LogisticaViaria();
    Transporte transporte = logistica.CriarTransporte();
    transporte.Entregar(); // "Entrega por terra."
    
    ```
    

# **Benefícios do Factory Method**

- **Desacoplamento**: O cliente não precisa conhecer os detalhes das classes concretas que estão sendo usadas.
- **Extensibilidade**: Adicionar novos tipos de transporte não exige alterações no código existente, apenas novas implementações de fábrica e produtos.
- **Simplicidade na manutenção**: Evita a proliferação de condicionais e código duplicado.

O **Factory Method** é ideal para cenários em que o tipo exato de objeto a ser criado não é conhecido de antemão ou pode variar dinamicamente em função das condições de execução.