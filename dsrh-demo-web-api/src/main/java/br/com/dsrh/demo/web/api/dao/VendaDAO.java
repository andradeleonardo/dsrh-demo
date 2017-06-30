package br.com.dsrh.demo.web.api.dao;

import br.com.dsrh.demo.web.api.exception.DAOException;
import br.com.dsrh.demo.web.api.model.Processamento;

public interface VendaDAO {

	public void insertProcessamento(Processamento processamento) throws DAOException;
	
}
