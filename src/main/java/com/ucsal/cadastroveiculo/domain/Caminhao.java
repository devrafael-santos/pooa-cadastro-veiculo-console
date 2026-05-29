package com.ucsal.cadastroveiculo.domain;

public class Caminhao extends Veiculo {
    public Caminhao(String placa, String proprietario, String marca, String modelo, int ano) {
        super(placa, proprietario, marca, modelo, ano);
    }

    @Override
    public String getTipo() {
        return "Caminhao";
    }
}
