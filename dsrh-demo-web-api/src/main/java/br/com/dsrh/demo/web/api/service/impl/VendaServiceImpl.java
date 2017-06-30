package br.com.dsrh.demo.web.api.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dsrh.demo.web.api.dao.VendaDAO;
import br.com.dsrh.demo.web.api.exception.ServiceException;
import br.com.dsrh.demo.web.api.model.Processamento;
import br.com.dsrh.demo.web.api.model.Venda;
import br.com.dsrh.demo.web.api.service.VendaService;
import br.com.dsrh.demo.web.api.util.AppConstants;
import br.com.dsrh.demo.web.api.util.MessageResource;

@Service("vendaService")
public class VendaServiceImpl implements VendaService {

	private static final Log log = LogFactory.getLog(VendaServiceImpl.class);
	
	@Autowired
	private VendaDAO vendaDAO;
	
	@Autowired
	private MessageResource messageResource;
		
	public void tratarVenda(Venda venda) throws ServiceException {
		try {
			// Incluindo na fila de processamento.
			Processamento processamento = new Processamento();
			processamento.setId(venda.getId());
			processamento.setData(new Date());
			processamento.setLoja(venda.getLoja());
			processamento.setPdv(venda.getPdv());
			processamento.setStatus(AppConstants.STATUS_PENDENTE);
			
			vendaDAO.insertProcessamento(processamento);
		} 
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(messageResource.getMessage("message.erro.service.operacao", null, null), e);
		} 
	}
	
}
