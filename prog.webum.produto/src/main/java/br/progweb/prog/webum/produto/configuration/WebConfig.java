/*
 * WebConfig.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.progweb.prog.webum.produto.configuration;

import br.progweb.prog.api.config.ApiWebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Classe de configuração referente aos recursos Web MVC da aplicação.
 *
 * @author UEG
 */
@Configuration
public class WebConfig extends ApiWebConfig {

    /**
     * Retorna a instância {@link MethodValidationPostProcessor}.
     *
     * @return
     */
	/*@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}*/
}

