package br.progweb.prog.webum.produto.service;
import br.progweb.prog.webum.produto.model.Produto;

import java.util.List;

public interface ProdutoService {
    public Produto incluir (Produto produto);
    public Produto alterar (Produto produto, Long codigo);
    public Produto excluir (Long produtoCodigo);
    public Produto obterProdutopeloCodigo(Long codigo);
    public List<Produto> listarTodos();
    public List<Produto> localizar(Produto produto);
}
