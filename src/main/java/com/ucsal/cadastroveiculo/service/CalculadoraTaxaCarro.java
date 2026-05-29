package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.Veiculo;

import java.math.BigDecimal;

public class CalculadoraTaxaCarro implements CalculadoraTaxa {
    @Override
    public BigDecimal calcular(Veiculo veiculo) {
        return BigDecimal.valueOf(180.00);
    }
}
