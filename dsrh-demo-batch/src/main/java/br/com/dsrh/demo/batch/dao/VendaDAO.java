package br.com.dsrh.demo.batch.dao;

import java.util.List;

import br.com.dsrh.demo.batch.exception.DAOException;
import br.com.dsrh.demo.batch.model.ItemVenda;
import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;

public interface VendaDAO {

	public List<Venda> findVendas() throws DAOException;
	
	public void updateStatusVenda(Long id, String status) throws DAOException;
	
	public List<Processamento> findProcessamentosPendentes() throws DAOException;
	
	public void updateStatusProcessamento(Long id, String status) throws DAOException;
	
	public void insertProcessamento(Processamento processamento) throws DAOException;
	
	public List<ItemVenda> findItemVendaByIdProcessamento(Long idProcessamento) throws DAOException;
	
}
