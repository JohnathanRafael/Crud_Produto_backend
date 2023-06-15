package br.progweb.prog.webum.produto.mapper;


import br.progweb.prog.webum.produto.dto.ProdutoDTO;
import br.progweb.prog.webum.produto.dto.ProdutoDadosAlterarDTO;
import br.progweb.prog.webum.produto.dto.ProdutoListaDTO;
import br.progweb.prog.webum.produto.model.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {


    // listar
    public ProdutoListaDTO toComponenteListaDTO(Produto produto);
    public List<ProdutoListaDTO> toListaDTO(List<Produto> produtos);

    //incluir
    public ProdutoDadosAlterarDTO toProdutoIncluirDTO(Produto produto);
    public Produto toModelo(ProdutoDadosAlterarDTO produto);

    //alterar
    public ProdutoDTO toProdutoDTO(Produto produto);

    public Produto toModelo(ProdutoDTO produto);
}

