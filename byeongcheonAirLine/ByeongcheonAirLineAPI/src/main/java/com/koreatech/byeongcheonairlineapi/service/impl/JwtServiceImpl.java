package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.exception.UnAuthorizeException;
import com.koreatech.byeongcheonairlineapi.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    private static final int MINUTES = 1;
    private static final int WEEKS = 2;
    private static final String SALT = "fenndqwkdwqfnnqd99823jjkfdwadafef2323224241321dbkwdwrgwefewf";


    @Override
    public <T> String create(String subject, String key, T data, long expire) {
        // 페이로드를 구성할 클레임들 설정.
        Claims claims = Jwts.claims()
                // 토큰 종류 설정
                .setSubject(subject)
                // 토큰 생성일 설정
                .setIssuedAt(new Date())
                // 토큰 만료기간 설정.
                .setExpiration(new Date(System.currentTimeMillis() + expire));
        // 클레임 삽입.
        claims.put(key, data);


        // JWT 만드는 로직.
        String jwt = Jwts.builder()
                // 헤더 구성 => 토큰 타입만 구성, 알고리즘은 구성 안함.
                .setHeaderParam("typ", "JWT")
                // 페이로드 구성.
                .setClaims(claims)
                // 시그니처 구성.
//                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .signWith(this.generateKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }
    private Key generateKey() {
        byte[] byteKey = Decoders.BASE64.decode(SALT);
        return Keys.hmacShaKeyFor(byteKey);

    }

    @Override
    public <T> String createAccessToken(String key, T data) {
        return create("access-token", key, data, 1000 * 60 * MINUTES);
    }

    @Override
    public <T> String createRefreshToken(String key, T data) {
//        return create("refresh-token", key, data, 1000 * 60 * 60 * 24 * 7 * WEEKS);
        return create("refresh-token", key, data, 1000 * 60 * MINUTES * 5);
    }

    @Override
    public boolean checkToken(String jwt) {
        try{
            // 서명 확인. => 서명을 통해 위 변조 확인 , 유효기간 검사.
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(this.generateKey()).build().parseClaimsJws(jwt);
            log.debug("Claims : {}" , claims);
            return true;
        }catch(Exception e){
            log.error("error", e);
            return false;
        }
    }

    @Override
    public Map<String, Object> getUserInfoFromToken(String token) {
        try {
            // 토큰에서 클레임을 꺼낸다.
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(this.generateKey()).build().parseClaimsJws(token);
            Map<String, Object> body = claims.getBody();
            log.debug("body : {}", body);
            return body;
        }catch (Exception e) {
            throw new UnAuthorizeException();
        }
    }

    @Override
    public String getInfo(String key, String token) {
        return (String)this.getUserInfoFromToken(token).get(key);
    }


}
