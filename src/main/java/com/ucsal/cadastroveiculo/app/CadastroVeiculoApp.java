package com.ucsal.cadastroveiculo.app;

import com.ucsal.cadastroveiculo.domain.Caminhao;
import com.ucsal.cadastroveiculo.domain.Carro;
import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;
import com.ucsal.cadastroveiculo.domain.Moto;
import com.ucsal.cadastroveiculo.domain.Veiculo;
import com.ucsal.cadastroveiculo.repository.VeiculoRepository;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxa;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxaCaminhao;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxaCarro;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxaMoto;
import com.ucsal.cadastroveiculo.service.ValidadorCaminhao;
import com.ucsal.cadastroveiculo.service.ValidadorCarro;
import com.ucsal.cadastroveiculo.service.ValidadorMoto;
import com.ucsal.cadastroveiculo.service.ValidadorVeiculo;

import java.math.BigDecimal;
import java.util.Scanner;

public class CadastroVeiculoApp {
    private final Scanner scanner = new Scanner(System.in);
    private final VeiculoRepository repository = new VeiculoRepository();

    public void executar() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1 -> cadastrarVeiculo();
                case 2 -> listarVeiculos();
                case 3 -> buscarPorPlaca();
                case 0 -> System.out.println("Encerrando sistema.");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("=== Cadastro de Veiculos ===");
        System.out.println("1 - Cadastrar veiculo");
        System.out.println("2 - Listar veiculos");
        System.out.println("3 - Buscar por placa");
        System.out.println("0 - Sair");
    }

    private void cadastrarVeiculo() {
        System.out.println();
        System.out.println("Tipos disponiveis: carro, moto, caminhao");

        String tipo = lerTexto("Tipo: ");
        String placa = lerTexto("Placa: ");
        String proprietario = lerTexto("Proprietario: ");
        String marca = lerTexto("Marca: ");
        String modelo = lerTexto("Modelo: ");
        int ano = lerInteiro("Ano: ");

        DadosCadastroVeiculo dados = new DadosCadastroVeiculo(placa, proprietario, marca, modelo, ano);

        try {
            if (repository.existePlaca(placa)) {
                System.out.println("Ja existe um veiculo com essa placa.");
                return;
            }

            ValidadorVeiculo validador = criarValidador(tipo);
            validador.validar(dados);

            Veiculo veiculo = criarVeiculo(tipo, dados);
            CalculadoraTaxa calculadoraTaxa = criarCalculadoraTaxa(tipo);
            BigDecimal taxa = calculadoraTaxa.calcular(veiculo);
            repository.salvar(veiculo);

            System.out.println("Veiculo cadastrado com sucesso.");
            System.out.println("Taxa estimada: R$ " + taxa);
        } catch (IllegalArgumentException exception) {
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private void listarVeiculos() {
        System.out.println();
        if (repository.listarTodos().isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado.");
            return;
        }

        for (Veiculo veiculo : repository.listarTodos()) {
            System.out.println(veiculo.resumo());
        }
    }

    private void buscarPorPlaca() {
        String placa = lerTexto("Placa: ");

        repository.buscarPorPlaca(placa)
                .ifPresentOrElse(
                        veiculo -> System.out.println(veiculo.resumo()),
                        () -> System.out.println("Veiculo nao encontrado.")
                );
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private Veiculo criarVeiculo(String tipo, DadosCadastroVeiculo dados) {
        if (tipo.equalsIgnoreCase("carro")) {
            return new Carro(dados.placa(), dados.proprietario(), dados.marca(), dados.modelo(), dados.ano());
        }

        if (tipo.equalsIgnoreCase("moto")) {
            return new Moto(dados.placa(), dados.proprietario(), dados.marca(), dados.modelo(), dados.ano());
        }

        if (tipo.equalsIgnoreCase("caminhao")) {
            return new Caminhao(dados.placa(), dados.proprietario(), dados.marca(), dados.modelo(), dados.ano());
        }

        throw new IllegalArgumentException("Tipo de veiculo nao suportado: " + tipo);
    }

    private ValidadorVeiculo criarValidador(String tipo) {
        if (tipo.equalsIgnoreCase("carro")) {
            return new ValidadorCarro();
        }

        if (tipo.equalsIgnoreCase("moto")) {
            return new ValidadorMoto();
        }

        if (tipo.equalsIgnoreCase("caminhao")) {
            return new ValidadorCaminhao();
        }

        throw new IllegalArgumentException("Tipo de veiculo nao suportado: " + tipo);
    }

    private CalculadoraTaxa criarCalculadoraTaxa(String tipo) {
        if (tipo.equalsIgnoreCase("carro")) {
            return new CalculadoraTaxaCarro();
        }

        if (tipo.equalsIgnoreCase("moto")) {
            return new CalculadoraTaxaMoto();
        }

        if (tipo.equalsIgnoreCase("caminhao")) {
            return new CalculadoraTaxaCaminhao();
        }

        throw new IllegalArgumentException("Tipo de veiculo nao suportado: " + tipo);
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Digite um numero valido.");
            }
        }
    }
}
