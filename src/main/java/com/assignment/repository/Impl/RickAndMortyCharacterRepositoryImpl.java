package com.assignment.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.assignment.domain.entity.Character;
import com.assignment.domain.response.RickAndMortyCharacterResponse;
import com.assignment.repository.RickAndMortyCharacterRepository;

@Repository
public class RickAndMortyCharacterRepositoryImpl implements RickAndMortyCharacterRepository{
	
	private RestTemplate restTemplate;

	@Autowired
	public RickAndMortyCharacterRepositoryImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public RickAndMortyCharacterResponse findCharacter() {

		ResponseEntity<RickAndMortyCharacterResponse> result = restTemplate.exchange(
				"https://rickandmortyapi.com/api/character/", HttpMethod.GET, null,
				RickAndMortyCharacterResponse.class);
		return result.getBody();
	}

	@Override
	public Character findById(String characterId) {

		ResponseEntity<Character> result = restTemplate.exchange(
				"https://rickandmortyapi.com/api/character/"+characterId, HttpMethod.GET, null,
				Character.class);
		return result.getBody();
	}

}
