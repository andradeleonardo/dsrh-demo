package br.com.dsrh.demo.batch.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.dsrh.demo.batch.model.Venda;
import br.com.dsrh.demo.batch.service.VendaService;

@Controller("tratarVendaTask")
@Scope("prototype")
public class TratarVendaTask implements Runnable {
	
	private static final Log log = LogFactory.getLog(TratarVendaTask.class);
	
	private VendaService vendaService;
		
	private Venda currentProcess;
			
	public TratarVendaTask(Venda currentProcess, VendaService vendaService) {
		super();
		this.currentProcess = currentProcess;
		this.vendaService = vendaService;
	}
	
	public void run() {
		try {
			vendaService.tratarVenda(currentProcess);			
		} 
		catch (Exception e) {
			log.error(e);	
		} 	
	}	
}
