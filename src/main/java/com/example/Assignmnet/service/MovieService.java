package com.example.Assignmnet.service;


import com.example.Assignmnet.dto.MovieDto;
import com.example.Assignmnet.dto.ResponseDto;
import com.example.Assignmnet.entity.Movies;
import com.example.Assignmnet.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MovieDto saveMovie(MovieDto movieDto){
        movieRepository.save(modelMapper.map(movieDto,Movies.class));
        return movieDto;
    }

    public ResponseDto getAllMovies(){
        List<MovieDto> movieDtos = new ArrayList<>();
        List<Movies> moviesList = movieRepository.findAll();

       for (Movies movie:moviesList){
            MovieDto movieDto = modelMapper.map(movie,MovieDto.class);
            movieDtos.add(movieDto);
        }
       ResponseDto responseDto = new ResponseDto();
       if (movieDtos.size()>0){
           responseDto.setRespondseCode("00");
           responseDto.setResponseMsg("Success");
           responseDto.setContent(movieDtos);
       }
       else {
           responseDto.setRespondseCode("02");
           responseDto.setResponseMsg("No Movies Found");
       }
        return responseDto;

    }

    public ResponseDto getMovieByImdb(String imdb){

            List<Movies> movies = movieRepository.findAll();

            if (movies.size()>0){}

            ResponseDto responseDto = new ResponseDto();
            if (movies.size()>=1){
                responseDto.setRespondseCode("00");
                responseDto.setResponseMsg("Success");
                responseDto.setContent(modelMapper.map(movies.get(0),MovieDto.class));

            }
            else {
                responseDto.setRespondseCode("02");
                responseDto.setResponseMsg("No such Movie Found");
            }
            return responseDto;
    }


    public ResponseDto updateMovie(Movies movies,String imdb){
        Movies updateMovie;
        List<Movies> moviesList = movieRepository.getByImdb(imdb);
        ResponseDto responseDto =  new ResponseDto();

        if (moviesList.size()==1){
            updateMovie = moviesList.get(0);

            if (movies.getDescription()!=null){
                updateMovie.setDescription(movies.getDescription());
            }
            if (movies.getCategory()!=null){
                updateMovie.setCategory(movies.getCategory());
            }
            if (movies.getTitle()!=null){
                updateMovie.setTitle(movies.getTitle());
            }
            if (movies.getYear()!=0){
                updateMovie.setYear(movies.getYear());
            }
            if (movies.getRating()!=0.0){
                updateMovie.setRating(movies.getRating());
            }
            if (movies.getImage_url()!=null){
                updateMovie.setImage_url(movies.getImage_url());
            }

            movieRepository.save(updateMovie);
            responseDto.setRespondseCode("00");
            responseDto.setResponseMsg("Success");
        }
        else {
            responseDto.setRespondseCode("02");
            responseDto.setResponseMsg("No Such Movie Exists");

        }

        return responseDto;

    }

    public ResponseDto deleteMovie(String imdb){

        movieRepository.deleteByImdb(imdb);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setRespondseCode("00");
        responseDto.setResponseMsg("Success");
        responseDto.setContent("null");

        return responseDto;




    }




}
