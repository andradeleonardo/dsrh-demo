package br.com.dsrh.demo.web.service;

import java.util.List;

import br.com.dsrh.demo.web.exception.ServiceException;
import br.com.dsrh.demo.web.model.Processamento;

public interface VendaService {

	public List<Processamento> findProcessamentos() throws ServiceException;
	
}
