package br.com.seubanco.boleto.util;

public class LinhaDigitavelUtil {

    public static String gerarLinhaDigitavel(String codigoDeBarras) {
        // Campos do código de barras
        String banco = codigoDeBarras.substring(0, 3);
        String moeda = codigoDeBarras.substring(3, 4);
        String campoLivre = codigoDeBarras.substring(19); // 25 dígitos
        String fatorVencimento = codigoDeBarras.substring(5, 9);
        String valor = codigoDeBarras.substring(9, 19);
        String dvGeral = codigoDeBarras.substring(4, 5);

        // Campo 1: banco + moeda + 5 primeiros do campo livre
        String campo1 = banco + moeda + campoLivre.substring(0, 5);
        int dv1 = Modulo10.calcular(campo1);
        campo1 = campo1.substring(0, 5) + "." + campo1.substring(5) + dv1;

        // Campo 2: posições 6 a 15 do campo livre
        String campo2 = campoLivre.substring(5, 15);
        int dv2 = Modulo10.calcular(campo2);
        campo2 = campo2.substring(0, 5) + "." + campo2.substring(5) + dv2;

        // Campo 3: posições 16 a 25 do campo livre
        String campo3 = campoLivre.substring(15, 25);
        int dv3 = Modulo10.calcular(campo3);
        campo3 = campo3.substring(0, 5) + "." + campo3.substring(5) + dv3;

        // Campo 4: dígito verificador geral (já calculado via Modulo 11)
        String campo4 = dvGeral;

        // Campo 5: fator de vencimento + valor
        String campo5 = fatorVencimento + valor;

        return campo1 + " " + campo2 + " " + campo3 + " " + campo4 + " " + campo5;
    }
}
