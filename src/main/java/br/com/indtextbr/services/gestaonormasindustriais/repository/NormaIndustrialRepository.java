package br.com.indtextbr.services.gestaonormasindustriais.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.indtextbr.services.gestaonormasindustriais.model.NormaIndustrial;


@Repository
public interface NormaIndustrialRepository extends JpaRepository<NormaIndustrial, Long> {
	
	Page<NormaIndustrial> findByCodigoContainsAndTituloContainsAndVersao(String codigo,  String titulo, Integer versao,  Pageable request);
	
	Page<NormaIndustrial> findByCodigoContainsAndTituloContains(String codigo,  String titulo,  Pageable request);
	
	Page<NormaIndustrial> findByCodigoContainsAndVersao(String codigo,  Integer versao, Pageable request);
	
	Page<NormaIndustrial> findByTituloContainsAndVersao(String titulo,  Integer versao, Pageable request);
	
	Page<NormaIndustrial> findByCodigoContains(String codigo, Pageable request);
	
	Page<NormaIndustrial> findByTituloContains(String titulo,  Pageable request);
	
	Page<NormaIndustrial> findByVersao(Integer versao, Pageable request);
}
