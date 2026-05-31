package com.ucsal.cadastroveiculo.factory;

import com.ucsal.cadastroveiculo.domain.Carro;
import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;
import com.ucsal.cadastroveiculo.domain.Veiculo;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxa;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxaCarro;
import com.ucsal.cadastroveiculo.service.ValidadorCarro;
import com.ucsal.cadastroveiculo.service.ValidadorVeiculo;

public class CarroFactory implements VeiculoFactory {
    @Override
    public Veiculo criarVeiculo(DadosCadastroVeiculo dados) {
        return new Carro(dados.placa(), dados.proprietario(), dados.marca(), dados.modelo(), dados.ano());
    }

    @Override
    public ValidadorVeiculo criarValidador() {
        return new ValidadorCarro();
    }

    @Override
    public CalculadoraTaxa criarCalculadoraTaxa() {
        return new CalculadoraTaxaCarro();
    }
}
