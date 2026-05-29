package com.ucsal.cadastroveiculo.service;

import com.ucsal.cadastroveiculo.domain.Veiculo;

import java.math.BigDecimal;

public interface CalculadoraTaxa {
    BigDecimal calcular(Veiculo veiculo);
}
