package com.assignment.service;

import com.assignment.domain.entity.Location;

public interface RickAndMortyLocationService {
	
	Location getById(String locationId);
	
	Location getByCharacterId(String characterId);

}
