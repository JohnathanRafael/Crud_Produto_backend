package br.progweb.prog.webum.produto.controller;

import br.progweb.prog.api.exception.MessageResponse;
import br.progweb.prog.webum.produto.dto.ProdutoDTO;
import br.progweb.prog.webum.produto.dto.ProdutoDadosAlterarDTO;
import br.progweb.prog.webum.produto.dto.ProdutoListaDTO;
import br.progweb.prog.webum.produto.mapper.ProdutoMapper;
import br.progweb.prog.webum.produto.model.Produto;
import br.progweb.prog.webum.produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@Api(tags = "Produto API")*/
@RestController
@RequestMapping(path = "${app.api.base}/produto")
public class ProdutoController {

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @Operation(description = "Listagem geral de produtos", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listagem de produtos",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ProdutoListaDTO.class)
                            )
                    )
            )
    })
    public ResponseEntity<List<ProdutoListaDTO>> listAll(){
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(this.produtoMapper.toListaDTO(produtos));
    }

    @PostMapping
    @Operation(description = "Método utilizado para realizar a inclusao de um produto", responses = {
            @ApiResponse(responseCode = "200", description = "Produto Incluído",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoDTO.class) )),
            @ApiResponse(responseCode = "400", description = "Campos Obrigatórios não informados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class)
                    )
            )
    })
    public ResponseEntity<ProdutoDTO> incluir(@RequestBody ProdutoDadosAlterarDTO produto){
        Produto produtoIncluir = this.produtoMapper.toModelo(produto);
        produtoIncluir = this.produtoService.incluir(produtoIncluir);
        ProdutoDTO retorno = this.produtoMapper.toProdutoDTO(produtoIncluir);
        return ResponseEntity.ok(retorno);
    }

    @PutMapping(path = "/{produtoCodigo}")
    @Operation(description = "Método utilizado para realizar a alteracao dos dados de um produto", responses = {
            @ApiResponse(responseCode = "200", description = "Produto Alterado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "404", description = "Produto Não encontrado",
                    content = @Content(mediaType = "application/json"))})
    public ResponseEntity<ProdutoDTO> alterar(@RequestBody() ProdutoDadosAlterarDTO produto, @PathVariable(name = "produtoCodigo") Long codigo ){
        Produto produtoP = produtoMapper.toModelo(produto);
        Produto alterar = produtoService.alterar(produtoP, codigo);
        return ResponseEntity.ok(produtoMapper.toProdutoDTO(alterar));
    }

    @DeleteMapping(path = "/{produtoCodigo}")
    @Operation(description = "Método utilizado para realizar a exclusao de um produto", responses = {
            @ApiResponse(responseCode = "200", description = "Produto Removido",
                        content = @Content(mediaType = "application/json"))})
    public ResponseEntity<ProdutoDTO> remover(@PathVariable(name = "produtoCodigo") Long codigo){
        Produto produtoExcluido = this.produtoService.excluir(codigo);

        return ResponseEntity.ok(produtoMapper.toProdutoDTO(produtoExcluido));
    }

    @GetMapping(path = "/{produtoCodigo}")
    @Operation(description = "Método utilizado para obter todos os dados de um produto por codigo", responses = {
            @ApiResponse(responseCode = "200", description = "Produto informado no ID",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProdutoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto Não encontrado",
                        content = @Content(mediaType = "application/json"))})
    public ResponseEntity<ProdutoDTO> ObterPorCodigo(@PathVariable(name = "produtoCodigo") Long codigo){
        Produto produto = this.produtoService.obterProdutopeloCodigo(codigo);
        return ResponseEntity.ok(this.produtoMapper.toProdutoDTO(produto));
    }




}
