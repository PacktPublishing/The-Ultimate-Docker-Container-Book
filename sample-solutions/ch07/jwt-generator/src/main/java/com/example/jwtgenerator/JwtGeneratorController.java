package com.example.jwtgenerator;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
public class JwtGeneratorController {
  // this secret key needs to be used on the secured API too to work...
  private static final String SECRET_KEY = "mysecretkey";
  
  @PostMapping("/create-jwt")
  public final String getJwt() {
    var jwt = JWT.create()
      .withSubject("12345")
      .withIssuer("my-app")
      // token is valid for 1 hour
      .withExpiresAt(new Date(System.currentTimeMillis() + 3600*1000))
      // add scopes to token
      .withArrayClaim("scopes", new String[]{"SCOPE_read:resource", "SCOPE_write:resource"})
      // add partner and jurisdiction claims
      .withArrayClaim("partner", new String[]{"degenia"})
      .withArrayClaim("jurisdiction", new String[]{"deu"})
      // The algorithm needs to be compatible with what we use in OKTA
      .sign(Algorithm.HMAC256(SECRET_KEY));

    return jwt;
  }

  @GetMapping("/verify-jwt")
  public boolean verifyJwt(@RequestHeader("Authorization") String authHeader) {
    var jwt = authHeader.replace("Bearer ",   "");
    try {
      var algorithm = Algorithm.HMAC256(SECRET_KEY);
      JWT.require(algorithm)
        .build()
        .verify(jwt);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
