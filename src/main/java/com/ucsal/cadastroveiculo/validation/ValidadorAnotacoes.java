package com.ucsal.cadastroveiculo.validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;

public class ValidadorAnotacoes {
    private static final String FORMATO_PLACA_MERCOSUL = "[A-Z]{3}[0-9][A-Z][0-9]{2}";

    public void validar(Object objeto) {
        if (objeto == null) {
            throw new IllegalArgumentException("Objeto para validação não pode ser nulo.");
        }

        Class<?> classe = objeto.getClass();

        if (!classe.isRecord()) {
            throw new IllegalArgumentException("Validação por anotações exige um record.");
        }

        for (RecordComponent campo : classe.getRecordComponents()) {
            Object valor = acessarValor(objeto, campo);
            validarObrigatorio(campo, valor);
            validarPlacaMercosul(campo, valor);
        }
    }

    private Object acessarValor(Object objeto, RecordComponent campo) {
        try {
            return campo.getAccessor().invoke(objeto);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new IllegalStateException("Não foi possível acessar o campo: " + campo.getName(), exception);
        }
    }

    private void validarObrigatorio(RecordComponent campo, Object valor) {
        Obrigatorio obrigatorio = campo.getAnnotation(Obrigatorio.class);

        if (obrigatorio == null) {
            return;
        }

        if (valor == null || valor.toString().isBlank()) {
            throw new IllegalArgumentException(obrigatorio.mensagem());
        }
    }

    private void validarPlacaMercosul(RecordComponent campo, Object valor) {
        PlacaMercosul placaMercosul = campo.getAnnotation(PlacaMercosul.class);

        if (placaMercosul == null) {
            return;
        }

        if (valor == null || !valor.toString().matches(FORMATO_PLACA_MERCOSUL)) {
            throw new IllegalArgumentException(placaMercosul.mensagem());
        }
    }
}
