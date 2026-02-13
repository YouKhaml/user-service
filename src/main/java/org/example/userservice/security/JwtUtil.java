//package org.example.userservice.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.function.Function;
//@Component
//public class JwtUtil {
//    // Clé secrète (en prod, utilise une valeur sécurisée et en variable d'environnement !)
//    private final String SECRET_KEY = "une_super_cle_secrete_bien_longue_pour_le_token_jwt";
//
//    // Durée de validité du token (ex: 24h)
//    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
//
//    private Key getSignInKey() {
//        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//    }
//
//    // Générer un token pour un utilisateur
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // Extraire le nom d'utilisateur du token
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    // Vérifier si le token est valide pour cet utilisateur
//    public boolean isTokenValid(String token, String username) {
//        String tokenUsername = extractUsername(token);
//        return (username.equals(tokenUsername)) && !isTokenExpired(token);
//    }
//
//    // Vérifier si le token est expiré
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}