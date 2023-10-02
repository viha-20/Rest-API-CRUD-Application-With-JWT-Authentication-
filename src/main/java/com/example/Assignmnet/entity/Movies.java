package com.example.Assignmnet.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movies {

    @Id
    @Column(name = "IMDB",unique = true)
    String imdb;
    @Column(name = "TITLE")
    String title;
    @Column(name = "DESCRIPTION")
    String description;
    @Column(name = "RATING")
    Double rating;
    @Column(name="CATEGORY")
    String category;
    @Column(name = "YEAR")
    int year;
    @Column(name = "image_url")
    String image_url;

   }
