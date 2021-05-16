package org.juan.disneyworld.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.juan.disneyworld.enums.MovieGenre;
import org.juan.disneyworld.enums.Status;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @JoinTable(
    		name= "movie_character",
    		joinColumns = @JoinColumn(name = "movie_id"),
    		inverseJoinColumns = @JoinColumn(name = "character_id")
    		)
   
    @ToString.Exclude
    @ManyToMany
    private List<Character> characters;
    
    public void addCharacter(Character ch) {
    	if(this.characters == null) this.characters = new ArrayList<>();
    	this.characters.add(ch);
    }
}
