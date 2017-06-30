package br.com.dsrh.demo.web.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dsrh.demo.web.dao.VendaDAO;
import br.com.dsrh.demo.web.exception.ServiceException;
import br.com.dsrh.demo.web.model.Processamento;
import br.com.dsrh.demo.web.service.VendaService;
import br.com.dsrh.demo.web.util.MessageResource;

@Service("vendaService")
public class VendaServiceImpl implements VendaService {

	private static final Log log = LogFactory.getLog(VendaServiceImpl.class);
	
	@Autowired
	private VendaDAO vendaDAO;
	
	@Autowired
	private MessageResource messageResource;
	
	public List<Processamento> findProcessamentos() throws ServiceException {
		try {
			return vendaDAO.findProcessamentos();
		} 
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(messageResource.getMessage("message.erro.service.operacao", null, null), e);
		} 	
	}
		
	
	
}
