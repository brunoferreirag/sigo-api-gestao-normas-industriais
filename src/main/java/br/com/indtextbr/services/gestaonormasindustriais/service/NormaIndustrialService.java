package br.com.indtextbr.services.gestaonormasindustriais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.indtextbr.services.gestaonormasindustriais.model.NormaIndustrial;
import br.com.indtextbr.services.gestaonormasindustriais.repository.NormaIndustrialRepository;

@Service
public class NormaIndustrialService {
	private NormaIndustrialRepository repository;
	
	@Autowired
	public NormaIndustrialService(NormaIndustrialRepository repository) {
		this.repository = repository;
	}
	
	public Page<NormaIndustrial> getAllNormasIndustriaisEmVigor(PageRequest page){
		return this.repository.findAll(page);
	}
	
	public NormaIndustrial criarNormaIndustrial(NormaIndustrial normaIndustrial) {
		return this.repository.save(normaIndustrial);
	}
	
	public NormaIndustrial editarNormaIndustrial(NormaIndustrial normaIndustrialEdicao) {
		var normaIndustrialEncontrada = this.repository.findById(normaIndustrialEdicao.getId());
		if(normaIndustrialEncontrada.isPresent()) {
			var normaIndustrial = normaIndustrialEncontrada.get();
			normaIndustrial.setLink(normaIndustrialEdicao.getLink());
			normaIndustrial.setAutor(normaIndustrialEdicao.getAutor());
			normaIndustrial.setTitulo(normaIndustrialEdicao.getTitulo());
			normaIndustrial.setVersao(normaIndustrialEdicao.getVersao());
			normaIndustrial.setDataVigor(normaIndustrialEdicao.getDataVigor());
			return this.repository.save(normaIndustrial);
		}
		return null;
	}
	
	public boolean excluirNormaIndustrial(Long id) {
		var normaIndustrialEncontrada = this.repository.findById(id);
		if(normaIndustrialEncontrada.isPresent()) {
			var normaIndustrial = normaIndustrialEncontrada.get();
			this.repository.delete(normaIndustrial);
			return true;
		}
		return false;
	}
	
	public NormaIndustrial getById(Long id) {
		var normaIndustrialEncontrada = this.repository.findById(id);
		if(normaIndustrialEncontrada.isPresent()) {
			return normaIndustrialEncontrada.get();
		}
		return null;
	}
	
	public Page<NormaIndustrial> pesquisarNormasIndustriais(String codigo, String titulo,Integer versao, PageRequest page){
		if(codigo!=null || titulo!=null || versao!=null) {
			if(codigo!=null && titulo ==null && versao ==null) {
				return this.repository.findByCodigoContains(codigo, page);
			}
			else if (codigo==null && titulo !=null && versao ==null) {
				return this.repository.findByTituloContains(titulo, page);
			}
			else if (codigo==null && titulo ==null && versao !=null) {
				return this.repository.findByVersao(versao, page);
			}
			else if (codigo!=null && titulo !=null && versao ==null) {
				return this.repository.findByCodigoContainsAndTituloContains(codigo, titulo, page);
			}
			
			else if (codigo!=null && titulo ==null && versao !=null) {
				return this.repository.findByCodigoContainsAndVersao(codigo, versao, page);
			}
			
			else if (codigo==null && titulo !=null && versao !=null) {
				return this.repository.findByTituloContainsAndVersao(titulo, versao, page);
			}
			
			
			return this.repository.findAll(page);
		}
		
		return this.repository.findAll(page);
		
	}
}
