/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.juan.disneyworld.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.juan.disneyworld.enums.Status;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author juan
 */

@Data
@Entity
@Table(name = "character")
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private int age;
    private float weight;
    private Date createdAt;
    private String story;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "characters")
    private List<Movie> movies;
    
    @Enumerated(EnumType.STRING)
    Status status;
    
    public void addMovie(Movie m) {
    	if(this.movies == null) this.movies = new ArrayList<>();
    	this.movies.add(m);
    }
}
