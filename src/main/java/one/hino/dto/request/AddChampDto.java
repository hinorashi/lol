package one.hino.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddChampDto {

	@NotBlank
	private String champName;

	@NotBlank
	private String passive;

	@NotBlank
	private String skill;
}
