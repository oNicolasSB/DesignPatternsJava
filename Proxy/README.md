# Definição

O proxy é um padrão estrutural que age como um intermediário entre um cliente e um objeto “real” que ele deseja acessar. O proxy utiliza uma interface idêntica ao objeto real, permitindo que o cliente o utilize sem saber se está acessando diretamente o objeto real ou uma cópia (o proxy).

# Estrutura

![structure.png](https://github.com/oNicolasSB/DesignPatternsJava/blob/main/Proxy/structure.png)

# Propósito

O objetivo principal do proxy é controlar o acesso ao objeto real. Assim, permitindo executar operações antes ou depois do acesso ao objeto real. 

# Exemplo de problema

Imagine uma aplicação que exibe imagens de alta resolução para os usuários. Essas imagens são pesadas, e carregá-las todas de uma vez pode ser custoso e causar lentidão. Contudo, as imagens só precisam ser carregadas quando o usuário realmente tentar visualizá-las.

# Solução com Proxy

Para resolver o problema de carregamento desnecessário das imagens, podemos utilizar um Proxy Virtual que carrega a imagem de alta resolução apenas quando o usuário solicita sua visualização.

# Exemplo de código (Java)

### Interface da imagem:

```jsx
public interface Image {
    void display();
}
```

### Imagem real:

```jsx
public class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk(); // Carregamento pesado ocorre aqui
    }
    
    private void loadImageFromDisk() {
        System.out.println("Loading " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}
```

### Proxy da Imagem:

```jsx
public class ImageProxy implements Image {
    private RealImage realImage;
    private String filename;
    
    public ImageProxy(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Carrega apenas quando necessário
        }
        realImage.display();
    }
}
```

### Uso do proxy:

```jsx
public class Client {
    public static void main(String[] args) {
        Image image = new ImageProxy("high_resolution_image.jpg");
        
        // A imagem será carregada e exibida apenas na primeira chamada
        // ao método display
        image.display(); // Carrega e exibe a imagem
        image.display(); // Exibe sem carregar novamente
    }
}
```

## Explicação da solução

No exemplo acima:

- A primeira chamada ao método `display()` em `ImageProxy` causa o carregamento da imagem, pois `realImage` ainda é `null` .
- Uma vez carregada, as chamadas subsequentes ao método `display()` não causam novo carregamento, pois o proxy já possui a referência para a `RealImage` .

Esse mecanismo reduz o tempo de espera e o uso de memória inicial, inicial carregando a imagem de alta resolução somente quando necessário.

# Vantagens do Proxy

- Eficiência: Evita operações desnecessárias até que sejam realmente precisas.
- Controle de acesso: Pode permitir ou negar acesso a funcionalidades específicas com base nas permissões.
- Flexibilidade: Torna mais fácil adicionar funcionalidades secundárias (como logging ou cache) sem alterar o objeto real.

# Limitações do Proxy

- Complexidade adicional: Adiciona camadas de código e pode dificultar a depuração.
- Latência em alguns casos: Pode introduzir atrasos devido a sobrecarga de redirecionamento pelo proxy.