package br.com.dsrh.demo.batch.service.impl;

import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dsrh.demo.batch.dao.VendaDAO;
import br.com.dsrh.demo.batch.exception.ServiceException;
import br.com.dsrh.demo.batch.model.ItemVenda;
import br.com.dsrh.demo.batch.model.Processamento;
import br.com.dsrh.demo.batch.model.Venda;
import br.com.dsrh.demo.batch.service.VendaService;
import br.com.dsrh.demo.batch.util.AppConstants;
import br.com.dsrh.demo.batch.util.AppUtil;
import br.com.dsrh.demo.batch.util.MessageResource;

@Service("vendaService")
public class VendaServiceImpl implements VendaService {

	private static final Log log = LogFactory.getLog(VendaServiceImpl.class);
	
	@Autowired
	private VendaDAO vendaDAO;
	
	@Autowired
	private MessageResource messageResource;
		
	public List<Venda> findVendas() throws ServiceException {
		try {
			return vendaDAO.findVendas();
		} 
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(messageResource.getMessage("message.erro.service.operacao", null, null), e);
		} 	
	}
	
	public void tratarVenda(Venda venda) throws ServiceException {
		try {
			// Incluindo na fila de processamento.
			Processamento processamento = new Processamento();
			processamento.setId(venda.getId());
			processamento.setData(new Date());
			processamento.setLoja(venda.getLoja());
			processamento.setPdv(venda.getPdv());
			processamento.setStatus(AppConstants.STATUS_PENDENTE);
			
			vendaDAO.insertProcessamento(processamento);
			
			// Atualizando status para OK
			vendaDAO.updateStatusVenda(venda.getId(), AppConstants.STATUS_OK);
		} 
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(messageResource.getMessage("message.erro.service.operacao", null, null), e);
		} 
	}
	
	public List<Processamento> findProcessamentosPendentes() throws ServiceException {
		try {
			return vendaDAO.findProcessamentosPendentes();
		} 
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(messageResource.getMessage("message.erro.service.operacao", null, null), e);
		} 
	}
	
	public void processarVendas(List<Processamento> processamentos) throws ServiceException {
		
		SimpleDateFormat df = new SimpleDateFormat("ddmmyyyy");
		DecimalFormat nf = new DecimalFormat(".#####");
		
		// Verifica se diretório de gravação existe.
		File diretorio = new File(AppConstants.PATH_STORAGE);
		
		// Se não existir, cria.
		if (!diretorio.exists())
			diretorio.mkdirs();
		
		String fileName = null;
		
		try {
			
			// Para cada processamento pendente, varre os itens da venda.
			for (Processamento processamento : processamentos) {
				
				List<ItemVenda> itens = vendaDAO.findItemVendaByIdProcessamento(processamento.getId());
				
				// Iteração dos itens para gravação em arquivo texto.
				for (ItemVenda item : itens) {
					
					// Cria novo arquivo, se necessário.
					if (AppUtil.ROW_INDEX == AppConstants.FILE_ROW_LIMIT
							|| AppUtil.FILE_COUNTER == 0) {
						AppUtil.FILE_COUNTER++;
						AppUtil.ROW_INDEX = 0;
						if (AppUtil.printWriter!=null) {
							AppUtil.printWriter.close();
							AppUtil.printWriter = null;
						}
					}
					if (AppUtil.printWriter==null) {
						fileName = AppConstants.PREFIX_FILE_NAME + AppUtil.FILE_COUNTER + ".txt";
						AppUtil.printWriter = new PrintWriter(new File(AppConstants.PATH_STORAGE + fileName), "UTF-8");
					}
					
					if (AppUtil.ROW_INDEX > 0){ // Quebra linha para escrever próximo registro.
						AppUtil.printWriter.append("\n");
					}
					
					// Escreve a linha equivalente ao item da venda. 
					AppUtil.printWriter.append(StringUtils.leftPad(String.valueOf(item.getIdVenda()), 10, '0'));
					AppUtil.printWriter.append(StringUtils.leftPad(String.valueOf(df.format(item.getDataVenda())), 10, '0'));
					AppUtil.printWriter.append(StringUtils.leftPad(String.valueOf(processamento.getLoja()), 4, '0'));
					AppUtil.printWriter.append(StringUtils.leftPad(String.valueOf(processamento.getPdv()), 3, '0'));
					AppUtil.printWriter.append(StringUtils.leftPad(item.getProduto(), 11, '0'));
					AppUtil.printWriter.append(StringUtils.leftPad(nf.format(item.getPrecoUnitario()).replace(".", "").replace(",", ""), 5, '0'));
					AppUtil.printWriter.append(StringUtils.leftPad(nf.format(item.getDesconto()).replace(".", "").replace(",", ""), 4, '0'));
					AppUtil.printWriter.append(StringUtils.leftPad(nf.format(item.getPrecoUnitario()-item.getDesconto()).replace(".", "").replace(",", ""), 5, '0'));
				    
				    // Incrementa o controle de linhas do arquivo.
					AppUtil.ROW_INDEX++;
					
				}
				
				// Atualizando status do processamento para OK
				vendaDAO.updateStatusProcessamentoAndNomeArquivo(processamento.getId(), AppConstants.STATUS_OK, fileName);
			}
		
		} 
		catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(messageResource.getMessage("message.erro.service.operacao", null, null), e);
		} 
		finally {
			if (AppUtil.ROW_INDEX == AppConstants.FILE_ROW_LIMIT && 
					AppUtil.printWriter!=null) 
				AppUtil.printWriter.close();
		}
	}
	
}
