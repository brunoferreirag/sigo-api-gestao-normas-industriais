package br.com.indtextbr.services.gestaonormasindustriais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import br.com.indtextbr.services.gestaonormasindustriais.model.NormaIndustrial;
import br.com.indtextbr.services.gestaonormasindustriais.repository.NormaIndustrialRepository;

@Service
public class NormaIndustrialService {
	private NormaIndustrialRepository repository;
	
	@Autowired
	public NormaIndustrialService(NormaIndustrialRepository repository) {
		this.repository = repository;
	}
	
	public NormaIndustrial getById(Long id) {
		try{
			var normaIndustrialEncontrada = this.repository.findById(id);
			return normaIndustrialEncontrada;	
		}
		catch(HttpStatusCodeException ex) {
			return null;
		}
		
	}
	
	public String pesquisarNormasIndustriais(String codigo, String titulo,Integer versao, Integer page, Integer size){		
		return this.repository.pesquisarNormasIndustriais(codigo, titulo, versao, page ,size);
	}
}
