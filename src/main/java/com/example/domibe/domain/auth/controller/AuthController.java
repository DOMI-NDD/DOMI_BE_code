package com.example.domibe.domain.auth.controller;

import com.example.domibe.domain.auth.dto.SignInRequest;
import com.example.domibe.domain.auth.dto.SignUpRequest;
import com.example.domibe.domain.auth.dto.TokenResponse;
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
  public ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
    return ResponseEntity.ok(signUpService.execute(signUpRequest));
  }

  @PostMapping("/sign-in")
  public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
    return ResponseEntity.ok(signInService.execute(signInRequest));
  }
}
