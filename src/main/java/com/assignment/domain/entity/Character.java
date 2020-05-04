package com.assignment.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
	
	private String id;
	
	private String name;
	
	private String status;
	
	private String species;
	
	private String type;
	
	private String gender;
	
	private Location origin;
	
	private Location location;
	
	@JsonProperty("image")
	private String imageUrl;
	
	List<String> episode;
	
	private String url;
	
	private LocalDateTime created; 
}
