package com.assignment.domain.response;

import java.util.List;

import com.assignment.domain.dto.CharacterPageInfo;
import com.assignment.domain.entity.Character;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RickAndMortyCharacterResponse {
	
	@JsonProperty("info")
	CharacterPageInfo characterPageInfo;
	
	@JsonProperty("results")
	List<Character> Character;

}
