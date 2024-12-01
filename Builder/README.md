# Definição

O Builder é um padrão criacional que te permite construir objetos complexos passo a passo. O padrão torna possível produzir diferentes tipos e representações de um objeto usando o mesmo código de construção.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Builder/structure.png)

1. A interface `Builder` declara os passos de construção do produto que são comuns a todos os tipos de builders.
2. `ConcreteBuilder`'s fornecem diferentes implementações para os passos de construção. Construtores concretos podem produzir produtos que não seguem a interface comum.
3. `Product`s são objetos resultantes. Produtos construídos com diferentes builders não precisam pertencer a mesma hierarquia de classe ou interface.
4. A classe `Director` define a ordem na qual chamar os passos de construção, de forma que você possa criar e reutilizar configurações de produtos específicas.
5. O `Client` deve associar um  dos objetos builders com um diretor. Normalmente, isso só é feito uma única vez, por parâmetros do construtor do diretor. Então o diretor usa os objetos builders para todas as futuras construções. Contudo, existe uma abordagem alternativa para quando o cliente passa o objeto builder para o método de produção do diretor. Nesse caso, você pode usar diferentes builders toda vez que você produzir algo com o diretor.

# Problema

Imagine um objeto complexo que precisa de uma inicialização trabalhosa, passo a passo com muitos campos e objetos aninhados. Tal código de inicialização geralmente é enfiado dentro de um construtor monstruoso com vários parâmetros. Ou ainda pior: espalhado por todo código cliente.

Por exemplo, vamos pensar em como criar um objeto `House` . Para construir uma casa simples, você precisa de construir quatro paredes e um chão, instalar uma porta, encaixar um par de janelas, e construir um telhado. Mas e se você quiser uma casa maior, mais brilhante, com um quintal e outras coisas (como sistema de aquecimento, encanação e fiação elétrica)?

A solução mais simples é extender a classe base `House` e criar um conjunto de subclasses para abranger todas as combinações de parâmetros. Mas eventualmente você vai acabar com um número de subclasses considerável. Qualquer novo parâmetro, como o estilo da varanda, vai precisar crescer essa hierarquia ainda mais.

Existe uma outra aproximação que não envolve criação de subclasses. Você pode criar um construtor gigante direto na classe base `House` com todas os parâmetros possíveis que controlam o objeto casa. Enquanto essa aproximação de fato elimina a necessidade de subclasses, ela cria outro problema.

Na maioria dos casos muitos parâmetros não vão ser utilizados, fazendo a chamada do construtor muito feia. Por exemplo, somente uma fração de casas possuem piscinas, então os parâmetros relacionados à piscinas serão inúteis 9 de 10 vezes.

# Solução

O padrão builder sugere que você extraia o código de construção do objeto de dentro de sua própria classe e mova-a para objetos separados chamados *builders*.

O padrão organiza a construção dos objetos em um conjunto de passos (`buildWalls`,`buildDoor`, etc.). Para criar um objeto, você executa uma série desses passos em um objeto builder. A parte importante é que você não precisa chamar todos os passos. Você pode chamar somente os passos que são necessários para produzir uma configuração específica de um objeto.

Alguns passos de construção podem necessitar diferentes implementações quando você precisa produzir várias representações do produto. Por exemplo, paredes em uma cabana podem ser feitas de madeira, mas as paredes do castelo devem ser feitas de pedra.

Nesse caso, você pode criar diversas classes builder diferentes que implementam o mesmo conjunto de passos de construção, mas de uma forma diferente. Então você pode usar esses builders no processo de construção (isto é, um conjunto ordenado de chamadas para os passos de construção) para produzir diferentes tipos de objetos.

Por exemplo, imaginer um construtor que constrói tudo de madeira e vidro, um segundo que constrói tudo com ferro e pedra e um terceiro que usa ouro e diamantes. Ao chamar o mesmo conjunto de instruções, você você consegue uma casa convencional do primeiro construtor, um pequeno castelo do segundo e um palácio com o terceiro. Entretanto, isso apenas funcionaria se o código do cliente que chama por esses passos de construção seja capaz de interação com esses builders usando uma interface comum.

## Director

Você pode ir mais fundo e extrair uma série de chamados aos passos do builder em uma classe separada chamada *director*. A classe director define a ordem a se executar os passos de construção, enquanto o builder provém a implementação para esses passos.

Possuir uma classes director em seu programa não é estritamente necessária. Você sempre pode chamar os passos de construção em uma ordem específica diretamente do código cliente. Contudo, a classe director pode ser um bom lugar para colocar várias rotinas de construção de forma que você possa reutilizá-las em todo seu programa.

Adicionalmente, a classe director esconde totalmente os detalhes da construção do produto do código cliente. O cliente apenas precisa associar um builder com um diretor, iniciar a construção com o diretor, e pegar o resultado do builder.