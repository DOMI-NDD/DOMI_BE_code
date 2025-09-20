package com.example.domibe.domain.auth.dto.request;


import lombok.Getter;

@Getter
public class SignInRequest {
  private String accountId;
  private String password;
}
