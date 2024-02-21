package com.rhpro.utils;

import org.modelmapper.AbstractConverter;

import java.math.BigDecimal;

public class ConversaoSalario extends AbstractConverter<BigDecimal, String> {

    @Override
    protected String convert(BigDecimal bigDecimal) {

        String salario = bigDecimal.toString();

        String salarioVirgula = salario.replaceAll(".", ",");

        return "R$ " + salarioVirgula;
    }

}
