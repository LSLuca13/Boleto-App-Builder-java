package br.com.seubanco.boleto.builder;

public class Boleto {
    private String codigoDeBarras;
    private String linhaDigitavel;
    private String campoLivre;

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public void setCampoLivre(String campoLivre) {
        this.campoLivre = campoLivre;
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
}
