package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Select("""
            SELECT *
            FROM ticket
            """)
    List<Ticket> findAll();

    @Select("""
            SELECT *
            FROM ticket
            WHERE id = #{id}
            """)
    Ticket findById(int id);

    @Select("""
            SELECT *
            FROM ticket t
            JOIN member m
            ON m.id = t.memberId
            WHERE m.account = #{account}
            """)
    Ticket findByAccount(String account);
}
