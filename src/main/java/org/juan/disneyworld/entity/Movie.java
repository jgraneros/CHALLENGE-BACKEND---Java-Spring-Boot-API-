package org.juan.disneyworld.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.juan.disneyworld.enums.MovieGenre;
import org.juan.disneyworld.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author juan
 */


@Data
@Entity
@Table(name = "movie")
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String title;
    private Date createdAt;
    private int qualification;
    private MovieGenre genre;
    private Status status;
    
    @JoinTable(
    		name= "movie_character",
    		joinColumns = @JoinColumn(name = "movie_id"),
    		inverseJoinColumns = @JoinColumn(name = "character_id")
    		)
    
    @ManyToMany
    private List<Character> characters;
    
    public void addCharacter(Character ch) {
    	if(this.characters == null) this.characters = new ArrayList<>();
    	this.characters.add(ch);
    }
}
