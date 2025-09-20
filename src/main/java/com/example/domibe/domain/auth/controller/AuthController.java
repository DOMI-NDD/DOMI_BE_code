package com.example.domibe.domain.auth.controller;

import com.example.domibe.domain.auth.dto.request.SignInRequest;
import com.example.domibe.domain.auth.dto.request.SignUpRequest;
import com.example.domibe.domain.auth.dto.response.TokenResponse;
import com.example.domibe.domain.auth.service.SignInService;
import com.example.domibe.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auths")
@RequiredArgsConstructor
public class AuthController {

  private final SignInService signInService;
  private final SignUpService signUpService;

  @PostMapping("/sign-up")
  public TokenResponse signUp(@RequestBody SignUpRequest signUpRequest) {
    return signUpService.execute(signUpRequest);
  }

  @PostMapping("/sign-in")
  public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
    return signInService.execute(signInRequest);
  }
}
