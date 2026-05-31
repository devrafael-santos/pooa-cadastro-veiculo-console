package com.ucsal.cadastroveiculo.app;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;
import com.ucsal.cadastroveiculo.domain.Veiculo;
import com.ucsal.cadastroveiculo.factory.VeiculoFactory;
import com.ucsal.cadastroveiculo.factory.VeiculoFactorySelector;
import com.ucsal.cadastroveiculo.repository.VeiculoRepository;

import java.math.BigDecimal;
import java.util.Scanner;

public class CadastroVeiculoApp {
    private final Scanner scanner = new Scanner(System.in);
    private final VeiculoRepository repository = new VeiculoRepository();
    private final VeiculoFactorySelector factorySelector = new VeiculoFactorySelector();

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

            VeiculoFactory factory = factorySelector.buscarPorTipo(tipo);
            factory.criarValidador().validar(dados);

            Veiculo veiculo = factory.criarVeiculo(dados);
            BigDecimal taxa = factory.criarCalculadoraTaxa().calcular(veiculo);
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
