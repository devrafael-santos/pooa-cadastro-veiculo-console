package com.ucsal.cadastroveiculo.factory;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;
import com.ucsal.cadastroveiculo.domain.Moto;
import com.ucsal.cadastroveiculo.domain.Veiculo;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxa;
import com.ucsal.cadastroveiculo.service.CalculadoraTaxaMoto;
import com.ucsal.cadastroveiculo.service.ValidadorMoto;
import com.ucsal.cadastroveiculo.service.ValidadorVeiculo;

public class MotoFactory implements VeiculoFactory {
    @Override
    public Veiculo criarVeiculo(DadosCadastroVeiculo dados) {
        return new Moto(dados.placa(), dados.proprietario(), dados.marca(), dados.modelo(), dados.ano());
    }

    @Override
    public ValidadorVeiculo criarValidador() {
        return new ValidadorMoto();
    }

    @Override
    public CalculadoraTaxa criarCalculadoraTaxa() {
        return new CalculadoraTaxaMoto();
    }
}
