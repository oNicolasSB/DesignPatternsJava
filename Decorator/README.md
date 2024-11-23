# Definição

O **Decorator** é um padrão estrutural que permite adicionar novos comportamentos a objetos de forma dinâmica, encapsulando-os em *wrappers*. Esses wrappers seguem a mesma interface que o objeto original, tornando a adição de funcionalidades flexível e transparente para o cliente.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Decorator/structure.png)

1. **`Component` (interface base):**
    - Define a interface comum para o objeto base e os decorators.
    - Exemplo: `INotifier` com o método `Send(string message)`.
2. **`ConcreteComponent` (componente base):**
    - Implementa a funcionalidade padrão.
    - Exemplo: `EmailNotifier` que envia notificações por e-mail.
3. **`BaseDecorator`:**
    - Implementa a interface base e contém uma referência para um `Component`.
    - Encapsula o comportamento básico e delega chamadas ao objeto referenciado.
    - Exemplo: `NotifierDecorator`.
4. **`ConcreteDecorator` (decorators concretos):**
    - Extendem o `BaseDecorator` para adicionar funcionalidades específicas.
    - Exemplo: `SMSDecorator`, `SlackDecorator`.
5. **Cliente:**
    - Usa a interface do `Component` para manipular objetos sem se preocupar se eles são "puros" ou decorados.

# Problema

Imagine que você está desenvolvendo uma biblioteca de notificações. Na versão inicial, havia uma única classe chamada `Notifier` que enviava mensagens por e-mail. Ela possuía um construtor para configurar os destinatários e um método `send()` para enviar mensagens.

Agora os usuários pedem suporte a outros canais de notificação, como:

- SMS,
- Slack,
- Facebook Messenger.

Adicionar cada canal como uma nova subclasse do `Notifier` parece uma solução inicial. Por exemplo:

- `EmailNotifier`,
- `SMSNotifier`,
- `SlackNotifier`.

No entanto, surge a necessidade de **combinar canais**: um cliente pode querer notificações por e-mail *e* SMS, ou e-mail *e* Slack. Criar subclasses para todas as combinações possíveis rapidamente gera um crescimento exponencial no número de classes, tornando o código difícil de manter.

Além disso:

1. **Herança é estática**: não permite adicionar ou remover comportamentos em tempo de execução.
2. **Limitação de herança única**: a maioria das linguagens não suporta herança múltipla, dificultando a combinação de comportamentos.

# Solução

O **Decorator** resolve esses problemas ao usar *composição* em vez de herança. Em vez de criar subclasses para cada combinação de comportamentos, você cria **wrappers** (*decorators*) que adicionam funcionalidades a objetos em tempo de execução.

Os *decorators*:

1. Implementam a mesma interface do objeto original.
2. Encapsulam o objeto base (ou outro decorator).
3. Adicionam novos comportamentos antes ou depois de delegar a chamada ao objeto encapsulado.

Por exemplo:

- O `Notifier` básico continua enviando notificações por e-mail.
- Para adicionar suporte a SMS, Slack ou outras plataformas, você cria decorators como `SMSDecorator` ou `SlackDecorator`.
- O cliente pode envolver o objeto base em uma pilha de decorators conforme necessário:
    
    ```java
    Notifier notifier = new SMSDecorator(new SlackDecorator(new EmailNotifier()));
    notifier.send("Mensagem importante");
    ```
    

# Benefícios do Decorator

1. **Extensibilidade dinâmica:** Adiciona ou remove comportamentos em tempo de execução.
2. **Reutilização:** Combinando decorators, evita a explosão de subclasses.
3. **Transparência:** O cliente não precisa saber se está trabalhando com um objeto puro ou decorado.

# Quando evitar o Decorator?

- **Muitas camadas:** Uso excessivo de decorators pode tornar a estrutura difícil de entender e depurar.
- **Objetos imutáveis:** O padrão requer objetos mutáveis, o que pode não ser ideal em sistemas baseados em programação funcional.