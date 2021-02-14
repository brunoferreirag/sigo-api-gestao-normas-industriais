package br.com.indtextbr.services.gestaonormasindustriais.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.indtextbr.services.gestaonormasindustriais.common.Status;
import br.com.indtextbr.services.gestaonormasindustriais.model.NormaIndustrial;


@Repository
public interface NormaIndustrialRepository extends MongoRepository<NormaIndustrial, String> {
	Page<NormaIndustrial> findByStatus(Status status,Pageable request);
	
	Page<NormaIndustrial> findByCodigoContainsAndTituloContainsAndVersaoContainsAndStatus(String codigo,  String titulo, String versao,  Status status, Pageable request);
	
	Page<NormaIndustrial> findByCodigoContainsAndTituloContainsAndStatus(String codigo,  String titulo, Status status, Pageable request);
	
	Page<NormaIndustrial> findByCodigoContainsAndVersaoContainsAndStatus(String codigo,  String versao, Status status, Pageable request);
	
	Page<NormaIndustrial> findByTituloContainsAndVersaoContainsAndStatus(String titulo,  String versao, Status status, Pageable request);
	
	Page<NormaIndustrial> findByCodigoContainsAndStatus(String codigo, Status status, Pageable request);
	
	Page<NormaIndustrial> findByTituloContainsAndStatus(String titulo, Status status, Pageable request);
	
	Page<NormaIndustrial> findByVersaoContainsAndStatus(String versao, Status status, Pageable request);
}
