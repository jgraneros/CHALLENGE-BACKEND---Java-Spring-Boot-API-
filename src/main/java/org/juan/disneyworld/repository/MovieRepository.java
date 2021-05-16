package org.juan.disneyworld.repository;

import java.util.List;

import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.enums.MovieGenre;
import org.juan.disneyworld.enums.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	public List<Movie> findByTitle(String title);
	public List<Movie> findByGenre(MovieGenre genre);
	public List<Movie> findByCharacters(org.juan.disneyworld.entity.Character character);
}
