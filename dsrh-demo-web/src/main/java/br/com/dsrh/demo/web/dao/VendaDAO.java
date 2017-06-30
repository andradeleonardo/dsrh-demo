package br.com.dsrh.demo.web.dao;

import java.util.List;

import br.com.dsrh.demo.web.exception.DAOException;
import br.com.dsrh.demo.web.model.Processamento;

public interface VendaDAO {

	public List<Processamento> findProcessamentos() throws DAOException;
	
}
