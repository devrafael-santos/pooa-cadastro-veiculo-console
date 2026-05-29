package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.Veiculo;

import java.math.BigDecimal;

public class CalculadoraTaxaMoto implements CalculadoraTaxa {
    @Override
    public BigDecimal calcular(Veiculo veiculo) {
        return BigDecimal.valueOf(95.00);
    }
}
