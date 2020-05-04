package com.assignment.repository;

import com.assignment.domain.entity.Location;

public interface RickAndMortyLocationRepository {

	Location findById(String locationId);
	
	Location findByUrl(String url);

}
