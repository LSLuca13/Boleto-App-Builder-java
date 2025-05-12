package br.com.seubanco.boleto.builder;

import br.com.seubanco.boleto.model.*;
import br.com.seubanco.boleto.util.*;

public class ItauBuilder implements BoletoBuilder {

    private Boleto boleto;
    private Beneficiario beneficiario;
    private Sacado sacado;
    private Titulo titulo;

    public ItauBuilder() {
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
        // Exemplo simplificado para Itaú: carteira (3) + nosso número (8) + agência (4) + conta (5) + zero
        String campoLivre = String.format("%3s", beneficiario.getCarteira()).replace(" ", "0") +
                String.format("%8s", titulo.getNumeroDocumento()).replace(" ", "0") +
                String.format("%4s", beneficiario.getAgencia()).replace(" ", "0") +
                String.format("%5s", beneficiario.getConta()).replace(" ", "0") +
                "0";

        boleto.setCampoLivre(campoLivre);
    }

    @Override
    public void gerarCodigoDeBarras() {
        String codigo = CodigoDeBarrasUtil.gerarCodigoDeBarras(
                "341", // Itaú
                "9",
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
