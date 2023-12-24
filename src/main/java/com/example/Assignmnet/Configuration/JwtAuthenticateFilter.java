package com.example.Assignmnet.Configuration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;


@Component
@RequiredArgsConstructor
@Log4j2
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final UserDetailsService userDetailsService;
    //public   String HEADER_STRING = "authorization";

     String userEmail;


    @Override
    protected void doFilterInternal( HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException, ExpiredJwtException{

        try {
            final String token;
            String header_string = req.getHeader("Authorization");

            if (header_string==null || !header_string.startsWith("Bearer ")){
                if (req.getServletPath().contains("/api/v1/auth")){
                    final HttpSession session = req.getSession();
                    log.info("Session info for url: {}, Current session: {}, Requested session id: {}", req.getRequestURI(), session.getId(), req.getRequestedSessionId());
                    filterChain.doFilter(req,res);
                }
                else {
                    PrintWriter printWriter = res.getWriter();
                    printWriter.println("{\"responseCode\": \"03\",\"responseMsg\": \"Not Authorized\",\"content\": null}");
                    printWriter.close();
                }
                return;
            }

            token = header_string.substring(7);
            userEmail = jwtUtil.extractUsername(token);
            if (userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtUtil.isTokenValid(token,userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(req,res);
        }
        catch (SignatureException e){
            e.printStackTrace();
        }
        catch (AccessDeniedException exception){
            exception.printStackTrace();
        }

        catch (Exception exception){
            exception.printStackTrace();

        }


    }
}
