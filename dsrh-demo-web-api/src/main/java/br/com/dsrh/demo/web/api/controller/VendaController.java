package br.com.dsrh.demo.web.api.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsrh.demo.web.api.model.Venda;
import br.com.dsrh.demo.web.api.service.VendaService;

@RestController
public class VendaController {
	
	private static final Log log = LogFactory.getLog(VendaController.class);
	
	@Autowired
	private VendaService vendaService;
	
	@PostMapping(value = "/venda")
	public ResponseEntity incluirVenda(@RequestBody Venda venda)  throws Exception {
		try {
			vendaService.tratarVenda(venda);
		}
		catch(Exception e) {
			log.error(e);
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(venda, HttpStatus.OK);		
	}
}
