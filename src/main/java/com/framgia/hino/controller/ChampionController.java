package com.framgia.hino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framgia.hino.dto.request.AddChampDto;
import com.framgia.hino.service.ChampService;

@RestController
@RequestMapping("/champions")
public class ChampionController {

	@GetMapping("/")
	public ResponseEntity<String> list() {
		return new ResponseEntity<String>(ChampService.getChamps().toString(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody AddChampDto addDto) {
		return new ResponseEntity<String>("Zed!", HttpStatus.CREATED);
	}

	@GetMapping("/{champName}")
	public ResponseEntity<String> read(
//			@RequestParam(value = "champName", defaultValue = "yasuo")
			@PathVariable("champName")
			String champName) {
		return new ResponseEntity<String>(ChampService.getChamp(champName).toString(), HttpStatus.OK);
	}
}
