package br.com.seubanco.boleto.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Titulo {
    private String numeroDocumento;
    private LocalDate dataVencimento;
    private BigDecimal valor;

    public Titulo(String numeroDocumento, LocalDate dataVencimento, BigDecimal valor) {
        this.numeroDocumento = numeroDocumento;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getValorFormatado() {
        return String.format("%.2f", valor).replace(".", "").replace(",", "");
    }
}
