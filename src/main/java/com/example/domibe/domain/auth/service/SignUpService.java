package com.example.domibe.domain.auth.service;

import com.example.domibe.domain.auth.dto.SignUpRequest;
import com.example.domibe.domain.auth.dto.TokenResponse;
import com.example.domibe.domain.auth.exception.AccountIdAlreadyExistsException;
import com.example.domibe.domain.user.User;
import com.example.domibe.domain.user.UserRepository;
import com.example.domibe.global.security.auth.Role;
import com.example.domibe.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SignUpService {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public TokenResponse execute(SignUpRequest signUpRequest) {

    if(userRepository.existsByAccountId(signUpRequest.getAccountId())) {
      throw new AccountIdAlreadyExistsException();
    }


    User user = User.builder()
        .accountId(signUpRequest.getAccountId())
        .password(passwordEncoder.encode(signUpRequest.getPassword()))
        .studentNumber(signUpRequest.getStudentNumber())
        .role(Role.ROLE_USER)
        .createdAt(LocalDate.now())
        .build();

    userRepository.save(user);

    String refreshToken = jwtTokenProvider.generateRefreshToken(signUpRequest.getAccountId());
    String accessToken = jwtTokenProvider.generateAccessToken(signUpRequest.getAccountId());
    return new TokenResponse(accessToken, refreshToken);

  }
}
