package br.com.dsrh.demo.web.api.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.dsrh.demo.web.api.dao.VendaDAO;
import br.com.dsrh.demo.web.api.exception.DAOException;
import br.com.dsrh.demo.web.api.model.Processamento;

@Repository("vendaDAO")
public class VendaDAOImpl implements VendaDAO {
	
	private static final Log log = LogFactory.getLog(VendaDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertProcessamento(Processamento processamento) throws DAOException {
		try {
			jdbcTemplate.update("INSERT INTO tb_processamento (id_processamento, data, loja, pdv, nome_arquivo, status) VALUES (?, ?, ?, ?, ?, ?)",
				processamento.getId(), processamento.getData(), processamento.getLoja(), processamento.getPdv(), processamento.getNomeArquivo(), processamento.getStatus());
		} 
		catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage(), e);
		}
	}
	
}
