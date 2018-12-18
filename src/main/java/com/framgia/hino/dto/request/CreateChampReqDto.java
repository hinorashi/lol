package com.framgia.hino.dto.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreateChampReqDto {

	@NotBlank
	@JsonProperty("champ_name")
	private String champName;

	@NotBlank
	@JsonProperty("passive")
	private String passive;

	@NotBlank
	@JsonProperty("skill")
	private String skill;
}
