package com.example.Assignmnet.controller;

import com.example.Assignmnet.dto.AuthenticationDto;
import com.example.Assignmnet.dto.RegisterRequestDto;
import com.example.Assignmnet.dto.ResponseDto;
import com.example.Assignmnet.service.AuthenticationService;
//import com.example.Assignmnet.service.jwt.UserDetailsServiceImpl;
import com.example.Assignmnet.Configuration.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/Signup")
    public ResponseEntity<ResponseDto> Signup(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authenticationService.Signup(registerRequestDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticate(@Valid @RequestBody AuthenticationDto authenticationDto){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationDto));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto handleValidationExceptions(MethodArgumentNotValidException ex){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setRespondseCode("06");
        responseDto.setResponseMsg("Bad Request");
        return responseDto;
    }



}
