package com.example.domibe.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInResponse {
  private String accessToken;
  private String refreshToken;
  private String username;
}
