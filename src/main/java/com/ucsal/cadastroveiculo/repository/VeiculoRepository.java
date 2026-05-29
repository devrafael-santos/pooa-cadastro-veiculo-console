package com.ucsal.cadastroveiculo.repository;

import com.ucsal.cadastroveiculo.domain.Veiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepository {
    private final List<Veiculo> banco = new ArrayList<>();

    public void salvar(Veiculo veiculo) {
        banco.add(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return new ArrayList<>(banco);
    }

    public Optional<Veiculo> buscarPorPlaca(String placa) {
        return banco.stream()
                .filter(veiculo -> veiculo.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
    }

    public boolean existePlaca(String placa) {
        return buscarPorPlaca(placa).isPresent();
    }
}
