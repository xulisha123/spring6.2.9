package com.xushu.extensions.create.aware.messageSource;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DemoBean implements MessageSourceAware {


    @Override
    public void setMessageSource(MessageSource messageSource) {
		String message = messageSource.getMessage("test", null, new Locale("en_US"));
		System.out.println(message);
	}


}