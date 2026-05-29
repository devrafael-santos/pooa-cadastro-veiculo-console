package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;

public class ValidadorCarro extends ValidadorBase {
    @Override
    public void validar(DadosCadastroVeiculo dados) {
        validarCamposObrigatorios(dados);

        if (dados.ano() < 1980) {
            throw new IllegalArgumentException("Carros devem ter ano a partir de 1980.");
        }
    }
}
