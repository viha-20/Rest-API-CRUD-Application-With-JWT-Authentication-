package com.example.Assignmnet.service;

import com.example.Assignmnet.Configuration.JwtUtil;
import com.example.Assignmnet.dto.AuthenticationDto;
import com.example.Assignmnet.dto.AuthenticationResponseDto;
import com.example.Assignmnet.dto.RegisterRequestDto;
import com.example.Assignmnet.dto.ResponseDto;
import com.example.Assignmnet.entity.Role;
import com.example.Assignmnet.entity.UserData;
import com.example.Assignmnet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final UserRepository userRepository;



    public ResponseDto authenticate(AuthenticationDto authenticateRequestDto){
        ResponseDto responseDto = new ResponseDto();
        try{
            UserData user = userRepository.findByEmail(authenticateRequestDto.getEmail());
//            UserData user = new UserData();
//            user.setEmail("hello30@email.com");
//            user.setFirstName("heily");
//            user.setLastName("jesh");
//            user.setPassword("$2a$10$uA/3ejmb8s62fAKHAQLVueY8IDMSd5gRBtljjRly.cOgvaHeE41cC");
//            user.setRoles(Role.USER);
            log.info("User Found " +user.getFirstName() + " " + user.getLastName());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequestDto.getEmail(),authenticateRequestDto.getPassword()));
            var jwttoken = jwtUtil.generateToken((UserDetails) user);
            responseDto.setRespondseCode("00");
            responseDto.setResponseMsg("Success");
            responseDto.setContent(AuthenticationResponseDto.builder().jwtToken(jwttoken).build());

        }
        catch (BadCredentialsException exception){
            responseDto.setRespondseCode("03");
            responseDto.setResponseMsg("Invalid Credentials");
        }
        catch (NoSuchElementException elementException){
            responseDto.setRespondseCode("02");
            responseDto.setResponseMsg("No such user exists");
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseDto;

    }

    public ResponseDto Signup(RegisterRequestDto registerRequestDto){
        ResponseDto responseDto = new ResponseDto();
        try{
            var user = UserData
                    .builder()
                    .firstName(registerRequestDto.getFirstName())
                    .lastName(registerRequestDto.getLastName())
                    .email(registerRequestDto.getEmail())
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .roles(Role.USER).build();

            userRepository.save(user);
            responseDto.setRespondseCode("00");
            responseDto.setResponseMsg("Success");

        }
        catch (DataIntegrityViolationException exception){
            responseDto.setRespondseCode("04");
            responseDto.setResponseMsg("User Already Exists");
        }
        return responseDto;
    }
}
