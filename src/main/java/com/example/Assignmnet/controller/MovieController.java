package com.example.Assignmnet.controller;


import com.example.Assignmnet.dto.MovieDto;
import com.example.Assignmnet.dto.ResponseDto;
import com.example.Assignmnet.entity.Movies;
import com.example.Assignmnet.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getAll")
    public ResponseDto getAllMovies(){
        return movieService.getAllMovies();
    }

    @PostMapping("/saveMovie")
    public MovieDto saveMovie(@RequestBody MovieDto movieDto){
        return movieService.saveMovie(movieDto);
    }


    @GetMapping("/getMovie/{imdb}")
    public ResponseDto getMovie(@PathVariable("imdb") String imdb){
        //MovieDto movieDto = movieService.get(imdb);

        //return movieDto;
        return movieService.getMovieByImdb(imdb);
    }

    @PutMapping("/updateMovie/{imdb}")
    public ResponseDto updateMovie(@RequestBody Movies movies, @PathVariable String imdb){
        return movieService.updateMovie(movies,imdb);

    }

    @DeleteMapping("/deleteMovie/{imdb}")
    public ResponseDto deleteMovie(@PathVariable("imdb") String imdb ){
        return movieService.deleteMovie(imdb);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto handleValidationExceptions(MethodArgumentNotValidException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setRespondseCode("06");
        responseDto.setResponseMsg("Bad Request");
        return responseDto;
    }

}
