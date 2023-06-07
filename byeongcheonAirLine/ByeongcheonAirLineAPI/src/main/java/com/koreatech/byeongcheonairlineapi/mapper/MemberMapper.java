package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member(account, password, email, nation, milege, phone,
                            enLastName, enFirstName, koLastName, koFirstName, sex, birthday)
                    VALUES(#{account}, #{password}, #{email}, #{nation}, #{milege}, #{phone}, #{enLastName},
                            #{enFirstName}, #{koLastName}, #{koFirstName}, #{sex}, #{birthday}
                                )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Member member);

    @Select("SELECT * FROM member")
    List<Member> findAll();

    @Select("""
            SELECT *
            FROM member
            WHERE account = #{account}
            """)
    Member findByAccount(String account);

    @Update("""
            UPDATE member
            SET token = #{token}
            WHERE account = #{account}
            """)
    void saveToken(Map<String, String> map);

    @Update("""
            UPDATE member
            SET token = NULL
            WHERE account = #{account}
            """)
    void deleteTokenByAccount(String account);





}
