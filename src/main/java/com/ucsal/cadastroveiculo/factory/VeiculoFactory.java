package com.ucsal.cadastroveiculo.factory;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;
import com.ucsal.cadastroveiculo.domain.Veiculo;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxa;
import com.ucsal.cadastroveiculo.service.ValidadorVeiculo;

public interface VeiculoFactory {
    Veiculo criarVeiculo(DadosCadastroVeiculo dados);

    ValidadorVeiculo criarValidador();

    CalculadoraTaxa criarCalculadoraTaxa();
}
