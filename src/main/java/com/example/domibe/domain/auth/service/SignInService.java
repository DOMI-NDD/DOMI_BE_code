package com.example.domibe.domain.auth.service;

import com.example.domibe.domain.auth.dto.request.SignInRequest;
import com.example.domibe.domain.auth.dto.response.SignInResponse;
import com.example.domibe.domain.auth.exception.AccountNotFoundException;
import com.example.domibe.domain.auth.exception.IncorrectPasswordException;
import com.example.domibe.domain.user.User;
import com.example.domibe.domain.user.UserRepository;
import com.example.domibe.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInService {
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SignInResponse execute(SignInRequest signInRequest) {
    User user = userRepository.findByAccountId(signInRequest.getAccountId())
        .orElseThrow((AccountNotFoundException::new));

    if(!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
      throw new IncorrectPasswordException();
    }

    String accessToken = jwtTokenProvider.generateAccessToken(signInRequest.getAccountId());
    String refreshToken = jwtTokenProvider.generateRefreshToken(signInRequest.getAccountId());

    return new SignInResponse(accessToken, refreshToken, user.getAccountId(),user.getUsername());
  }

}
