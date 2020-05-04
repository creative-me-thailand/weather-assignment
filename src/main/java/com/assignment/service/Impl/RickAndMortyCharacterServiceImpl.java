package com.assignment.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.domain.entity.Character;
import com.assignment.domain.response.RickAndMortyCharacterResponse;
import com.assignment.repository.RickAndMortyCharacterRepository;
import com.assignment.service.RickAndMortyCharacterService;

@Service
public class RickAndMortyCharacterServiceImpl implements RickAndMortyCharacterService {

	private RickAndMortyCharacterRepository characterRepository;

	@Autowired
	public RickAndMortyCharacterServiceImpl(RickAndMortyCharacterRepository characterRepository) {
		this.characterRepository = characterRepository;
	}

	@Override
	public RickAndMortyCharacterResponse getCharacter() {

		return characterRepository.findCharacter();
	}

	@Override
	public Character getCharacterById(String characterId) {
		return characterRepository.findById(characterId);
	}

}
