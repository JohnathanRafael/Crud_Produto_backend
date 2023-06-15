package br.progweb.prog.webum.produto.repository;

import br.progweb.prog.webum.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findProdutoByprodutoNome(String nome);

    @Query(value = "select count(p) from Produto p where p.produtoNome = :Nome ")
    Integer countUtilizacaoNome(String Nome);
}
