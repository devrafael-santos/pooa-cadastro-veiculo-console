# Cadastro de Veículos Console
Turma: POOA | ESW-NOT-PIT-4S-T1

Equipe: 
Rafael Costa;
Maria Eduarda França;
Yasmin Planzo.

Design pattern: Abstract Factory.

## Problema

O projeto é um sistema de cadastro de veículo.

O sistema precisa cadastrar diferentes tipos de veículos: carro, moto e caminhão. Cada tipo possui uma família de objetos relacionada:

- o próprio veículo;
- um validador com regras específicas;
- uma calculadora de taxa específica.

Na versão inicial, a classe principal precisava conhecer diretamente todas as classes concretas e escolher cada uma usando vários `if`. Isso deixava o código mais acoplado e mais difícil de manter.

## Solução com Abstract Factory

A interface `VeiculoFactory` define os objetos que toda família de veículo deve criar:

```java
public interface VeiculoFactory {
    Veiculo criarVeiculo(DadosCadastroVeiculo dados);
    ValidadorVeiculo criarValidador();
    CalculadoraTaxa criarCalculadoraTaxa();
}
```

As factories concretas criam famílias coerentes:

- `CarroFactory`: cria `Carro`, `ValidadorCarro` e `CalculadoraTaxaCarro`;
- `MotoFactory`: cria `Moto`, `ValidadorMoto` e `CalculadoraTaxaMoto`;
- `CaminhaoFactory`: cria `Caminhao`, `ValidadorCaminhao` e `CalculadoraTaxaCaminhao`.

Assim, a aplicação principal passa a depender da abstração `VeiculoFactory`, e não das classes concretas de cada tipo de veículo.

## UML 

```mermaid
classDiagram
    class VeiculoFactory {
        <<interface>>
        +criarVeiculo(dados) Veiculo
        +criarValidador() ValidadorVeiculo
        +criarCalculadoraTaxa() CalculadoraTaxa
    }

    class CarroFactory {
        +criarVeiculo(dados) Veiculo
        +criarValidador() ValidadorVeiculo
        +criarCalculadoraTaxa() CalculadoraTaxa
    }

    class MotoFactory {
        +criarVeiculo(dados) Veiculo
        +criarValidador() ValidadorVeiculo
        +criarCalculadoraTaxa() CalculadoraTaxa
    }

    class CaminhaoFactory {
        +criarVeiculo(dados) Veiculo
        +criarValidador() ValidadorVeiculo
        +criarCalculadoraTaxa() CalculadoraTaxa
    }

    VeiculoFactory <|.. CarroFactory
    VeiculoFactory <|.. MotoFactory
    VeiculoFactory <|.. CaminhaoFactory

    class Veiculo {
        <<abstract>>
        -placa String
        -proprietario String
        -marca String
        -modelo String
        -ano int
        +getTipo() String
        +resumo() String
    }

    class Carro {
        +Carro(placa, proprietario, marca, modelo, ano)
        +getTipo() String
    }

    class Moto {
        +Moto(placa, proprietario, marca, modelo, ano)
        +getTipo() String
    }

    class Caminhao {
        +Caminhao(placa, proprietario, marca, modelo, ano)
        +getTipo() String
    }

    Veiculo <|-- Carro
    Veiculo <|-- Moto
    Veiculo <|-- Caminhao

    class ValidadorVeiculo {
        <<interface>>
        +validar(dados) void
    }

    class ValidadorCarro {
        +validar(dados) void
    }

    class ValidadorMoto {
        +validar(dados) void
    }

    class ValidadorCaminhao {
        +validar(dados) void
    }

    ValidadorVeiculo <|.. ValidadorCarro
    ValidadorVeiculo <|.. ValidadorMoto
    ValidadorVeiculo <|.. ValidadorCaminhao

    class CalculadoraTaxa {
        <<interface>>
        +calcular(veiculo) BigDecimal
    }

    class CalculadoraTaxaCarro {
        +calcular(veiculo) BigDecimal
    }

    class CalculadoraTaxaMoto {
        +calcular(veiculo) BigDecimal
    }

    class CalculadoraTaxaCaminhao {
        +calcular(veiculo) BigDecimal
    }

    CalculadoraTaxa <|.. CalculadoraTaxaCarro
    CalculadoraTaxa <|.. CalculadoraTaxaMoto
    CalculadoraTaxa <|.. CalculadoraTaxaCaminhao

    CarroFactory ..> Carro : cria
    CarroFactory ..> ValidadorCarro : cria
    CarroFactory ..> CalculadoraTaxaCarro : cria

    MotoFactory ..> Moto : cria
    MotoFactory ..> ValidadorMoto : cria
    MotoFactory ..> CalculadoraTaxaMoto : cria

    CaminhaoFactory ..> Caminhao : cria
    CaminhaoFactory ..> ValidadorCaminhao : cria
    CaminhaoFactory ..> CalculadoraTaxaCaminhao : cria
```

## Evolução com reflexão e anotações

As validações comuns do cadastro usam anotações próprias nos componentes do record `DadosCadastroVeiculo`:

```java
@Obrigatorio(mensagem = "Placa é obrigatória.")
@PlacaMercosul
String placa
```

O `ValidadorAnotacoes` usa reflexão para ler essas anotações em tempo de execução e aplicar regras genéricas, como campo obrigatório e formato de placa. Com isso, os validadores específicos de carro, moto e caminhão não precisam conhecer manualmente todos os campos básicos do cadastro.

## Execução

Primeiro, faça o clone do repositório:

```console
git clone https://github.com/devrafael-santos/pooa-cadastro-veiculo-console.git
```

Depois é só executar o código da classe `Main.java` pela IDE.
