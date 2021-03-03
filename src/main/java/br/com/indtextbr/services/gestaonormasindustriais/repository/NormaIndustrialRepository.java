package br.com.indtextbr.services.gestaonormasindustriais.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.indtextbr.services.gestaonormasindustriais.model.NormaIndustrial;


@Repository
public class NormaIndustrialRepository {
	private String enderecoApi;
	private RestTemplate template;
	@Autowired
	public NormaIndustrialRepository(RestTemplate template,  @Value("${enderecoApi}") String enderecoApi) {
		this.enderecoApi = enderecoApi;
		this.template = template;
	}
	public NormaIndustrial findById(Long id) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "Bearer "+"");
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<NormaIndustrial> httpEntity = new HttpEntity<>(requestHeaders);
		ParameterizedTypeReference<NormaIndustrial>  tipoResposta = new ParameterizedTypeReference<NormaIndustrial>() {};
		String urlFinal = this.enderecoApi + "/" + id;
		ResponseEntity<NormaIndustrial> responseEntity = template.exchange(urlFinal, HttpMethod.GET, httpEntity,tipoResposta);
		return responseEntity.getBody();
	}
	
	public String pesquisarNormasIndustriais(String codigo, String titulo, Integer versao, Integer page , Integer size) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Authorization", "Bearer "+"");
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<>(requestHeaders);
		ParameterizedTypeReference<String>  tipoResposta = new ParameterizedTypeReference<String>() {};
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		if(codigo!=null) {
			sb.append("&codigo="+codigo);
		}
		if(titulo!=null) {
			sb.append("&titulo="+titulo);
		}
		if(versao!=null) {
			sb.append("&versao="+versao);
		}
		sb.append("&page="+page);
		sb.append("&size="+size);
		String urlFinal = this.enderecoApi + "/busca" + sb.toString();
		ResponseEntity<String> responseEntity = template.exchange(urlFinal, HttpMethod.GET, httpEntity,tipoResposta);
		return responseEntity.getBody();
	}
	
}
