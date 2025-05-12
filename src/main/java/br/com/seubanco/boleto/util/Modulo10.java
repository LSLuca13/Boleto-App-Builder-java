package br.com.seubanco.boleto.util;

public class Modulo10 {

    public static int calcular(String numero) {
        int soma = 0;
        int peso = 2;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            int multiplicacao = digito * peso;

            if (multiplicacao > 9) {
                multiplicacao = (multiplicacao / 10) + (multiplicacao % 10); // soma os dígitos
            }

            soma += multiplicacao;
            peso = (peso == 2) ? 1 : 2;
        }

        int resto = soma % 10;
        int resultado = (10 - resto);
        return (resultado == 10) ? 0 : resultado;
    }
}
