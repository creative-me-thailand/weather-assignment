package com.assignment.service;

import com.assignment.domain.response.RickAndMortyCharacterResponse;
import com.assignment.domain.entity.Character;

public interface RickAndMortyCharacterService {

	RickAndMortyCharacterResponse getCharacter();
	
	Character getCharacterById(String characterId);

}
