package br.com.dsrh.demo.batch.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.dsrh.demo.batch.dao.VendaDAO;
import br.com.dsrh.demo.batch.exception.DAOException;
import br.com.dsrh.demo.batch.model.ItemVenda;
import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;

@Repository("vendaDAO")
public class VendaDAOImpl implements VendaDAO {
	
	private static final Log log = LogFactory.getLog(VendaDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Venda> findVendas() throws DAOException {
		try {
			return jdbcTemplate.query("SELECT id_venda as id, data, loja, pdv, status FROM tb_venda WHERE status <> 'OK' ORDER BY id_venda LIMIT 2", new BeanPropertyRowMapper(Venda.class));
		} 
		catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	public void updateStatusVenda(Long id, String status) throws DAOException {
		try {
			jdbcTemplate.update("UPDATE tb_venda SET status = ? WHERE id_venda = ? ", status, id);
		} 
		catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Processamento> findProcessamentosPendentes() throws DAOException {
		try {
			return jdbcTemplate.query("SELECT id_venda as id, data, loja, pdv, status FROM tb_venda WHERE status <> 'OK' ORDER BY id_venda LIMIT 2", new BeanPropertyRowMapper(Processamento.class));
		} 
		catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	public void updateStatusProcessamento(Long id, String status) throws DAOException {
		try {
			jdbcTemplate.update("UPDATE tb_venda SET status = ? WHERE id_venda = ? ", status, id);
		} 
		catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage(), e);
		}
	}
	
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ItemVenda> findItemVendaByIdProcessamento(Long idProcessamento) throws DAOException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT t1.id_item_venda as idItemVenda, t1.id_venda as idVenda, t1.produto, t1.preco_unitario as precoUnitario, t1.desconto, t2.data as dataVenda ");
			sql.append("FROM tb_item_venda t1 ");
			sql.append("INNER JOIN tb_venda t2 ");
			sql.append("ON t2.id_venda = t1.id_venda ");
			sql.append("WHERE t1.id_venda = ? ");
			return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ItemVenda.class), idProcessamento);
		} 
		catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage(), e);
		}	
	}
	
}
