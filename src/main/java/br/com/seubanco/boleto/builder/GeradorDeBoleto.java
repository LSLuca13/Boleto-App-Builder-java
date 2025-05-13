package br.com.seubanco.boleto.builder;

import br.com.seubanco.boleto.model.Beneficiario;
import br.com.seubanco.boleto.model.Sacado;
import br.com.seubanco.boleto.model.Titulo;

public class GeradorDeBoleto {
    private final BoletoBuilder builder;

    public GeradorDeBoleto(BoletoBuilder builder) {
        this.builder = builder;
    }

    public Boleto gerar(Beneficiario beneficiario, Sacado sacado, Titulo titulo) {
        builder.definirBeneficiario(beneficiario);
        builder.definirSacado(sacado);
        builder.definirTitulo(titulo); // ← ESSENCIAL
        builder.montarCampoLivre();
        builder.gerarCodigoDeBarras();
        builder.gerarLinhaDigitavel();
        return builder.getBoleto();
    }
}
