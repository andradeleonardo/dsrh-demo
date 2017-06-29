package br.com.dsrh.demo.batch.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.dsrh.demo.batch.dao.VendaDAO;
import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;

@Repository("vendaDAO")
public class VendaDAOImpl implements VendaDAO {
	
	private static final Log log = LogFactory.getLog(VendaDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<Venda> findVendas() {
		return jdbcTemplate.query("SELECT id_venda as id, data, loja, pdv, status FROM tb_venda WHERE status <> 'OK' ORDER BY id_venda LIMIT 2", new BeanPropertyRowMapper(Venda.class));
	}
	
	public void updateStatusVenda(Long id, String status) {
		jdbcTemplate.update("UPDATE tb_venda SET status = ? WHERE id_venda = ? ", status, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Processamento> findProcessamentosPendentes() {
		return jdbcTemplate.query("SELECT id_venda as id, data, loja, pdv, status FROM tb_venda WHERE status <> 'OK' ORDER BY id_venda LIMIT 2", new BeanPropertyRowMapper(Processamento.class));
	}
	
	public void updateStatusProcessamento(Long id, String status) {
		jdbcTemplate.update("UPDATE tb_venda SET status = ? WHERE id_venda = ? ", status, id);
	}
	
	public void insertProcessamento(Processamento processamento) {
		jdbcTemplate.update("INSERT INTO tb_processamento (id_processamento, data, loja, pdv, nome_arquivo, status) VALUES (?, ?, ?, ?, ?, ?)",
			processamento.getId(), processamento.getData(), processamento.getLoja(), processamento.getPdv(), processamento.getNomeArquivo(), processamento.getStatus());
	}
	
}
