package br.com.dsrh.demo.batch.executor;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import br.com.dsrh.demo.batch.model.Venda;
import br.com.dsrh.demo.batch.service.VendaService;
import br.com.dsrh.demo.batch.task.TratarVendaTask;

@Component("checarVendasExecutor")
public class ChecarVendasExecutor {
	
	private static final Log log = LogFactory.getLog(ChecarVendasExecutor.class);
	
	@Autowired
	@Qualifier("execChecarVendas")
	ThreadPoolTaskExecutor poolTaskExecutor;
	
	@Autowired
	VendaService vendaService;
	
	@Scheduled(fixedRate=10000)
	public void listenner() throws Exception {
		try {
			List<Venda> vendas = vendaService.findVendas();
			if (vendas!=null && !vendas.isEmpty()) {
				for (Venda venda : vendas) {
					poolTaskExecutor.execute(new TratarVendaTask(venda, vendaService));
				}
			}			
		}
		catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
