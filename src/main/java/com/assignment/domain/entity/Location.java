package com.assignment.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	
	private String id;
	
	private String name;
	
	private String type;
	
	private String dimension;
	
	private List<String> residents;
	
	private String url;
	
	private LocalDateTime created;

}

