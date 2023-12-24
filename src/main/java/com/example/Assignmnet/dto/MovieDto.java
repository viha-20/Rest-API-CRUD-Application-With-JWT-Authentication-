package com.example.Assignmnet.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {

    String imdb;
    String title;
    String description;
    Double rating;
    String category;
    int year;
    @Column(name = "image_url")
    String image_url;

}
