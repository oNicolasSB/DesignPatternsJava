# **Definição**

O **Adapter** é um padrão de projeto estrutural que permite que objetos com interfaces incompatíveis trabalhem juntos. Ele age como uma ponte, traduzindo a interface de um objeto para algo que outro objeto possa entender.

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Adapter/AdapterPattern/structure.png)

# **Propósito**

O principal objetivo do Adapter é esconder a complexidade da conversão de interfaces entre objetos, permitindo que eles colaborem sem modificações diretas em seus códigos.

- Ele não altera os objetos originais, mas cria uma camada intermediária (o adaptador) que faz a tradução.
- Além de converter formatos de dados, o Adapter pode reconciliar diferenças entre interfaces de APIs ou classes.

# **Problema**

Imagine que você está desenvolvendo uma aplicação que recebe dados em **XML**, mas precisa processar esses dados com uma biblioteca que só aceita o formato **JSON**.

Esses dois formatos são incompatíveis, e adaptar o código diretamente nos objetos originais não seria uma boa prática, pois:

1. Viola o princípio da **abertura e fechamento** (código aberto para extensão, mas fechado para modificação).
2. Aumenta o risco de introduzir erros e dificulta a manutenção.

# **Solução**

Para resolver esse problema, você pode implementar um **Adapter**. O Adapter é uma classe intermediária que atua como um "tradutor", convertendo dados de XML para JSON para que a biblioteca funcione corretamente.

# **Como funciona**

O funcionamento básico do Adapter pode ser dividido em três etapas:

1. **Recepção**:
O adaptador implementa a interface esperada pelo objeto de destino (neste caso, a biblioteca que aceita JSON).
2. **Conversão**:
Quando o objeto de origem (XML) faz uma chamada, o adaptador converte os dados ou as solicitações para o formato ou método esperado pelo objeto de destino.
3. **Encaminhamento**:
O adaptador repassa a chamada ao objeto de destino, garantindo que ela seja compreendida.

Em alguns casos, é possível criar um **adaptador bidirecional**, que converte chamadas ou dados em ambas as direções (por exemplo, de XML para JSON e vice-versa).

# **Exemplo aplicado**

No exemplo do processamento de dados XML:

1. Você cria uma classe chamada `XmlToJsonAdapter`.
2. Esta classe implementa a interface da biblioteca que trabalha com JSON.
3. Internamente, o adaptador converte os dados XML recebidos em JSON antes de passá-los para a biblioteca.

Assim, os dois componentes (a aplicação e a biblioteca) podem interagir sem saber que há um adaptador no meio.

# **Benefícios do padrão Adapter**

- **Reutilização**: Permite que você use classes ou bibliotecas existentes, mesmo que suas interfaces sejam incompatíveis.
- **Desacoplamento**: O adaptador separa os objetos originais, evitando modificações diretas.
- **Flexibilidade**: Adiciona funcionalidade sem alterar o código de objetos existentes.
- **Manutenção facilitada**: Reduz o impacto de mudanças futuras nas interfaces.

O Adapter é particularmente útil ao integrar sistemas legados ou componentes de terceiros com uma aplicação moderna, mantendo um código limpo e fácil de gerenciar.