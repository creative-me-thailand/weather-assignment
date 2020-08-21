package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.domain.response.Response;
import com.assignment.service.RickAndMortyCharacterService;

@RestController
@RequestMapping("/api/")
public class RickAndMortyCharacterController {

	private RickAndMortyCharacterService characterService;

	@Autowired
	public RickAndMortyCharacterController(RickAndMortyCharacterService characterService) {
		this.characterService = characterService;

	}

	@GetMapping(value = "character/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCharacter() {
		Response response = new Response(characterService.getCharacter(), "Get character success.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "character/{characterId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCharacterById(@PathVariable String characterId) {
		Response response = new Response(characterService.getCharacterById(characterId),
				"Get character by id : " + characterId + " success.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
