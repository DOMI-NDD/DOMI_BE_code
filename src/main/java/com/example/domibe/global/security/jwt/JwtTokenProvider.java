package com.example.domibe.global.security.jwt;

import com.example.domibe.global.security.auth.CustomUserDetails;
import com.example.domibe.global.security.auth.CustomUserDetailsService;
import com.example.domibe.global.security.exception.JwtExpiredException;
import com.example.domibe.global.security.exception.JwtInvalidException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;
  private final CustomUserDetailsService authDetailsService;
  private final RedisTemplate<String,String> redisTemplate;
  private final static String ACCESS_TOKEN = "access_token";
  private final static String REFRESH_TOKEN = "refresh_token";
  private final static String REDIS_PREFIX = "RT:";

  private SecretKey secretKey;

  @PostConstruct
  public void init() {
    this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
  }

  //토큰 생성기능
  public String generateAccessToken(String accountId) {
    return generateToken(accountId,ACCESS_TOKEN,jwtProperties.getAccessTokenExpiresIn());

  }

  public String generateRefreshToken(String accountId) {
    String refreshToken=generateToken(accountId,REFRESH_TOKEN,jwtProperties.getRefreshTokenExpiresIn());
    String key =REDIS_PREFIX+accountId;
    redisTemplate.opsForValue().set(key, refreshToken, jwtProperties.getRefreshTokenExpiresIn(), TimeUnit.MILLISECONDS);
    return refreshToken;

  }

  public String generateToken(String accountId,String type,Long time) {
    Date now = new Date();
    return Jwts.builder()
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .claim("type", type)
        .setSubject(accountId)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime()+time))
        .compact();
  }

  //토큰에서 값 가져오기
  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.replace("Bearer ", "");
    }
    return null;
  }

  //토큰의 유효성을 감사
  public boolean validateToken(String token){
    try{
      Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token)
          .getBody();
      return true;
    }catch (ExpiredJwtException e){
      throw new JwtExpiredException();

    } catch (JwtException e) {
      throw new JwtInvalidException();
    }
  }

  public void validateRefreshToken(String accountId,String refreshToken) {

    String key = REDIS_PREFIX + accountId;
    String storedRefreshToken = redisTemplate.opsForValue().get(key);

    if(storedRefreshToken==null) throw  new JwtExpiredException();

    if(!refreshToken.equals(storedRefreshToken)) throw new JwtInvalidException();

    validateToken(refreshToken);

  }

  public String reissueAccessToken(String accountId,String refreshToken) {
    validateRefreshToken(accountId,refreshToken);

    return generateAccessToken(accountId);
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String token) {
    Claims claims=getClaims(token);
    CustomUserDetails customUserDetails=(CustomUserDetails) authDetailsService.loadUserByUsername(claims.getSubject());
    return new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());
  }

  private Claims getClaims(String token) {
    try {
      return Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token)
          .getBody();
    } catch (JwtExpiredException e) {
      throw new JwtExpiredException();
    }catch (JwtInvalidException e){
      throw new JwtInvalidException();
    }
  }
}
