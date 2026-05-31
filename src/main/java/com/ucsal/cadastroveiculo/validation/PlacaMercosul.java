package com.ucsal.cadastroveiculo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.RECORD_COMPONENT)
public @interface PlacaMercosul {
    String mensagem() default "Placa deve estar no formato ABC1D23.";
}
