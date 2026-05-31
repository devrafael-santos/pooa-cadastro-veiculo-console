package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.DadosCadastroVeiculo;
import com.ucsal.cadastroveiculo.validation.ValidadorAnotacoes;

public abstract class ValidadorBase implements ValidadorVeiculo {
    private final ValidadorAnotacoes validadorAnotacoes = new ValidadorAnotacoes();

    protected void validarCamposAnotados(DadosCadastroVeiculo dados) {
        validadorAnotacoes.validar(dados);
    }
}
