# **Definição**

O **Strategy** é um padrão de projeto comportamental que permite organizar algoritmos relacionados em famílias, encapsulando cada um deles em classes separadas. Isso torna os algoritmos **intercambiáveis** em tempo de execução, sem alterar o código que os utiliza.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Strategy/structure.png)

# **Problema**

Imagine que você está desenvolvendo um aplicativo de navegação que inicialmente traça rotas apenas para carros. Com o tempo, surge a necessidade de incluir rotas para caminhadas, transporte público, bicicletas e até sugestões de rotas turísticas.

Cada novo tipo de rota exige alterações significativas na classe principal responsável pelo cálculo das rotas. Isso resulta em uma classe cada vez maior, mais complexa e difícil de manter.

Além disso:

1. **Risco de erros**: Alterar um algoritmo pode afetar o comportamento de outros.
2. **Dificuldade de colaboração**: Quando várias pessoas trabalham na mesma classe gigantesca, surgem conflitos de código.
3. **Baixa extensibilidade**: Adicionar novos algoritmos se torna um desafio, porque a classe principal precisa conhecer todas as variações possíveis.

Esse cenário cria um código rígido, difícil de manter e com baixa capacidade de adaptação.

# **Solução**

O **Strategy** resolve esse problema ao **separar os algoritmos** em classes independentes chamadas **estratégias**. Em vez de implementar todos os algoritmos diretamente na classe principal (contexto), cada algoritmo é encapsulado em sua própria classe.

### Como funciona:

1. **Defina uma interface ou classe base**: Essa interface descreve o método comum que todas as estratégias devem implementar.
    
    ```csharp
    public interface IEstrategiaRota {
        List<Ponto> ConstruirRota(Ponto origem, Ponto destino);
    }
    
    ```
    
2. **Implemente as estratégias concretas**: Crie classes separadas para cada algoritmo específico.
    
    ```csharp
    public class RotaCarro : IEstrategiaRota {
        public List<Ponto> ConstruirRota(Ponto origem, Ponto destino) {
            Console.WriteLine("Construindo rota para carros...");
            return new List<Ponto>(); // Lógica específica.
        }
    }
    
    public class RotaCaminhada : IEstrategiaRota {
        public List<Ponto> ConstruirRota(Ponto origem, Ponto destino) {
            Console.WriteLine("Construindo rota para caminhada...");
            return new List<Ponto>(); // Lógica específica.
        }
    }
    
    ```
    
3. **Use um contexto para delegar a lógica**: O contexto mantém uma referência para a estratégia atual e delega o trabalho para ela.
    
    ```csharp
    public class Navegador {
        private IEstrategiaRota estrategia;
    
        public void DefinirEstrategia(IEstrategiaRota estrategia) {
            this.estrategia = estrategia;
        }
    
        public void ConstruirRota(Ponto origem, Ponto destino) {
            if (estrategia == null) {
                Console.WriteLine("Nenhuma estratégia definida!");
                return;
            }
            var rota = estrategia.ConstruirRota(origem, destino);
            // Renderiza a rota no mapa...
        }
    }
    
    ```
    
4. **No cliente, escolha a estratégia desejada**: O cliente decide qual estratégia usar com base nas necessidades do usuário.
    
    ```csharp
    Navegador navegador = new Navegador();
    
    navegador.DefinirEstrategia(new RotaCarro());
    navegador.ConstruirRota(new Ponto(0, 0), new Ponto(10, 10)); // Saída: Construindo rota para carros...
    
    navegador.DefinirEstrategia(new RotaCaminhada());
    navegador.ConstruirRota(new Ponto(0, 0), new Ponto(5, 5)); // Saída: Construindo rota para caminhada...
    
    ```
    

# **Vantagens do Strategy**

1. **Código mais organizado**: Cada algoritmo está encapsulado em uma classe independente, facilitando sua compreensão e manutenção.
2. **Extensibilidade**: Novos algoritmos podem ser adicionados sem alterar o código existente. Basta criar uma nova classe que implemente a interface de estratégia.
3. **Maior flexibilidade**: As estratégias podem ser trocadas em tempo de execução.
4. **Trabalho em equipe mais eficiente**: A separação de responsabilidades reduz conflitos de código e facilita o desenvolvimento colaborativo.

# **Aplicação no exemplo de navegação**

No aplicativo de navegação:

- **Estratégias concretas**: `RotaCarro`, `RotaCaminhada`, `RotaCiclista`, etc., representam diferentes algoritmos para calcular rotas.
- **Contexto**: A classe `Navegador` mantém a referência à estratégia escolhida e delega a tarefa de calcular a rota.
- **Cliente**: O botão da interface de usuário permite que o usuário selecione a estratégia desejada, como "rota para ciclistas".

Ao encapsular os algoritmos em estratégias independentes, o código se torna mais modular, fácil de estender e menos sujeito a erros.