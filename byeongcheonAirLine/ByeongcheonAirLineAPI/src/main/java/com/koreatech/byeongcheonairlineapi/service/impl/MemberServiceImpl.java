package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.MemberDto;
import com.koreatech.byeongcheonairlineapi.dto.SignUpDto;
import com.koreatech.byeongcheonairlineapi.dto.domain.Member;
import com.koreatech.byeongcheonairlineapi.exception.UnAuthorizeException;
import com.koreatech.byeongcheonairlineapi.mapper.MemberMapper;
import com.koreatech.byeongcheonairlineapi.service.JwtService;
import com.koreatech.byeongcheonairlineapi.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper, JwtService jwtService) {
        this.memberMapper = memberMapper;
        this.jwtService = jwtService;
    }

    @Override
    public void signUp(SignUpDto signUpDto) {
        if (!signUpDto.getPassword().equals(signUpDto.getPassword2())) throw new IllegalArgumentException();

        Member member = new Member();
        member.setAccount(signUpDto.getAccount());
        member.setBirthday(signUpDto.getBirthday());
        member.setEmail(signUpDto.getEmail());
        member.setNation(signUpDto.getNation());
        member.setPhone(signUpDto.getPhone());
        member.setSex(signUpDto.getSex());
        member.setEnFirstName(signUpDto.getEnFirstName());
        member.setEnLastName(signUpDto.getEnLastName());
        member.setKoFirstName(signUpDto.getKoFirstName());
        member.setKoLastName(signUpDto.getKoLastName());
        member.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        memberMapper.create(member);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    /**
     * 로그인 로직.
     *
     * @param loginDto : 사용자 입력을 받은 account / password 가 들어있는 LoginDto
     * @return : Jwt 토큰
     * 로그인 절자
     * 1. ID / PW를 DB에서 검증.
     * 2. 검증 완료시 AccessToken, RefreshToken 발급.
     * 3. RefreshToken DB에 저장.
     * 4. 응답으로 AccessToken, RefreshToken 전달.
     */
    @Override
    public Map<String, String> login(LoginDto loginDto) {
        Map<String, String> tokens = new HashMap<>();
        Member member = memberMapper.findByAccount(loginDto.getAccount());
        // 아이디로 조회한 회원이 없거나 비밀 번호가 맞지 않을 경우 -> 예외 발생시키기.
        if (member == null || !passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("해당 ID와 PASSWORD가 일치하는 회원이 없음.");
        }
        // 토큰 발급
        String accessToken = jwtService.createAccessToken("account", loginDto.getAccount());
        String refreshToken = jwtService.createRefreshToken("account", loginDto.getAccount());
        // refreshToken 저장.
        Map<String, String> map = new HashMap<>();
        map.put("token", refreshToken);
        map.put("account", loginDto.getAccount());
        memberMapper.saveToken(map);

        tokens.put("access-token", accessToken);
        tokens.put("refresh-token", refreshToken);
        return tokens;
    }


    /**
     * Access Token을 재발급해주는 로직.
     * 1. refresh Token 검증.
     *  -> 위변조 체크, 유효기간 만료 체크
     * 2. refresh Token 2차 검증.
     *  -> DB에 저장된 토큰과 동일한지 체크.
     * 3. 유효성 검증 통과시 access token 재발급.
     * @param token : refresh Token
     * @return : access Token
     */
    @Override
    public String refresh(String token) {
        // refresh token 검증
        if (jwtService.checkToken(token)) {
            String account = jwtService.getInfo("account", token);
            Member member = memberMapper.findByAccount(account);
            if (member.getToken().equals(token)) {
                // 검증 통과시 access token 재발급.
                return jwtService.createAccessToken("account", account);
            }
        }
        // 검증 통과 못했다면 재 로그인 요청.
        throw new UnAuthorizeException();
    }

    @Override
    public void logout(String account) {
       memberMapper.deleteTokenByAccount(account);
    }

    @Override
    public MemberDto findByAccount(String account) {
        Member member = memberMapper.findByAccount(account);
        if (member == null) throw new IllegalArgumentException();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        MemberDto memberDto = new MemberDto(
                member.getEnLastName(),
                member.getEnFirstName(),
                member.getSex(),
                formatter.format(member.getBirthday()),
                member.getPhone(),
                member.getEmail()
                );
        return memberDto;
    }


}
