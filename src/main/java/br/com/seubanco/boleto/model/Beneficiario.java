package br.com.seubanco.boleto.model;

public class Beneficiario {
    private String nome;
    private String documento; // CPF ou CNPJ
    private Endereco endereco;

    private String agencia;
    private String conta;
    private String carteira;

    public Beneficiario(String nome, String documento, Endereco endereco, String agencia, String conta, String carteira) {
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.agencia = agencia;
        this.conta = conta;
        this.carteira = carteira;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public String getCarteira() {
        return carteira;
    }
}
