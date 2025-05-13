package br.com.seubanco.boleto.builder;

import br.com.seubanco.boleto.model.Beneficiario;
import br.com.seubanco.boleto.model.Sacado;
import br.com.seubanco.boleto.model.Titulo;
import br.com.seubanco.boleto.util.CodigoDeBarrasUtil;
import br.com.seubanco.boleto.util.LinhaDigitavelUtil;

public class ItauBuilder implements BoletoBuilder {

    private Boleto boleto;

    public ItauBuilder() {
        this.boleto = new Boleto();
    }

    @Override
    public void definirBeneficiario(Beneficiario beneficiario) {
        boleto.setBeneficiario(beneficiario);
    }

    @Override
    public void definirSacado(Sacado sacado) {
        boleto.setSacado(sacado);
    }

    @Override
    public void definirTitulo(Titulo titulo) {
        boleto.setTitulo(titulo);
    }

    @Override
    public void montarCampoLivre() {
        Beneficiario b = boleto.getBeneficiario();
        Titulo t = boleto.getTitulo();

        String campoLivre = String.format("%3s", b.getCarteira()).replace(" ", "0") +
                String.format("%8s", t.getNumeroDocumento()).replace(" ", "0") +
                String.format("%5s", b.getAgencia()).replace(" ", "0") +
                String.format("%8s", b.getConta()).replace(" ", "0") +
                "0";

        boleto.setCampoLivre(campoLivre);
    }

    @Override
    public void gerarCodigoDeBarras() {
        boleto.setCodigoDeBarras(CodigoDeBarrasUtil.gerarCodigoDeBarras(boleto));
    }

    @Override
    public void gerarLinhaDigitavel() {
        boleto.setLinhaDigitavel(LinhaDigitavelUtil.gerarLinhaDigitavel(boleto.getCodigoDeBarras()));

    }

    @Override
    public Boleto getBoleto() {
        return boleto;
    }
}
