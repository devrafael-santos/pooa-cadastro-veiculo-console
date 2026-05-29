package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.Veiculo;

import java.math.BigDecimal;

public class CalculadoraTaxaCaminhao implements CalculadoraTaxa {
    @Override
    public BigDecimal calcular(Veiculo veiculo) {
        return BigDecimal.valueOf(350.00);
    }
}
