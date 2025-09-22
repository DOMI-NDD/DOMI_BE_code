package com.example.domibe.domain.auth.service;

import com.example.domibe.domain.auth.dto.request.ReissueRequest;
import com.example.domibe.domain.auth.dto.response.TokenResponse;
import com.example.domibe.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReissueService {
  private final JwtTokenProvider jwtTokenProvider;

  public TokenResponse execute(ReissueRequest reissueRequest) {
    String accountId = reissueRequest.getAccountId();
    String refreshToken = reissueRequest.getRefreshToken();

    String newAccessToken = jwtTokenProvider.reissueAccessToken(accountId, refreshToken);

    return new TokenResponse(newAccessToken, refreshToken);
  }
}
