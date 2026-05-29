package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;

public class ValidadorMoto extends ValidadorBase {
    @Override
    public void validar(DadosCadastroVeiculo dados) {
        validarCamposObrigatorios(dados);

        if (dados.ano() < 1990) {
            throw new IllegalArgumentException("Motos devem ter ano a partir de 1990.");
        }
    }
}
