package com.thymewizards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeWizardsApplicationConfiguration {

	// We instruct Thymeleaf to search for fragments in the svg directory using the .svg suffix (as opposed to the default .html suffix).
	@Bean
	public ITemplateResolver svgTemplateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:/templates/svg/");
		resolver.setSuffix(".svg");
		resolver.setTemplateMode("XML");
		return resolver;
	}

}
