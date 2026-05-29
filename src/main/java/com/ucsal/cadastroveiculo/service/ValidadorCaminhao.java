package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;

public class ValidadorCaminhao extends ValidadorBase {
    @Override
    public void validar(DadosCadastroVeiculo dados) {
        validarCamposObrigatorios(dados);

        if (dados.ano() < 2000) {
            throw new IllegalArgumentException("Caminhoes devem ter ano a partir de 2000.");
        }
    }
}
