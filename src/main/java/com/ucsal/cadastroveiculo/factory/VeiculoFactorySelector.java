package com.ucsal.cadastroveiculo.factory;

public class VeiculoFactorySelector {
    public VeiculoFactory buscarPorTipo(String tipo) {
        if (tipo.equalsIgnoreCase("carro")) {
            return new CarroFactory();
        }

        if (tipo.equalsIgnoreCase("moto")) {
            return new MotoFactory();
        }

        if (tipo.equalsIgnoreCase("caminhao")) {
            return new CaminhaoFactory();
        }

        throw new IllegalArgumentException("Tipo de veiculo nao suportado: " + tipo);
    }
}
