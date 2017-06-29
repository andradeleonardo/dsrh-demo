package br.com.dsrh.demo.batch.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "br.com.dsrh.demo.batch")
public class SpringThreadPoolConfig {

	private static final Log log = LogFactory.getLog(SpringThreadPoolConfig.class);

	@Bean(name = "execChecarVendas")
	public ThreadPoolTaskExecutor taskExecutorChecarVendas() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(100);
		executor.initialize();
		return executor;
	}
	
	@Bean(name = "execProcessarVendas")
	public ThreadPoolTaskExecutor taskExecutorProcessarVendas() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(100);
		executor.initialize();
		return executor;
	}
	
}
