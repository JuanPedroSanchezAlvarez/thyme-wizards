package com.thymewizards.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeWizardsApplicationConfiguration {

    /**
     * Instruct Thymeleaf to search for fragments in the svg directory using the .svg suffix (as opposed to the default .html suffix)
     */
	@Bean
	public ITemplateResolver svgTemplateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:/templates/svg/");
		resolver.setSuffix(".svg");
		resolver.setTemplateMode("XML");
		return resolver;
	}

    /**
     * We use the {…} annotation to be able to translate the error messages from our custom validation annotations. To be able to read those translations from messages.properties, we need to configure it this way.
     */
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

    /**
     * There is no PasswordEncoder created by Spring Boot by default, so we need to create such a bean ourselves.
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
