package br.com.dsrh.demo.batch.service.impl;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dsrh.demo.batch.dao.VendaDAO;
import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;
import br.com.dsrh.demo.batch.service.VendaService;
import br.com.dsrh.demo.batch.util.AppConstants;

@Service("vendaService")
public class VendaServiceImpl implements VendaService {

	private static final Log log = LogFactory.getLog(VendaServiceImpl.class);
	
	@Autowired
	private VendaDAO vendaDAO;
		
	public List<Venda> findVendas() {
		return vendaDAO.findVendas();
	}
	
	public void tratarVenda(Venda venda) {
		
		// Incluindo na fila de processamento.
		Processamento processamento = new Processamento();
		processamento.setId(venda.getId());
		processamento.setData(new Date());
		processamento.setLoja(venda.getLoja());
		processamento.setPdv(venda.getPdv());
		processamento.setStatus(AppConstants.STATUS_PENDENTE);
		
		vendaDAO.insertProcessamento(processamento);
		
		// Atualizando status para OK
		vendaDAO.updateStatusVenda(venda.getId(), AppConstants.STATUS_OK);
		
	}
	
	public List<Processamento> findProcessamentosPendentes() {
		return vendaDAO.findProcessamentosPendentes();
	}
	
	public void processarVendas(List<Processamento> processamentos) {
		
		for (Processamento processamento : processamentos) {
			
			PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		    writer.println("The first line");
		    writer.println("The second line");
		    writer.close();
			
			// Atualizando status para OK
			vendaDAO.updateStatusProcessamento(processamento.getId(), AppConstants.STATUS_OK);
		
		}
		
	}
	
}
