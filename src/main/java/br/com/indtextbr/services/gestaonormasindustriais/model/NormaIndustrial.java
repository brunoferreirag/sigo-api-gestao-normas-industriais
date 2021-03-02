package br.com.indtextbr.services.gestaonormasindustriais.model;

import lombok.*;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "norma_industrial", schema="public")
public class NormaIndustrial {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
	@Column(name = "id")
	private Long id;

	@JsonProperty("codigo")
	@NotNull
	@NotBlank(message = "Código deve ser preenchido")
	@Size(min = 0, max = 30, message = "Código deve ter no máximo 30 caracteres")
	@Column(name = "codigo")
	private String codigo;

	@Column(name = "data_vigor")
	@JsonProperty("data-vigor")
	private Date dataVigor;

	@JsonProperty("versao")
	@NotNull
	@Column(name = "versao")
	private Integer versao;

	@JsonProperty("titulo")
	@NotNull
	@NotBlank(message = "Título deve ser preenchido")
	@Size(min = 0, max = 300, message = "Título deve ter no máximo 300 caracteres")
	@Column(name = "titulo")
	private String titulo;

	@JsonProperty("autor")
	@NotNull
	@NotBlank(message = "Autor deve ser preenchido")
	@Size(min = 0, max = 100, message = "Autordeve ter no máximo 100 caracteres")
	@Column(name = "autor")
	private String autor;


	@JsonProperty("link")
	@Size(min = 0, max = 500, message = "Link deve ter no máximo 500 caracteres")
	@NotNull
	@NotBlank(message = "Link deve ser preenchido")
	@Column(name = "link")
	private String link;
}
