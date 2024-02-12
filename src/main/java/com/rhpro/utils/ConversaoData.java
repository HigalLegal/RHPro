package com.rhpro.utils;

import org.modelmapper.AbstractConverter;

import java.time.LocalDate;

public class ConversaoData extends AbstractConverter<LocalDate, String> {

    @Override
    protected String convert(LocalDate date) {
        int dia = date.getDayOfMonth();
        String mes = extrairMes(date);
        int ano = date.getYear();

        return dia + " de " + mes + " de " + ano;
    }

    private String extrairMes(LocalDate data) {
        int numeroMes = data.getMonthValue();

        String mesString = "janeiro";

        switch (numeroMes) {
            case 2 -> mesString = "fevereiro";
            case 3 -> mesString = "março";
            case 4 -> mesString = "abril";
            case 5 -> mesString = "maio";
            case 6 -> mesString = "junho";
            case 7 -> mesString = "julho";
            case 8 -> mesString = "agosto";
            case 9 -> mesString = "setembro";
            case 10 -> mesString = "outubro";
            case 11 -> mesString = "novembro";
            case 12 -> mesString = "dezembro";
        }

        return mesString;
    }

}
