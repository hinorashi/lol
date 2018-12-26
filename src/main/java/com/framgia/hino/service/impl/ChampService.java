package com.framgia.hino.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.framgia.hino.common.Const;
import com.framgia.hino.dto.request.CreateChampReqDto;
import com.framgia.hino.dto.response.ChampListResDto;
import com.framgia.hino.dto.response.ChampResDto;
import com.framgia.hino.entity.ChampEntity;
import com.framgia.hino.exception.NotFoundException;
import com.framgia.hino.service.IChampService;

@Service
public class ChampService implements IChampService {

	private static Map<String, ChampEntity> champMap = new HashMap<>();

	/** currentId for storing current Champion */
	private static AtomicInteger currentId = new AtomicInteger(5);

	// FIXME can be mocked with JPA?
	static {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Const.DATE_PATTERN_BASIC_ISO);
		// DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
		ChampEntity zed = new ChampEntity();
		zed.setChampId("1");
		zed.setChampName("zed");
		zed.setPassive("Contempt for the Weak");
		zed.setSkill("Razor Shuriken");
		zed.setDateCreated(LocalDate.parse("19921031", formatter));
		champMap.put(zed.getChampName(), zed);

		ChampEntity syndra = new ChampEntity();
		syndra.setChampId("2");
		syndra.setChampName("syndra");
		syndra.setPassive("Transcendent");
		syndra.setSkill("Dark Sphere");
		syndra.setDateCreated(LocalDate.parse("20011126", formatter));
		champMap.put(syndra.getChampName(), syndra);

		ChampEntity katarina = new ChampEntity();
		katarina.setChampId("3");
		katarina.setChampName("katarina");
		katarina.setPassive("Voracity");
		katarina.setSkill("Bouncing Blades");
		katarina.setDateCreated(LocalDate.parse("19990101", formatter));
		champMap.put(katarina.getChampName(), katarina);

		ChampEntity vayne = new ChampEntity();
		vayne.setChampId("4");
		vayne.setChampName("vayne");
		vayne.setPassive("Night Hunter");
		vayne.setSkill("Tumble");
		vayne.setDateCreated(LocalDate.parse("20000101", formatter));
		champMap.put(vayne.getChampName(), vayne);

	}

	@Override
	public ChampListResDto getChamps() {

		ChampListResDto champs = new ChampListResDto();
		List<ChampResDto> champList = new ArrayList<>();

		champMap.forEach((k, v) -> {
			ChampResDto champ = new ChampResDto();
			champ.setChampId(v.getChampId());
			champ.setChampName(v.getChampName());
			champ.setPassive(v.getPassive());
			champ.setSkill(v.getSkill());
			champ.setDateCreated(v.getDateCreated());
			champList.add(champ);
			System.out.println(k + " " + v);
		});
		champs.setChamps(champList);
		return champs;
	}

	public static ChampResDto getChamp(String champName) {
		if (!champMap.containsKey(champName))
			throw new NotFoundException(champName + " not found!");
		return dtoFromEntity(champMap.get(champName));
	}

	@Override
	public ChampResDto createChamp(CreateChampReqDto createDto) {

		ChampEntity entity = new ChampEntity();
		entity.setChampName(createDto.getChampName());
		entity.setPassive(createDto.getPassive());
		entity.setSkill(createDto.getSkill());
		entity.setChampId(String.valueOf(currentId.getAndIncrement()));
		entity.setDateCreated(LocalDate.now());

		champMap.put(entity.getChampName(), entity);
		return dtoFromEntity(entity);
	}

	public static ChampResDto dtoFromEntity(ChampEntity entity) {

		ChampResDto dto = new ChampResDto();
		dto.setChampId(entity.getChampId());
		dto.setChampName(entity.getChampName());
		dto.setPassive(entity.getPassive());
		dto.setSkill(entity.getSkill());
		dto.setDateCreated(entity.getDateCreated());

		return dto;
	}
}
