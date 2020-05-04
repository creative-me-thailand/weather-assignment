package com.assignment.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterPageInfo {
	
	private Integer count;
	
	private Integer pages;
	
	@JsonProperty("next")
	private String nextPageUrl;
	
	@JsonProperty("prev")
	private String prevPageUrl;

}
