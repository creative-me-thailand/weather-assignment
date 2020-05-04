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
import com.assignment.service.RickAndMortyLocationService;

@RestController
@RequestMapping("/api/")
public class RickAndMortyLocationController {

	private RickAndMortyLocationService locationService;

	@Autowired
	public RickAndMortyLocationController(RickAndMortyLocationService locationService) {
		this.locationService = locationService;
		
	}

	@GetMapping(value = "location/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCharacter(@PathVariable String locationId) {
		Response response = new Response(locationService.getById(locationId),"Get location success.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping(value = "location-character/{characterId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLocationByCharacterId(@PathVariable String characterId) {
		Response response = new Response(locationService.getByCharacterId(characterId),"Get location success.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
