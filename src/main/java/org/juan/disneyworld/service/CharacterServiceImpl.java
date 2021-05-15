package org.juan.disneyworld.service;

import java.util.Date;
import java.util.List;

import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.enums.Status;
import org.juan.disneyworld.entity.Character;
import org.juan.disneyworld.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
public class CharacterServiceImpl implements CharacterService{
	
	@Autowired
	private CharacterRepository repo;
	
	@Override
	public List<Character> listAllCharacters() {
		
		return repo.findAll();
	}

	@Override
	public Character createCharacter(Character character) {
		character.setStatus(Status.CREATED);
		character.setCreatedAt(new Date());
		return repo.save(character);
	}

	@Override
	public Character updateCharacter(Character character) {
		Character characterDB = getCharacter(character.getId());
		if(characterDB == null) return null;
		characterDB.setAge(character.getAge());
		characterDB.setMovies(character.getMovies());
		characterDB.setName(character.getName());
		characterDB.setStory(character.getStory());
		characterDB.setWeight(character.getWeight());
		return repo.save(characterDB);
	}

	@Override
	public Character deleteCharacter(Long id) {
		Character characterDB = getCharacter(id);
		if(characterDB == null) return null;
		characterDB.setStatus(Status.DELETED);
		return repo.save(characterDB);
	}

	@Override
	public Character getCharacter(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Character> findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public List<Character> findByAge(int age) {
		return repo.findByAge(age);
	}

	@Override
	public List<Character> findByMovie(Movie movie) {
		return null;//repo.findByMovie(movie);
	}
	



}
