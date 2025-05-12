package br.com.seubanco.boleto.model;

public class Sacado {
    private String nome;
    private String documento; // CPF ou CNPJ
    private Endereco endereco;

    public Sacado(String nome, String documento, Endereco endereco) {
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
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
}
