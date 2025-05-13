package br.com.seubanco.boleto.builder;

import br.com.seubanco.boleto.model.Beneficiario;
import br.com.seubanco.boleto.model.Sacado;
import br.com.seubanco.boleto.model.Titulo;

public class Boleto {

    private Beneficiario beneficiario;
    private Sacado sacado;
    private Titulo titulo;
    private String codigoDeBarras;
    private String linhaDigitavel;
    private String campoLivre;

    // Getters
    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public Sacado getSacado() {
        return sacado;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public String getCampoLivre() {
        return campoLivre;
    }

    // Setters
    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public void setSacado(Sacado sacado) {
        this.sacado = sacado;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public void setCampoLivre(String campoLivre) {
        this.campoLivre = campoLivre;
    }
}
