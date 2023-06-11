package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.Model.ResponseTicket;
import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Select("""
            SELECT *
            FROM ticket
            """)
    List<Ticket> findAll();

    @Insert("""
            INSERT INTO ticket (state, reserveDate, payment, cardNo, flightId, customerId, seatId)
            VALUES ('reserved', now(), #{payment}, #{cardNo}, #{flightId}, #{customerId}, #{seatId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createByCustomer(Ticket ticket);

    @Insert("""
            INSERT INTO ticket (state, reserveDate, payment, cardNo, flightId, memberId, seatId)
            VALUES ('reserved', now(), #{payment}, #{cardNo}, #{flightId}, #{memberId}, #{seatId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createByMember(Ticket ticket);

    @Update("""
            UPDATE ticket
            SET state = #{state}
            WHERE id = #{id}
            """)
    void updateState(Ticket ticket);

    @Delete("""
            DELETE FROM ticket
            WHERE id = #{id}
            """)
    void deleteById(int id);

    //회원에게 보여줄 티켓정보 검색
    @Select("""
            SELECT t.id, dl.name as departure, al.name as arrival,
                f.departureTime, date_add(f.departureTime, interval f.duration hour) as arrivalTime,
                s.seatClass, p.name as planeName, t.seatId, t.state
                FROM ticket t
                JOIN flight f ON t.flightId = f.id
                JOIN location al ON f.arrivalId = al.id
                JOIN location dl ON f.departureId = dl.id
                JOIN plane p ON f.planeId = p.id
                JOIN seat s ON s.planeId = p.id and t.seatId = s.id
                JOIN member m ON m.id = t.memberId
                WHERE m.account = #{account}
                ORDER BY field(t.state, "reserved", "canceled", "used")
            """)
    List<ResponseTicket> findByMember(String account);
    @Select("""
            SELECT t.id, dl.name as departure, al.name as arrival,
                f.departureTime, date_add(f.departureTime, interval f.duration hour) as arrivalTime,
                s.seatClass, p.name as planeName, t.seatId, t.state
                FROM ticket t
                JOIN flight f ON t.flightId = f.id
                JOIN location al ON f.arrivalId = al.id
                JOIN location dl ON f.departureId = dl.id
                JOIN plane p ON f.planeId = p.id
                JOIN seat s ON s.planeId = p.id and t.seatId = s.id
                WHERE t.id = #{id}
                ORDER BY field(t.state, "reserved", "canceled", "used")
            """)
    List<ResponseTicket> findByCustomer(int id);
}
