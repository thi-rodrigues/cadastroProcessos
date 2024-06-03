package com.fsrb.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PROCESSO")
public class Processo implements Serializable {

	private static final long serialVersionUID = -6589433640222054096L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String npu;

	@NotNull
	@Column(name = "data_cadastro")
	private LocalDateTime dataCadastro;
	
	@Column(name = "data_visualizacao")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime dataVisualizacao;

	@NotNull
	private String municipio;

	@NotNull
	private String uf;
	
	@NotNull
	private String pathUploadDocumento;
	
	public Processo(Long id, String npu, LocalDateTime dataCadastro,
			String municipio, String uf) {
		this.id = id;
		this.npu = npu;
		this.dataCadastro = dataCadastro;
		this.municipio = municipio;
		this.uf = uf;
	}

}
