package com.framgia.hino.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.framgia.hino.common.Const;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "champ_id", "champ_name", "passive", "skill", "date_created" })
public class ChampResDto {

	@JsonProperty("champ_id")
	@JsonIgnore
	private String champId;

	@JsonProperty("champ_name")
	private String champName;

	@JsonProperty("passive")
	private String passive;

	@JsonProperty("skill")
	private String skill;

	@JsonProperty("date_created")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.DATE_PATTERN_BASIC_ISO)
	private LocalDate dateCreated;
}
