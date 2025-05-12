package br.com.seubanco.boleto.builder;

import br.com.seubanco.boleto.model.Beneficiario;
import br.com.seubanco.boleto.model.Sacado;
import br.com.seubanco.boleto.model.Titulo;

public interface BoletoBuilder {
    void definirBeneficiario(Beneficiario beneficiario);
    void definirSacado(Sacado sacado);
    void definirTitulo(Titulo titulo);
    void montarCampoLivre();
    void gerarCodigoDeBarras();
    void gerarLinhaDigitavel();
    Boleto getBoleto();
}
