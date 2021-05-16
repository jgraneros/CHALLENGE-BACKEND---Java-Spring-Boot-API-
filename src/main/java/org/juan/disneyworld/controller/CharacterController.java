package org.juan.disneyworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.juan.disneyworld.service.CharacterService;
import org.juan.disneyworld.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

import org.juan.disneyworld.entity.Character;
import org.juan.disneyworld.entity.Movie;

@RestController
@RequestMapping("/")
@Slf4j
public class CharacterController {

	@Autowired
	private CharacterService characterService;
	@Autowired
	private MovieService moviesService;
	
	@GetMapping(value = "characters")
	public ResponseEntity<List<Character>> listCharacters(){
		
		List<Character> characters = new ArrayList<>();
		characters = characterService.listAllCharacters();
		if(characters.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(characters);
	}
	
	@PostMapping(value = "characters")
	public ResponseEntity<Character> createCharacter(@RequestBody Character character){
		Character characterCreate =  characterService.createCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterCreate);
    }
	
	@PutMapping(value = "characters/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable("id") Long id, @RequestBody Character character){
		character.setId(id);
		Character characterDB =  characterService.updateCharacter(character);
        if (characterDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(characterDB);
    }
	
	 @DeleteMapping(value = "characters/{id}")
	    public ResponseEntity<Character> deleteCharacter(@PathVariable("id") Long id){
		 	Character characterDelete = characterService.deleteCharacter(id);
	        if (characterDelete == null){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(characterDelete);
	    }
	 
	 @GetMapping(value = "characters/{id}")
	    public ResponseEntity<Character> getCharacter(@PathVariable("id") Long id) {
		 Character character =  characterService.getCharacter(id);
	        if (null==character){
	            return ResponseEntity.notFound().build();
	        }
	       List<Movie> movies = moviesService.findByCharacters(character);
	       character.setMovies(movies);
	        
	        System.out.println(character.getMovies());
	        return ResponseEntity.ok(character);
	    }
	 
	 @GetMapping(value = "characters", params = "name")
	    public ResponseEntity<List<Character>> getCharactersByName(@RequestParam(name = "name", required = true) String name) {
		 List<Character> characters =  characterService.findByName(name);
	        if (characters.isEmpty()){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(characters);
	    }
	 
	 @GetMapping(value = "characters", params = "age")
	    public ResponseEntity<List<Character>> getCharactersByAge(@RequestParam(name = "age", required = true) int age) {
		 List<Character> characters =  characterService.findByAge(age);
	        if (characters.isEmpty()){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(characters);
	    }
	 
	 @GetMapping(value = "characters", params = "idMovie")
	    public ResponseEntity<List<Character>> getCharactersByMovieId(@RequestParam(name = "idMovie", required = true) Long idMovie) {
		 Movie movie =  moviesService.getMovie(idMovie);
	        if (movie == null)return ResponseEntity.notFound().build();
	         
	       List<Character> characters = characterService.findByMovies(movie);
	       
	       if(characters.isEmpty())return ResponseEntity.notFound().build();
	       
	        return ResponseEntity.ok(characters);
	    }
	 
	 

	
}
