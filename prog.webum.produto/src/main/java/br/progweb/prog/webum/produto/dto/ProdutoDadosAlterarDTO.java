package br.progweb.prog.webum.produto.dto;

import lombok.Data;

import java.math.BigDecimal;

public @Data class ProdutoDadosAlterarDTO {
    private String produtoNome;
    private String produtoMarca;
    private String produtoCategoria;
    private BigDecimal produtoValorVenda;
    private BigDecimal produtoValorCusto;
}

