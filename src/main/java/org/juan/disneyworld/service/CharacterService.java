package org.juan.disneyworld.service;

import java.util.List;

import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.entity.Character;

public interface CharacterService {
	public List<Character> listAllCharacters();
	public Character createCharacter(Character character);
	public Character updateCharacter(Character character);
	public Character deleteCharacter(Long id);
	public Character getCharacter(Long id);
	
	//Busqueda de personajes
	public List<Character> findByName(String name);
	public List<Character> findByAge(int age);
	public List<Character> findByMovies(Movie movie);
}
