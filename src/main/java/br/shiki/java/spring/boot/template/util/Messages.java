package br.shiki.java.spring.boot.template.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Shiki
 *
 */
@Component
public class Messages {
	
	@Autowired
	private MessageSource messageSource;
	
	private MessageSourceAccessor accessor;
	
	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource);
	}
	
	public String get(String key) {
        return accessor.getMessage(key);
    }
    
    public String get(String key, Object... arguments) {
        return accessor.getMessage(key, arguments);
    }

}
