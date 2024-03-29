package com.example.VodReco.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
//    @Size(min = 3, max = 50)
    private String email;

    @NotNull
//    @Size(min = 3, max = 100)
    private String password;
}
