package com.assignment.repository;

import com.assignment.domain.response.RickAndMortyCharacterResponse;
import com.assignment.domain.entity.Character;

public interface RickAndMortyCharacterRepository {
	
	RickAndMortyCharacterResponse findCharacter();
	
	Character findById(String characterId);

}
