package br.com.indtextbr.services.gestaonormasindustriais.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.indtextbr.services.gestaonormasindustriais.common.Status;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(value="norma-industrial")
@JsonRootName("norma-industrial")
public class NormaIndustrial {

	@Id
	@GeneratedValue
	@JsonProperty("id")
	private String id;

	@JsonProperty("codigo")
	@NotNull
	@NotBlank(message = "Código deve ser preenchido")
	@Size(min = 0, max = 30, message = "Código deve ter no máximo 30 caracteres")
	private String codigo;

	@LastModifiedDate
	@JsonProperty("data-alteracao")
	private Instant dataAlteracao;

	@CreatedDate
	@JsonProperty("data-criacao")
	private Instant dataCriacao;

	@JsonProperty("data-vigor")
	private Instant dataVigor;

	@LastModifiedBy
	@JsonProperty("username-alteracao")
	private String userNameAlteracao;

	@CreatedBy
	@JsonProperty("username-criacao")
	private String userNameCriacao;

	@JsonProperty("versao")
	@NotNull
	@NotBlank(message = "Versão deve ser preenchida")
	@Size(min = 0, max = 3, message = "Versão deve ter no máximo 3 caracteres")
	private String versao;

	@JsonProperty("titulo")
	@NotNull
	@NotBlank(message = "Título deve ser preenchido")
	@Size(min = 0, max = 300, message = "Título deve ter no máximo 300 caracteres")

	private String titulo;

	@JsonProperty("autor")
	@NotNull
	@NotBlank(message = "Autor deve ser preenchido")
	@Size(min = 0, max = 100, message = "Autordeve ter no máximo 100 caracteres")
	private String autor;

	@JsonProperty("status")
	private Status status;

	@JsonProperty("link")
	@Size(min = 0, max = 500, message = "Link deve ter no máximo 500 caracteres")
	@NotNull
	@NotBlank(message = "Link deve ser preenchido")
	private String link;
}
