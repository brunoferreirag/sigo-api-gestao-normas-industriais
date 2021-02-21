package br.com.indtextbr.services.gestaonormasindustriais.controller;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
	
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<NormaIndustrial> getAllNormasIndustriaisAtivas(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException {
		PageRequest pageRequest = PageRequest.of(page, size);
		var normaisIndustriais = this.service.getAllNormasIndustriaisEmVigor(pageRequest);
		return normaisIndustriais;
	}
	
	@GetMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NormaIndustrial> getNormaIndustrialById(@PathVariable(value="id") String id) {
		var normaIndustrial = this.service.getById(id);
		return new ResponseEntity<>(normaIndustrial, (normaIndustrial == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> incluirNormaIndustrial(@RequestBody @Valid NormaIndustrial normaIndustrial){
		var normaIndustrialIncluida = this.service.criarNormaIndustrial(normaIndustrial);
		URI location = URI.create(String.format("/%s", normaIndustrialIncluida.getId()));
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NormaIndustrial> editarNormaIndustrial(@PathVariable(value="id") String id, @RequestBody @Valid NormaIndustrial normaIndustrial) {
		normaIndustrial.setId(id);
		NormaIndustrial normaIndustrialEditada = this.service.editarNormaIndustrial(normaIndustrial);
		return new ResponseEntity<>(normaIndustrialEditada, (normaIndustrialEditada == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> excluirNormaIndustrial(@PathVariable(value="id") String id) {
		this.service.excluirNormaIndustrial(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="busca",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<NormaIndustrial> pesquisarNormasIndustriais(@RequestParam(value="codigo", required=false)String codigo, @RequestParam(value="titulo",required=false)String titulo, @RequestParam(value="versao",required=false)String versao, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		var  normasIndustriais = this.service.pesquisarNormasIndustriais(codigo, titulo, versao, pageRequest);
		return normasIndustriais;
	}
}
