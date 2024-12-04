# Definição

Memento é um padrão comportamental que permite salvar e restaurar o estado prévio de um objeto sem revelar detalhes de sua implementação.

# Estrutura

## Implementação 1 - Classes aninhadas

![structure1.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Memento/structure1.png)

A implementação clássica do memento se apoia no suporte de classes aninhadas, disponível em diversas linguagens de programação populares (como C++, C#, Java, etc.)

1. A classe `Originator` pode criar snapshots de seu próprio estado, como também restaurar seu estado de snapshots quando necessário.
2. O `Memento` é um objeto valor que atua como uma snapshot do estado do originador. É uma prática comum fazer o memento imutável e passar os dados para ele uma única vez, através do construtor.
3. O `Caretaker` sabe não só “quando” e “onde” capturar o estado do originador, mas também quando o estado deve ser restaurado.
Um caretaker pode manter o histórico do originador armazenando uma pilha de mementos. Quando o originador tem que voltar no tempo, o caretaker busca o memento mais alto da pilha e passa-o para o método de restauração do originador.
4. Nessa implementação, a classe memento é aninhada dentro do originador. Isso permite o originador acessar os campos e métodos de um memento, mesmo que eles sejam declarados privados. Em contra-partida, o caretaker tem acesso limitado aos métodos e campos do memento, que permite-o salvar mementos em uma fila mas sem poder alterar seus dados.

## Implementação 2 - Interface intermediária

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Memento/structure2.png)

Existe uma implementação alternativa, útil para linguagens de programação que não suportam classes aninhadas (sim, PHP).

1. Na ausência de classes aninhadas, você pode restringir o acesso aos campos do memento ao estabelecer uma convenção de que caretakers podem apenas lidar com mementos através de uma interface intermediária declarada explicitamente.
2. Em contra partida, originadores só podem trabalhar com o objeto memento diretamente, acessando campos e métodos declarados na classe memento. O lado ruim dessa abordagem é que você precisa declarar todos os membros do memento como públicos.

## Implementação 3 - encapsulamento ainda mais restrito

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Memento/structure3.png)

1. Essa implementação permite ter múltiplos tipos de originadores e mementos. Cada originador funciona com uma classe memento correspondente. Nem os originadores nem os mementos expõem seu estado a ninguém.
2. Caretakers agora são explicitamente restritos de alterar o estado armazenado nos mementos. Além disso, a classe `Caretaker` se torna independente do originador pois o método de restauração agora é definido dentro da classe memento.
3. Cada memento se torna vinculado ao originador que o produziu. O originador passa a si mesmo para o construtor do memento. junto com os valores de seu estado. Graças ao relacionamento íntimo entre essas classes, um memento pode restaurar o estado de seu originador, desde que o originador tenha definido os devidos setters.

# Problema

Imagine que você está desenvolvendo um aplicativo de edição de texto. Além de edição de texto simples, seu editor também pode formatar, anexar imagens, etc.

Em dado momento, você decide permitir os usuários a desfazer quaisquer operações feitas no texto. Essa funcionalidade se torna tão comum com o passar dos anos que hoje em dia as pessoas esperam que todos os aplicativos façam isso. Para essa implementação, você opta pela abordagem direta. Antes de executar qualquer operação, o aplicativo salva o estado de todos os objetos e salva-o em algum armazenamento. Depois, quando um usuário decide reverter uma ação, o aplicativo busca a última snapshot do histórico e usa ela para restaurar todos os objetos.

Vamos pensar sobre todas essas snapshots de estado. Como exatamente você criaria uma? Você provavelmente precisaria passar por todos os campos em um objeto e copiar seus valores no armazenamento. Contudo, isso só funcionaria se o objeto tivesse acesso irrestrito ao seu conteúdo. Infelizmente, a maioria dos objetos reais não vai permitir outros enxergarem seu conteúdo tão facilmente, escondendo todo conteúdo relevante em campos privados.

Vamos ignorar esse problema por enquanto e vamos assumir que nossos objetos se comportem como hippies: preferem relacionamentos abertos e mantém seu estado público. Enquanto essa abordagem resolveria o problema imediato e permitiria-o criar snapshots de objetos como quiser, ainda assim existiriam alguns problemas sérios. No futuro, você pode optar por refatorar algumas classes do editor, ou adicionar ou remover alguns dos campos. Parece fácil, mas isso também iria requerer alterar as classes responsáveis por copiar o estado dos objetos afetados.

Mas ainda tem mais. Vamos considerar as “snapshots” do estado do editor em si. Que dados contém? No mínimo, deve conter o texto, coordenadas do cursor, posição atual do scroll, etc. Para criar um snapshot, você precisaria coletar esses valores e colocá-los em algum tipo de contêiner.

Na maioria dos casos, você vai armazenar dezenas desses objetos contêineres dentro de alguma lista que representaria o histórico. Portanto os contêineres provavelmente seriam objetos de uma classe. Essa classe quase não teria métodos, mas dezenas de campos que espelham o estado do editor. Para permitir outros objetos a escreverem e lerem dados de e para uma snapshot, você provavelmente precisaria de fazer seus campos públicos. isso iria expor todos os estados do editor, privados ou não. Outras classes se tornariam dependentes de cada pequena alteração feita na classe snapshot, do contrário o que aconteceria entre campos privados e métodos sem afetar classes externas.

Parece que chegamos em um beco sem saída: ou você expõe todos os detalhes internos das classes, tornando-as muito frágeis, ou restringe o acesso ao seu estado, tornando impossível criar snapshots. Existe alguma outra maneira de implementar o “desfazer”?

# Solução

Todos os problemas que experienciamos são causados por encapsulamento quebrado. Alguns objetos tentam fazer mais do que eles deveriam. Para coletar os dados necessários para realizar alguma ação eles invadem o espaço privado de outros objetos em vez de deixar esses objetos realizarem a ação.

O padrão memento delega a criação das snapshots de estado para a própria classe detentora do estado, o objeto *originador*. Por isso, em vez de outros objetos tentarem copiar o estado do editor “do lado de fora”, a classe editor pode fazer a snapshot por si mesma, visto que ela possui acesso total ao próprio estado.

O padrão sugere armazenar a cópia do objeto estado em um objeto especial chamado *memento.* Os conteúdos do memento não são acessíveis por nenhum outro objeto exceto o objeto que o produziu. Outros objetos devem se comunicar com o memento usando uma interface limitada que deve permitir recuperar os metadados da snapshot (data de criação, o nome da ação executada, etc.), mas não o estado original do objeto contido na snapshot.

Uma política tão restritiva te permite armazenar mementos dentro de outros objetos, normalmente denominados *caretakers*. Desde que o caretaker trabalhe com o memento apenas através da interface, ele não é capaz de alterar o estado armazenado dentro do memento. Ao mesmo tempo, o originador tem acesso à todos os campos dentro do memento, permitindo-o restaurar o estado anterior ao seu desejo.

Quando um usuário ativa o desfazer, o histórico pega o memento mais recente da pilha e passa-o de volta ao editor, requisitando o roll-back. Como o editor tem acesso total ao memento, ele altar seu próprio estado com os valores recuperados no memento.