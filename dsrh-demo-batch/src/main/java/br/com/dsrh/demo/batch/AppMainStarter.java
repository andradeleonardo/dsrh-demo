package br.com.dsrh.demo.batch;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import br.com.dsrh.demo.batch.config.SpringThreadPoolConfig;

public class AppMainStarter {
	
	private static final Log log = LogFactory.getLog(AppMainStarter.class);
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws SQLException {
			
		AbstractApplicationContext threadPoolcontext  = new AnnotationConfigApplicationContext(SpringThreadPoolConfig.class);
		
		ThreadPoolTaskExecutor execChecarVendas = (ThreadPoolTaskExecutor) threadPoolcontext.getBean("execChecarVendas");
		ThreadPoolTaskExecutor execProcessarVendas = (ThreadPoolTaskExecutor) threadPoolcontext.getBean("execProcessarVendas");
				
	  }	

}
