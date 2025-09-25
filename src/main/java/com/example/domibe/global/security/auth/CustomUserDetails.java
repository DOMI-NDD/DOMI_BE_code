package com.example.domibe.global.security.auth;

import com.example.domibe.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

  private final User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String role = user.getRole().toString();
    if (!role.startsWith("ROLE_")) {
      role = "ROLE_" + role;
    }
    return Collections.singleton(new SimpleGrantedAuthority(role));
  }
  public User getUser() {
    return user;
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return "";
  }
}
