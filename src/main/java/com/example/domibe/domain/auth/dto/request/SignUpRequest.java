package com.example.domibe.domain.auth.dto;

import com.example.domibe.global.security.auth.Role;
import lombok.Getter;

import java.util.Date;

@Getter
public class SignUpRequest {
  private String accountId;
  private String password;
  private Date createdAt;
  private int studentNumber;
  private Role role;
}
