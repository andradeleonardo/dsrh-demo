package br.com.dsrh.demo.batch.executor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.service.VendaService;
import br.com.dsrh.demo.batch.task.ProcessarVendasTask;

@Component("processarVendasExecutor")
public class ProcessarVendasExecutor {
	
	private static final Log log = LogFactory.getLog(ProcessarVendasExecutor.class);
	
	@Autowired
	@Qualifier("execProcessarVendas")
	ThreadPoolTaskExecutor poolTaskExecutor;
	
	@Autowired
	VendaService vendaService;
	
	@Scheduled(fixedRate=10000)
	public void listenner() throws Exception {
		try {
			List<Processamento> processamentos = vendaService.findProcessamentosPendentes();
			if (processamentos!=null && !processamentos.isEmpty()) {
				poolTaskExecutor.execute(new ProcessarVendasTask(processamentos, vendaService));
			}			
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
