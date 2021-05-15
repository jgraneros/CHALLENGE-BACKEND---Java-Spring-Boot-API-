package org.juan.disneyworld.repository;

import java.util.List;

import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>{
	
	 List<Character> findByName(String name);
	 List<Character> findByAge(int age);
	 //List<Character> findByMovie(Movie movie);
	
}
