//package com.example.Assignmnet.service.jwt;
//
//import com.example.Assignmnet.dto.ResponseDto;
//import com.example.Assignmnet.entity.UserData;
//import com.example.Assignmnet.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        UserData userData = userRepository.findFirstByEmail(email);
//
//        if (userData==null){
//            throw new UsernameNotFoundException("User not found",null);
//        }
//        return new User(userData.getEmail(),userData.getPassword(),new ArrayList<>());
//
//    }
//}
