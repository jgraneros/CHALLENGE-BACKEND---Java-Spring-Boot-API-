package org.juan.disneyworld.service;

import java.util.List;

import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.enums.MovieGenre;
import org.juan.disneyworld.enums.Order;
import org.juan.disneyworld.entity.Character;

public interface MovieService {
	public List<Movie> listAllMovies();
	public Movie createMovie(Movie movie);
	public Movie updateMovie(Movie movie);
	public Movie deleteMovie(Long id);
	public Movie getMovie(Long id);
	
	//Busqueda de peliculas
	public List<Movie> findByName(String name);
	public List<Movie> findByGenre(MovieGenre genre);
	public List<Movie> findByMovie(Order order);

}
