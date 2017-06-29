package br.com.dsrh.demo.batch.service;

import java.util.List;

import br.com.dsrh.demo.batch.exception.ServiceException;
import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;

public interface VendaService {

	public List<Venda> findVendas() throws ServiceException;
	
	public void tratarVenda(Venda venda) throws ServiceException;
	
	public List<Processamento> findProcessamentosPendentes() throws ServiceException;
	
	public void processarVendas(List<Processamento> processamentos) throws ServiceException;
	
}
