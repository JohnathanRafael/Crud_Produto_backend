package br.progweb.prog.webum.produto.service.impl;

import br.progweb.prog.api.exception.BusinessException;
import br.progweb.prog.webum.produto.exception.SistemaMessageCode;
import br.progweb.prog.webum.produto.model.Produto;
import br.progweb.prog.webum.produto.repository.ProdutoRepository;
import br.progweb.prog.webum.produto.service.ProdutoService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto incluir(Produto produto) {
        this.validarCamposObrigatorios(produto);
        this.prepararParaIncluir(produto);
        Produto alunoIncluido = this.gravarDados(produto);
        return alunoIncluido;
    }

    private void prepararParaIncluir(Produto produto) {
        produto.setStatusProduto("ATIVO");
        produto.setProdutolancamento(LocalDate.now());
    }

    private Produto gravarDados(Produto produto) {
        return produtoRepository.save(produto);
    }

    @SneakyThrows
    private void validarCamposObrigatorios(Produto produto) {
        if(Objects.isNull(produto)){
            throw new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
        }

        List<String> erros = new ArrayList<>();

        if(Strings.isEmpty(produto.getProdutoNome())){
            erros.add("Nome do produto é obrigatório");
        }

        if(produto.getProdutoValorVenda() == null) {
            erros.add("Valor de venda é obrigatório");
        }

        if(produto.getProdutoValorCusto() == null) {
            erros.add("Valor de custo é obrigatório");
        }

        String validacaoNome = validarNomeExistente(produto);
        if(Strings.isNotEmpty(validacaoNome)){
            erros.add(validacaoNome);
        }

        if(!erros.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
        }

    }

    private String validarNomeExistente(Produto produto) {
        Optional<Produto> nomeDoProduto = produtoRepository.findProdutoByprodutoNome(produto.getProdutoNome());
        String retorno = "";
        if (nomeDoProduto.isPresent()){
            retorno = "Nome de produto já utilizado no sistema";
        }
        return retorno;
    }
    @Override
    public Produto alterar(Produto produto, Long codigo) {
        this.validarCamposObrigatorios(produto);
        this.validarNomeExistente(produto);

        Produto produtoBD = recuperarProduto(codigo);

        produto.setStatusProduto(produtoBD.getStatusProduto());
        produto.setProdutolancamento(produtoBD.getProdutolancamento());
        produto.setProdutoCodigo(codigo);

        //TODO verificar pois nao é possivel alterar deixando o nome
        Produto save = produtoRepository.save(produto);

        return save;
    }

    private Produto recuperarProduto(Long codigo) {
        Produto produtoBD = produtoRepository.findById(codigo).orElseThrow( () -> new BusinessException(SistemaMessageCode.ERRO_REGISTRO_NAO_ENCONTRADO));
        return produtoBD;
    }

    @Override
    public Produto excluir(Long produtoCodigo) {
        Produto produtoExcluir = this.recuperarProduto(produtoCodigo);
        this.produtoRepository.delete(produtoExcluir);
        return produtoExcluir;
    }

    @Override
    public Produto obterProdutopeloCodigo(Long codigo) {
        return this.recuperarProduto(codigo);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public List<Produto> localizar(Produto produto) {
        return null;
    }
}
