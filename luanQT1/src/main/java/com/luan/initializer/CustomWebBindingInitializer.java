package com.luan.initializer;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class CustomWebBindingInitializer implements WebBindingInitializer{

	@Override
	public void initBinder(WebDataBinder arg0, WebRequest arg1) {
		arg0.addCustomFormatter(new DateFormatter("yyy-MM-dd HH:mm:ss"));
	}

}
