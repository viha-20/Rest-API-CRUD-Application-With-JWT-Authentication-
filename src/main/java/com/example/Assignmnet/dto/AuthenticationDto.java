package com.example.Assignmnet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationDto {

    @NotNull
    private String email;
    @NotNull
    private String password;

}
