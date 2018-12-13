package com.framgia.hino.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framgia.hino.entity.ChampEntity;
import com.framgia.hino.exception.NotFoundException;

public class ChampService {

	private static Map<String, ChampEntity> champs = new HashMap<>();

	static {

		ChampEntity zed = new ChampEntity();
		zed.setChampId("1");
		zed.setChampName("zed");
		zed.setPassive("Contempt for the Weak");
		zed.setSkill("Razor Shuriken");
		champs.put(zed.getChampName(), zed);

		ChampEntity katarina = new ChampEntity();
		katarina.setChampId("2");
		katarina.setChampName("katarina");
		katarina.setPassive("Voracity");
		katarina.setSkill("Bouncing Blades");
		champs.put(katarina.getChampName(), katarina);

		ChampEntity vayne = new ChampEntity();
		vayne.setChampId("3");
		vayne.setChampName("vayne");
		vayne.setPassive("Night Hunter");
		vayne.setSkill("Tumble");
		champs.put(vayne.getChampName(), vayne);

	}

	public static List<ChampEntity> getChamps() {
		return new ArrayList<>(champs.values());
	}

	public static ChampEntity getChamp(String champName) {
		if (!champs.containsKey(champName))
			throw new NotFoundException();
		return champs.get(champName);
	}
}
