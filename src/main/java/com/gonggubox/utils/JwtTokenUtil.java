//package com.gonggubox.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//
//public class JwtTokenUtil {
//
//    //username꺼내기
//    public static String getUserName(String token, String secretKey){
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
//                .getBody().get("userName", String.class);
//    }
//
//    public static boolean isExpired(String token, String secretKey){
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
//                .getBody().getExpiration().before(new Date());
//    }
//    public static String createToken(String username, String key, long expireTimeMs){
//        Claims claims = Jwts.claims(); //원하는 정보 담아놓을 수 있는 공간
//        claims.put("username", username); //token열면 username 들어있게
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date(System.currentTimeMillis())) //만든 날짜
//                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) //끝나는 날짜
//                .signWith(SignatureAlgorithm.HS256, key) //key이용하여 암호화
//                .compact();
//    }
//}
