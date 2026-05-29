package com.ucsal.cadastroveiculo.domain;

public abstract class Veiculo {
    private final String placa;
    private final String proprietario;
    private final String marca;
    private final String modelo;
    private final int ano;

    protected Veiculo(String placa, String proprietario, String marca, String modelo, int ano) {
        this.placa = placa;
        this.proprietario = proprietario;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public String getProprietario() {
        return proprietario;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public abstract String getTipo();

    public String resumo() {
        return "%s | %s | %s %s | ano %d | proprietario: %s"
                .formatted(getTipo(), placa, marca, modelo, ano, proprietario);
    }
}
