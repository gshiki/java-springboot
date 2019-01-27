package br.shiki.java.spring.boot.template.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


/**
 * 
 * @author Shiki
 *
 */
@Configuration
public class ResourceBundleConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasenames(
				"classpath:/messages/messages",
				"classpath:/messages/exceptions"
			);
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();

		bean.setValidationMessageSource(messageSource());

		return bean;
	}
	
}
