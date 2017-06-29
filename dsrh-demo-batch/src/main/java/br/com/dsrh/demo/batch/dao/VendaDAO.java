package br.com.dsrh.demo.batch.dao;

import java.util.List;

import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;

public interface VendaDAO {

	public List<Venda> findVendas();
	
	public void updateStatusVenda(Long id, String status);
	
	public List<Processamento> findProcessamentosPendentes();
	
	public void updateStatusProcessamento(Long id, String status);
	
	public void insertProcessamento(Processamento processamento);
	
}
