package one.hino.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChampEntity {

	private String champId;

	private String champName;

	private String passive;

	private String skill;
}
