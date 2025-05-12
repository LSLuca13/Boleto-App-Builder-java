package br.com.seubanco.boleto.util;

import br.com.seubanco.boleto.model.Titulo;

import java.time.format.DateTimeFormatter;

public class CodigoDeBarrasUtil {

    public static String gerarCodigoDeBarras(String banco, String moeda, Titulo titulo, String campoLivre) {
        String fatorVencimento = calcularFatorVencimento(titulo);
        String valorFormatado = formatarValor(titulo);

        // Parte sem o dígito verificador (posição 5)
        String parcial = banco + moeda + fatorVencimento + valorFormatado + campoLivre;

        // Calcula o dígito com Módulo 11 e insere na posição 5
        int dv = Modulo11.calcular(parcial);
        return banco + moeda + dv + fatorVencimento + valorFormatado + campoLivre;
    }

    private static String calcularFatorVencimento(Titulo titulo) {
        // Base: 07/10/1997 (data base da Febraban)
        java.time.LocalDate dataBase = java.time.LocalDate.of(1997, 10, 7);
        long dias = java.time.temporal.ChronoUnit.DAYS.between(dataBase, titulo.getDataVencimento());
        return String.format("%04d", dias);
    }

    private static String formatarValor(Titulo titulo) {
        // 10 dígitos, sem vírgula
        return String.format("%010d", titulo.getValor().multiply(new java.math.BigDecimal(100)).longValue());

    }
}
