package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;

public abstract class ValidadorBase implements ValidadorVeiculo {
    protected void validarCamposObrigatorios(DadosCadastroVeiculo dados) {
        if (dados.placa().isBlank() || dados.proprietario().isBlank()
                || dados.marca().isBlank() || dados.modelo().isBlank()) {
            throw new IllegalArgumentException("Todos os campos de texto sao obrigatorios.");
        }

        if (!dados.placa().matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
            throw new IllegalArgumentException("Placa deve estar no formato ABC1D23 ou ABC1234.");
        }
    }
}
