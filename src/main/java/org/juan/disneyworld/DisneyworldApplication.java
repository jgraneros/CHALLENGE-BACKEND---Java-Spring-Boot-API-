package org.juan.disneyworld;

import org.juan.disneyworld.entity.Character;
import org.juan.disneyworld.entity.Movie;
import org.juan.disneyworld.enums.MovieGenre;
import org.juan.disneyworld.enums.Status;
import org.juan.disneyworld.repository.CharacterRepository;
import org.juan.disneyworld.repository.MovieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class DisneyworldApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
		SpringApplication.run(DisneyworldApplication.class, args);
		
		MovieRepository movieRepo = configurableApplicationContext.getBean(MovieRepository.class);
		CharacterRepository characterRepo = configurableApplicationContext.getBean(CharacterRepository.class);
		
		
		Character ch1 = Character.builder()
				.name("Character 1")
				.age(40)
				.createdAt(new Date())
				.story("")
				.status(Status.CREATED)
				.build();
		
		Character ch2 = Character.builder()
				.name("Character 2")
				.age(40)
				.createdAt(new Date())
				.story("")
				.status(Status.CREATED)
				.build();
		
		Character ch3 = Character.builder()
				.name("Character 3")
				.age(40)
				.createdAt(new Date())
				.story("")
				.status(Status.CREATED)
				.build();
		
		List<Character> characters = Arrays.asList(ch1, ch2,ch3);
		
		
		
		Movie movie1 = Movie.builder()
				.title("Movie 1")
				.qualification(4)
				.createdAt(new Date())
				.genre(MovieGenre.Action)
				.status(Status.CREATED)
				.build();
		
		Movie movie2 = Movie.builder()
				.title("Movie 2")
				.qualification(4)
				.createdAt(new Date())
				.genre(MovieGenre.Action)
				.status(Status.CREATED)
				.build();
		
		List<Movie> movies = Arrays.asList(movie1, movie2);
		
		movie1.addCharacter(ch1);
		movie1.addCharacter(ch2);
		
		movie2.addCharacter(ch1);
		movie2.addCharacter(ch3);
		
		characterRepo.saveAll(characters);
		
		movieRepo.saveAll(movies);
			
	}


	
	

}
