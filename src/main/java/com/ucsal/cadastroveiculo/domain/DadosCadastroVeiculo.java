package com.ucsal.cadastroveiculo.domain;

public record DadosCadastroVeiculo(
        String placa,
        String proprietario,
        String marca,
        String modelo,
        int ano
) {
}
