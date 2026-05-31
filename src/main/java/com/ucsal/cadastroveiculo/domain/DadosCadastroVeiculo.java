package com.ucsal.cadastroveiculo.domain;

import com.ucsal.cadastroveiculo.validation.Obrigatorio;
import com.ucsal.cadastroveiculo.validation.PlacaMercosul;

public record DadosCadastroVeiculo(
        @Obrigatorio(mensagem = "Placa é obrigatória.")
        @PlacaMercosul
        String placa,

        @Obrigatorio(mensagem = "Proprietário é obrigatório.")
        String proprietario,

        @Obrigatorio(mensagem = "Marca é obrigatória.")
        String marca,

        @Obrigatorio(mensagem = "Modelo é obrigatório.")
        String modelo,

        int ano
) {
}
