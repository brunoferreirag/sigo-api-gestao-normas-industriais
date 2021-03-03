package br.com.indtextbr.services.gestaonormasindustriais.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.indtextbr.services.gestaonormasindustriais.model.NormaIndustrial;
import br.com.indtextbr.services.gestaonormasindustriais.service.NormaIndustrialService;

@RestController
@RequestMapping("norma-industrial")
@CrossOrigin(origins = "*")
public class NormaIndustrialController {
	private NormaIndustrialService service;
	
	@Autowired
	public NormaIndustrialController(NormaIndustrialService service) {
		this.service = service;
	}
	
	
	@GetMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NormaIndustrial> getNormaIndustrialById(@PathVariable(value="id") Long id) {
		var normaIndustrial = this.service.getById(id);
		return new ResponseEntity<>(normaIndustrial, (normaIndustrial == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	
	@GetMapping(value="busca",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String pesquisarNormasIndustriais(@RequestParam(value="codigo", required=false)String codigo, @RequestParam(value="titulo",required=false)String titulo, @RequestParam(value="versao",required=false)Integer versao, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
		var  normasIndustriais = this.service.pesquisarNormasIndustriais(codigo, titulo, versao,page, size);
		return normasIndustriais;
	}
}
