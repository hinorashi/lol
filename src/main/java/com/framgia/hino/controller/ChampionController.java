package com.framgia.hino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.framgia.hino.dto.request.CreateChampReqDto;
import com.framgia.hino.entity.ChampEntity;
import com.framgia.hino.service.IChampService;
import com.framgia.hino.service.impl.ChampService;

@RestController
@RequestMapping("/champions")
public class ChampionController {

	@Autowired
	private IChampService champService;
	
	@GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		return new ResponseEntity<>(champService.getChamps(), HttpStatus.OK);
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody CreateChampReqDto createDto) {
		return new ResponseEntity<>(champService.createChamp(createDto), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{champName}", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> read(
			// @RequestParam(value = "champName", defaultValue = "yasuo")
			@PathVariable("champName") String champName) {
		return new ResponseEntity<>(ChampService.getChamp(champName), HttpStatus.OK);
	}
}
