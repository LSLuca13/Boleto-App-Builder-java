package br.com.seubanco.boleto.util;

public class Modulo11 {

    public static int calcular(String numero) {
        int soma = 0;
        int peso = 2;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            soma += digito * peso;
            peso++;
            if (peso > 9) peso = 2;
        }

        int resto = soma % 11;
        int resultado = 11 - resto;

        if (resultado == 0 || resultado == 10 || resultado == 11) {
            return 1;
        }

        return resultado;
    }
}
