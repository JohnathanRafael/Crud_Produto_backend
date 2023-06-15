package br.progweb.prog.webum.produto.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public @Data class ProdutoListaDTO {
    private Long produtoCodigo;
    private String produtoNome;
    private String produtoMarca;
    private LocalDate produtolancamento;
    private BigDecimal produtoValorVenda;
    private BigDecimal produtoValorCusto;
    private String statusProduto;
}
