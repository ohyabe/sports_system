//package com.example.login.util;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//import java.util.Date;
//
//public class JwtUtil {
//
//    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final long EXPIRATION_TIME = 3600000; // 1 hour
//
//    public static String generateToken(String userId) {
//        return Jwts.builder()
//                .setSubject(userId)
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(key)
//                .compact();
//    }
//
//    public static String getUserIdFromToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public static boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
