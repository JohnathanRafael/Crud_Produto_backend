package br.progweb.prog.webum.produto.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public @Data class ProdutoDTO {
    private Long produtoCodigo;
    private String produtoNome;
    private String produtoMarca;
    private String produtoCategoria;
    private BigDecimal produtoValorVenda;
    private BigDecimal produtoValorCusto;
    private LocalDate produtolancamento;
    private String statusProduto;
}

