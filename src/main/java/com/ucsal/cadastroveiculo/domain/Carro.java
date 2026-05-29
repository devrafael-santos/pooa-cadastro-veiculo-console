package com.ucsal.cadastroveiculo.domain;

public class Carro extends Veiculo {
    public Carro(String placa, String proprietario, String marca, String modelo, int ano) {
        super(placa, proprietario, marca, modelo, ano);
    }

    @Override
    public String getTipo() {
        return "Carro";
    }
}
