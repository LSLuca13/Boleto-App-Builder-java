package br.com.seubanco.boleto.builder;

import br.com.seubanco.boleto.model.Beneficiario;
import br.com.seubanco.boleto.model.Sacado;
import br.com.seubanco.boleto.model.Titulo;
import br.com.seubanco.boleto.util.CodigoDeBarrasUtil;
import br.com.seubanco.boleto.util.LinhaDigitavelUtil;

public class BancoDoBrasilBuilder implements BoletoBuilder {

    private Boleto boleto;
    private Beneficiario beneficiario;
    private Sacado sacado;
    private Titulo titulo;

    public BancoDoBrasilBuilder() {
        this.boleto = new Boleto();
    }

    @Override
    public void definirBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    @Override
    public void definirSacado(Sacado sacado) {
        this.sacado = sacado;
    }

    @Override
    public void definirTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    @Override
    public void montarCampoLivre() {
        // Exemplo fictício de montagem do campo livre p/ Banco do Brasil
        String campoLivre = String.format("%4s", beneficiario.getAgencia()).replace(" ", "0") +
                String.format("%8s", beneficiario.getConta()).replace(" ", "0") +
                String.format("%3s", beneficiario.getCarteira()).replace(" ", "0") +
                String.format("%10s", titulo.getNumeroDocumento()).replace(" ", "0");

        boleto.setCampoLivre(campoLivre);
    }

    @Override
    public void gerarCodigoDeBarras() {
        String codigo = CodigoDeBarrasUtil.gerarCodigoDeBarras(
                "001", // Banco do Brasil
                "9",   // Moeda: Real
                titulo,
                boleto.getCampoLivre()
        );
        boleto.setCodigoDeBarras(codigo);
    }

    @Override
    public void gerarLinhaDigitavel() {
        String linha = LinhaDigitavelUtil.gerarLinhaDigitavel(boleto.getCodigoDeBarras());
        boleto.setLinhaDigitavel(linha);
    }

    @Override
    public Boleto getBoleto() {
        return boleto;
    }
}
