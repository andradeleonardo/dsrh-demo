package br.com.dsrh.demo.web.api.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component("messageResource")
public class MessageResource extends ResourceBundleMessageSource {
	
	private static final Log log = LogFactory.getLog(MessageResource.class);
	
	private MessageSource messages;

	public MessageResource() {
		super.setBasename("messages");
	}
	
}
