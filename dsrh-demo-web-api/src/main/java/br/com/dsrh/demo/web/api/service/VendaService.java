package br.com.dsrh.demo.web.api.service;

import br.com.dsrh.demo.web.api.exception.ServiceException;
import br.com.dsrh.demo.web.api.model.Venda;

public interface VendaService {

	public void tratarVenda(Venda venda) throws ServiceException;
	
}
