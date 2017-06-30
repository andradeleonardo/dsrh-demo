package br.com.dsrh.demo.web.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.dsrh.demo.web.dao.VendaDAO;
import br.com.dsrh.demo.web.exception.DAOException;
import br.com.dsrh.demo.web.model.Processamento;

@Repository("vendaDAO")
public class VendaDAOImpl implements VendaDAO {
	
	private static final Log log = LogFactory.getLog(VendaDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Processamento> findProcessamentos() throws DAOException {
		try {
			return jdbcTemplate.query("SELECT id_venda as id, data, loja, pdv, status FROM tb_venda WHERE status <> 'OK' ORDER BY id_venda DESC", new BeanPropertyRowMapper(Processamento.class));
		} 
		catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage(), e);
		}
	}
	
}
