package br.com.dsrh.demo.batch.task;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.service.VendaService;

@Controller("processarVendasVendaTask")
@Scope("prototype")
public class ProcessarVendasTask implements Runnable {
	
	private static final Log log = LogFactory.getLog(ProcessarVendasTask.class);
	
	private VendaService vendaService;
		
	private List<Processamento> processamentos;
			
	public ProcessarVendasTask(List<Processamento> processamentos, VendaService vendaService) {
		super();
		this.processamentos = processamentos;
		this.vendaService = vendaService;
	}
	
	public void run() {
		try {
			vendaService.tratarVenda(processamentos);			
		} 
		catch (Exception e) {
			log.error(e);	
		} 	
	}	
}
