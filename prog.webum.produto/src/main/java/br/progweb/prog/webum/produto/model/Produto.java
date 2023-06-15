package br.progweb.prog.webum.produto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "loja_produto",
        uniqueConstraints = {
                @UniqueConstraint(name= Produto.UK_LOJA_NOME_PRODUTO, columnNames = "nome_produto")
        }
)
public class Produto {
    public static final String UK_LOJA_NOME_PRODUTO = "uk_loja_nome_produto";
    @SequenceGenerator(
            name="produto_sequence",
            sequenceName = "produto_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "produto_sequence"
    )
    @Id
    @Column(name = "codigo")
    private Long produtoCodigo;
    @Column(name = "nome_produto", length = 200, nullable = false)
    private String produtoNome;
    @Column(name = "marca", length = 50)
    private String produtoMarca;
    @Column(name = "categoria", length = 50)
    private String produtoCategoria;
    @Column(name = "valor_de_venda", nullable = false)
    private BigDecimal produtoValorVenda;
    @Column(name = "valor_de_custo", nullable = false)
    private BigDecimal produtoValorCusto;
    @Column(name = "data_lancamento")
    private LocalDate produtolancamento;
    @Column(name = "status_produto")
    private String statusProduto;
}
