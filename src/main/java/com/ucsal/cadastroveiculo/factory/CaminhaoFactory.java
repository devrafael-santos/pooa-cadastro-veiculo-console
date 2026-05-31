package com.ucsal.cadastroveiculo.factory;

import com.ucsal.cadastroveiculo.domain.Caminhao;
import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;
import com.ucsal.cadastroveiculo.domain.Veiculo;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxa;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxaCaminhao;
import com.ucsal.cadastroveiculo.service.ValidadorCaminhao;
import com.ucsal.cadastroveiculo.service.ValidadorVeiculo;

public class CaminhaoFactory implements VeiculoFactory {
    @Override
    public Veiculo criarVeiculo(DadosCadastroVeiculo dados) {
        return new Caminhao(dados.placa(), dados.proprietario(), dados.marca(), dados.modelo(), dados.ano());
    }

    @Override
    public ValidadorVeiculo criarValidador() {
        return new ValidadorCaminhao();
    }

    @Override
    public CalculadoraTaxa criarCalculadoraTaxa() {
        return new CalculadoraTaxaCaminhao();
    }
}
