package br.com.indtextbr.services.gestaonormasindustriais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.indtextbr.services.gestaonormasindustriais.common.Status;
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
		return this.repository.findByStatus(Status.EM_VIGOR,page);
	}
	
	public NormaIndustrial criarNormaIndustrial(NormaIndustrial normaIndustrial) {
		normaIndustrial.setStatus(Status.EM_VIGOR);
		return this.repository.save(normaIndustrial);
	}
	
	public NormaIndustrial editarNormaIndustrial(NormaIndustrial normaIndustrialEdicao) {
		var normaIndustrialEncontrada = this.repository.findById(normaIndustrialEdicao.getId());
		if(normaIndustrialEncontrada.isPresent()) {
			var normaIndustrial = normaIndustrialEncontrada.get();
			normaIndustrial.setLink(normaIndustrialEdicao.getLink());
			normaIndustrial.setAutor(normaIndustrialEdicao.getAutor());
			normaIndustrial.setTitulo(normaIndustrialEdicao.getTitulo());
			normaIndustrial.setUserNameAlteracao(normaIndustrialEdicao.getUserNameAlteracao());
			normaIndustrial.setVersao(normaIndustrialEdicao.getVersao());
			normaIndustrial.setDataAlteracao(normaIndustrialEdicao.getDataAlteracao());
			normaIndustrial.setDataVigor(normaIndustrialEdicao.getDataVigor());
			return this.repository.save(normaIndustrial);
		}
		return null;
	}
	
	public boolean excluirNormaIndustrial(String id) {
		var normaIndustrialEncontrada = this.repository.findById(id);
		if(normaIndustrialEncontrada.isPresent()) {
			var normaIndustrial = normaIndustrialEncontrada.get();
			this.repository.delete(normaIndustrial);
			return true;
		}
		return false;
	}
	
	public NormaIndustrial getById(String id) {
		var normaIndustrialEncontrada = this.repository.findById(id);
		if(normaIndustrialEncontrada.isPresent()) {
			return normaIndustrialEncontrada.get();
		}
		return null;
	}
	
	public Page<NormaIndustrial> pesquisarNormasIndustriais(String codigo, String titulo,String versao, PageRequest page){
		if(codigo!=null || titulo!=null || versao!=null) {
			if(codigo!=null && titulo ==null && versao ==null) {
				return this.repository.findByCodigoContainsAndStatus(codigo, Status.EM_VIGOR, page);
			}
			else if (codigo==null && titulo !=null && versao ==null) {
				return this.repository.findByTituloContainsAndStatus(titulo, Status.EM_VIGOR, page);
			}
			else if (codigo==null && titulo ==null && versao !=null) {
				return this.repository.findByVersaoContainsAndStatus(titulo, Status.EM_VIGOR, page);
			}
			else if (codigo!=null && titulo !=null && versao ==null) {
				return this.repository.findByCodigoContainsAndTituloContainsAndStatus(codigo, titulo, Status.EM_VIGOR, page);
			}
			
			else if (codigo!=null && titulo ==null && versao !=null) {
				return this.repository.findByCodigoContainsAndVersaoContainsAndStatus(codigo, versao, Status.EM_VIGOR, page);
			}
			
			else if (codigo==null && titulo !=null && versao !=null) {
				return this.repository.findByTituloContainsAndVersaoContainsAndStatus(titulo, versao, Status.EM_VIGOR, page);
			}
			
			
			return this.repository.findByCodigoContainsAndTituloContainsAndVersaoContainsAndStatus(codigo, titulo, versao, Status.EM_VIGOR,page);
		}
		
		return this.repository.findByStatus(Status.EM_VIGOR, page);
		
	}
}
