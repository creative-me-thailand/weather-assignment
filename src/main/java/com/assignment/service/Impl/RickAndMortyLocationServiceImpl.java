package com.assignment.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.domain.entity.Character;
import com.assignment.domain.entity.Location;
import com.assignment.repository.RickAndMortyCharacterRepository;
import com.assignment.repository.RickAndMortyLocationRepository;
import com.assignment.service.RickAndMortyLocationService;

@Service
public class RickAndMortyLocationServiceImpl implements RickAndMortyLocationService{
	
	private RickAndMortyCharacterRepository characterRepository;
	private RickAndMortyLocationRepository locationRepository;
	
	@Autowired
	public RickAndMortyLocationServiceImpl(RickAndMortyCharacterRepository characterRepository
			,RickAndMortyLocationRepository locationRepository) {
		this.characterRepository = characterRepository;
		this.locationRepository = locationRepository;
		
	}

	@Override
	public Location getById(String locationId) {
		return locationRepository.findById(locationId);
	}

	@Override
	public Location getByCharacterId(String characterId) {
		Character character = characterRepository.findById(characterId);
		Location location = character.getLocation();
		String locationUrl = location.getUrl();
		return locationRepository.findByUrl(locationUrl);
	}

}
