package com.example.domibe.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class ReissueRequest {
  private String accountId;
  private String refreshToken;
}
