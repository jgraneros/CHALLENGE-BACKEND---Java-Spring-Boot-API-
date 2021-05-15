package org.juan.disneyworld.service;

import java.util.Date;
import java.util.List;

import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.enums.Status;
import org.juan.disneyworld.enums.MovieGenre;
import org.juan.disneyworld.enums.Order;
import org.juan.disneyworld.entity.Character;
import org.juan.disneyworld.repository.CharacterRepository;
import org.juan.disneyworld.repository.MovieRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository repo;
	
	@Override
	public List<Movie> listAllMovies() {
		return repo.findAll();
	}

	@Override
	public Movie createMovie(Movie movie) {
		movie.setStatus(Status.CREATED);
		movie.setCreatedAt(new Date());
		return repo.save(movie);
		
	}

	@Override
	public Movie updateMovie(Movie movie) {
		Movie movieDB = getMovie(movie.getId());
		if(movieDB == null) return null;
		movieDB.setCharacters(movie.getCharacters());
		movieDB.setGenre(movie.getGenre());
		movieDB.setQualification(movie.getQualification());
		movieDB.setTitle(movie.getTitle());
		
		return repo.save(movieDB);
	}

	@Override
	public Movie deleteMovie(Long id) {
		Movie movieDB = getMovie(id);
		if(movieDB == null) return null;
		movieDB.setStatus(Status.DELETED);
		return repo.save(movieDB);
	}

	@Override
	public Movie getMovie(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Movie> findByName(String name) {
		return repo.findByTitle(name);
	}

	@Override
	public List<Movie> findByGenre(MovieGenre genre) {
		// TODO Auto-generated method stub
		return repo.findByGenre(genre);
	}

	@Override
	public List<Movie> findByMovie(Order order) {
		return null;//repo.findByMovie(order);
	}
	
	
	
	



}
