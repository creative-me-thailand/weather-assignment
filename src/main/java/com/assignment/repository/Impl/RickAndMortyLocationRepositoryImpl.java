package com.assignment.repository.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.assignment.domain.entity.Location;
import com.assignment.repository.RickAndMortyLocationRepository;

@Repository
public class RickAndMortyLocationRepositoryImpl implements RickAndMortyLocationRepository{
	
	private RestTemplate restTemplate;

	@Autowired
	public RickAndMortyLocationRepositoryImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Location findById(String locationId) {

		ResponseEntity<Location> result = restTemplate.exchange(
				"https://rickandmortyapi.com/api/location/"+locationId, HttpMethod.GET, null,
				Location.class);
		return result.getBody();
	}

	@Override
	public Location findByUrl(String url) {

		ResponseEntity<Location> result = restTemplate.exchange(
				url, HttpMethod.GET, null,
				Location.class);
		return result.getBody();
	}
}
