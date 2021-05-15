package org.juan.disneyworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.juan.disneyworld.service.CharacterService;
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
import org.springframework.web.bind.annotation.RestController;
import org.juan.disneyworld.entity.Character;

@RestController
@RequestMapping (value = "/characters")
public class CharacterController {

	@Autowired
	private CharacterService service;
	
	@GetMapping
	public ResponseEntity<List<Character>> listCharacters(){
		
		List<Character> characters = new ArrayList<>();
		characters = service.listAllCharacters();
		if(characters.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(characters);
	}
	
	@PostMapping
	public ResponseEntity<Character> createCharacter(@RequestBody Character character){
		Character characterCreate =  service.createCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterCreate);
    }
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<Character> updateProduct(@PathVariable("id") Long id, @RequestBody Character character){
		character.setId(id);
		Character characterDB =  service.updateCharacter(character);
        if (characterDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(characterDB);
    }
	
	 @DeleteMapping(value = "/{id}")
	    public ResponseEntity<Character> deleteCharacter(@PathVariable("id") Long id){
		 	Character characterDelete = service.deleteCharacter(id);
	        if (characterDelete == null){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(characterDelete);
	    }
	 
	 @GetMapping(value = "/{id}")
	    public ResponseEntity<Character> getCharacter(@PathVariable("id") Long id) {
		 Character character =  service.getCharacter(id);
	        if (null==character){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(character);
	    }

	
}
