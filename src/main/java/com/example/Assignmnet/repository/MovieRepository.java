package com.example.Assignmnet.repository;

import com.example.Assignmnet.dto.MovieDto;
import com.example.Assignmnet.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movies,String> {

    void deleteByImdb(String imdb);
    List<Movies> getByImdb(String imdb);

}
