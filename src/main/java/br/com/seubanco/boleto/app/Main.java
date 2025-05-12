package br.com.seubanco.boleto.app;

import br.com.seubanco.boleto.builder.*;
import br.com.seubanco.boleto.model.*;
import br.com.seubanco.boleto.pdf.BoletoPdfGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Endereço comum
        Endereco endereco = new Endereco(
                "Rua das Flores", "123", "Centro",
                "São Paulo", "SP", "01000-000"
        );

        // Dados comuns
        Sacado sacado = new Sacado(
                "João da Silva", "123.456.789-00", endereco
        );

        Titulo titulo = new Titulo(
                "1234567890", LocalDate.now().plusDays(10),
                new BigDecimal("150.75")
        );

        // Banco do Brasil
        Beneficiario beneficiarioBB = new Beneficiario(
                "Empresa BB", "00.000.000/0001-00", endereco,
                "1234", "12345678", "001"
        );
        GeradorDeBoleto geradorBB = new GeradorDeBoleto(new BancoDoBrasilBuilder());
        Boleto boletoBB = geradorBB.gerar(beneficiarioBB, sacado, titulo);

        System.out.println("=== Banco do Brasil ===");
        System.out.println("Código de Barras: " + boletoBB.getCodigoDeBarras());
        System.out.println("Linha Digitável:  " + boletoBB.getLinhaDigitavel());
        BoletoPdfGenerator.gerar(boletoBB, "boleto-bb.pdf");

        // Itaú
        Beneficiario beneficiarioItau = new Beneficiario(
                "Empresa Itau", "11.111.111/0001-11", endereco,
                "4321", "87654321", "109"
        );
        GeradorDeBoleto geradorItau = new GeradorDeBoleto(new ItauBuilder());
        Boleto boletoItau = geradorItau.gerar(beneficiarioItau, sacado, titulo);

        System.out.println("=== Banco Itaú ===");
        System.out.println("Código de Barras: " + boletoItau.getCodigoDeBarras());
        System.out.println("Linha Digitável:  " + boletoItau.getLinhaDigitavel());
        BoletoPdfGenerator.gerar(boletoItau, "boleto-itau.pdf");

        // Bradesco
        Beneficiario beneficiarioBradesco = new Beneficiario(
                "Empresa Bradesco", "22.222.222/0001-22", endereco,
                "5678", "1122334", "09"
        );
        GeradorDeBoleto geradorBradesco = new GeradorDeBoleto(new BradescoBuilder());
        Boleto boletoBradesco = geradorBradesco.gerar(beneficiarioBradesco, sacado, titulo);

        System.out.println("=== Banco Bradesco ===");
        System.out.println("Código de Barras: " + boletoBradesco.getCodigoDeBarras());
        System.out.println("Linha Digitável:  " + boletoBradesco.getLinhaDigitavel());
        BoletoPdfGenerator.gerar(boletoBradesco, "boleto-bradesco.pdf");
    }
}
