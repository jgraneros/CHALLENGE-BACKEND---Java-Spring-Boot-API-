package org.juan.disneyworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.juan.disneyworld.entity.Character;
import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.enums.MovieGenre;
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

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private CharacterService characterService;
	@Autowired
	private MovieService moviesService;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Movie>> listMovies(){
		
		List<Movie> movies = new ArrayList<>();
		movies = moviesService.listAllMovies();
		if(movies.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(movies);
	}
	
	@PostMapping
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
		Movie movieCreate =  moviesService.createMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieCreate);
    }
	
	@PutMapping(value = "/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie){
		movie.setId(id);
		Movie movieDB =  moviesService.updateMovie(movie);
        if (movieDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDB);
    }
	
	 @DeleteMapping(value = "/{id}")
	    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") Long id){
		 Movie movieDelete = moviesService.deleteMovie(id);
	        if (movieDelete == null){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(movieDelete);
	    }
	 
	 @GetMapping(value = "/{id}")
	    public ResponseEntity<Movie> getMovie(@PathVariable("id") Long id) {
		 Movie movie =  moviesService.getMovie(id);
	        if (null==movie){
	            return ResponseEntity.notFound().build();
	        }
	        
	        return ResponseEntity.ok(movie);
	    }
	 
	 @GetMapping(params = "name")
	    public ResponseEntity<List<Movie>> getMoviesByName(@RequestParam(name = "name", required = true) String name) {
		 List<Movie> movies =  moviesService.findByName(name);
	        if (movies.isEmpty()){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(movies);
	    }
	 
	 @GetMapping(params = "genre")
	    public ResponseEntity<List<Movie>> getCharactersByAge(@RequestParam(name = "genre", required = true) MovieGenre genre) {
		 List<Movie> movies =  moviesService.findByGenre(genre);
	        if (movies.isEmpty()){
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(movies);
	    }
	 
	 
}
