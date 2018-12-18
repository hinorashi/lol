package com.framgia.hino.service;

import com.framgia.hino.dto.request.CreateChampReqDto;
import com.framgia.hino.dto.response.ChampListResDto;
import com.framgia.hino.dto.response.ChampResDto;

public interface IChampService {

	ChampListResDto getChamps();

	ChampResDto createChamp(CreateChampReqDto createDto);
}
