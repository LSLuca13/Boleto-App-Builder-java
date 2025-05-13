
package br.com.seubanco.boleto.util;

import br.com.seubanco.boleto.builder.Boleto;
import br.com.seubanco.boleto.model.Titulo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CodigoDeBarrasUtil {

    public static String gerarCodigoDeBarras(Boleto boleto) {
        if (boleto.getTitulo() == null) {
            throw new IllegalArgumentException("Titulo do boleto não pode ser nulo");
        }

        String banco = boleto.getCampoLivre().substring(0, 3); // usa os 3 primeiros digitos do campo livre
        String moeda = "9"; // Real
        String campoLivre = boleto.getCampoLivre();
        String fator = gerarFatorVencimento(boleto.getTitulo().getDataVencimento());
        String valor = formatarValor(boleto.getTitulo().getValor());

        String semDV = banco + moeda + fator + valor + campoLivre;
        String dv = calcularDV(semDV);

        return banco + moeda + dv + fator + valor + campoLivre;
    }

    private static String calcularDV(String codigo) {
        int soma = 0;
        int peso = 2;

        for (int i = codigo.length() - 1; i >= 0; i--) {
            int num = Character.getNumericValue(codigo.charAt(i));
            soma += num * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }

        int resto = soma % 11;
        int dv = 11 - resto;

        if (dv == 0 || dv == 10 || dv == 11) {
            return "1";
        }

        return String.valueOf(dv);
    }

    public static String formatarValor(BigDecimal valor) {
        BigDecimal multiplicado = valor.multiply(new BigDecimal(100));
        return String.format("%010d", multiplicado.longValue());
    }

    public static String gerarFatorVencimento(LocalDate vencimento) {
        LocalDate base = LocalDate.of(1997, 10, 7);
        long dias = ChronoUnit.DAYS.between(base, vencimento);
        return String.format("%04d", dias);
    }
}
