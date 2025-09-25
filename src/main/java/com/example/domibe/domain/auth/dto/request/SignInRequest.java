package com.example.domibe.domain.auth.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {
  @NotBlank
  private String accountId;
  @NotBlank
  private String password;
}
