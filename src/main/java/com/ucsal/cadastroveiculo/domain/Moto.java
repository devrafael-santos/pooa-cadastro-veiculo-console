package com.ucsal.cadastroveiculo.domain;

public class Moto extends Veiculo {
    public Moto(String placa, String proprietario, String marca, String modelo, int ano) {
        super(placa, proprietario, marca, modelo, ano);
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
