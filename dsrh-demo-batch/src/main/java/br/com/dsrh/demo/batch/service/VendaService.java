package br.com.dsrh.demo.batch.service;

import java.util.List;

import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;

public interface VendaService {

	public List<Venda> findVendas();
	
	public void tratarVenda(Venda venda);
	
	public List<Processamento> findProcessamentosPendentes();
	
}
