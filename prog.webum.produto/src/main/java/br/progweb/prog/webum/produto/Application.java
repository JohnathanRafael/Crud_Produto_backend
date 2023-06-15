package br.progweb.prog.webum.produto;

import br.progweb.prog.webum.produto.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//Cria um metodo para ser testado assim que a aplicacao comeca a rodar
	//o @Bran CommandLineRunner cria o objeto
	// injecao de dependencia, o spring boot cria o objeto
	@Bean
	public CommandLineRunner commandLineRunner(ProdutoRepository produtoRepository){
		return args -> {


			/*Optional<Produto> produtoLocalizado = produtoRepository.findProdutoByprodutoNome(p1.getProdutoNome());
			if(produtoLocalizado.isPresent()){
				System.out.println("Nome já utilizado");
			} else{
				p1 = produtoRepository.save(p1);
			}*/
            /*try{
				produtoRepository.save(p1);
			}catch(Exception e)
			{
				System.out.println("teste"+e.getMessage());
				nome_duplicado = e.getMessage().contains(Produto.UK_LOJA_NOME_PRODUTO);
			}*/

			//produtoRepository.delete(p1);


		};
	}

}

/*
{
  "produtoNome": "Headset",
  "produtoMarca": "JBL",
  "produtoCategoria": "Fone de Ouvido",
  "produtoValorVenda": 199.25,
  "produtoValorCusto": 99.36
}
 */
