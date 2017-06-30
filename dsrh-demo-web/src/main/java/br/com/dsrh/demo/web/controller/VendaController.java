package br.com.dsrh.demo.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.dsrh.demo.web.service.VendaService;

@Controller
public class VendaController {

	private static final Log log = LogFactory.getLog(VendaController.class);

	@Autowired
	private VendaService vendaService;

	@RequestMapping(value="/processamentos", method = RequestMethod.GET)
	public ModelAndView listarProcessamentos() throws Exception {
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("processamentos",  vendaService.findProcessamentos());
			return new ModelAndView("processamentosLista", model);
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}	
	}

}
