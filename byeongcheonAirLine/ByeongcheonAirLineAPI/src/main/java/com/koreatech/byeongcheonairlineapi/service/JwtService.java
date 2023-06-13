package com.koreatech.byeongcheonairlineapi.service;

import java.util.Map;

/**
 * 구현할 기능들
 * 1. JWT 토근 생성 로직.
 * 2. Access Token 생성 로직
 * 3. Refresh Token 생성 로직
 * 4. Access Token 유효성 검증 로직
 * 5. JWT 토큰에서 Account 얻는 로직
 */
public interface JwtService {

    /**
     * JWT 토큰을 생성하는 로직.
     * @param subject : ACCESS TOKEN 인지 REFRESH TOKEN인지 구별하는 요소.
     * @param key : Payload의 key
     * @param data: PayLoad의 value
     * @param expire:  토큰의 유효기간.
     * @return : 직렬화된 JWT 문자열.
     * @param <T> : Payload의 value를 범용적으로 사용하기 위해 (id 또는 계정 ... 타입이 모두 다름)
     */
    <T> String create(String subject, String key, T data, long expire);

    // ACCESS TOKEN 생성.
    <T> String createAccessToken(String key, T data);

    // REFRESH TOKEN 생성.
    <T> String createRefreshToken(String key, T data);

    // 토큰 유효성 검사.
    boolean checkToken(String jwt);

    /**
     * DB에 REFRESH 토큰을 저장하고 있음. DB에 저장되어 있는 토큰과 전달받은 토큰을 확인하기 위한 메솓,
     * @param token : 브라우저가 준 토큰
     * @return : 유저의 정보(토큰을 생성할 때 주었던 정보)를 반환해줌.
     */
    Map<String, Object> getUserInfoFromToken(String token);

    // getUserInfoFromToken 메소드의 결과 값으로 부터 토큰에 저장했던 정보를 얻음.
    String getInfo(String key, String jwt);


}
