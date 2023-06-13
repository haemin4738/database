package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeatMapper {


    @Insert("""
            INSERT INTO seat(seatClass, planeId)
                VALUES(#{seatClass}, #{planeId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Seat seat);

    @Select("""
            SELECT s.*
            FROM seat s, ticket t
            WHERE s.id = t.seatId AND t.state = "reserved"
            AND t.flightId = #{flightId}
            """)
    List<Seat> getReservedSeatsByFlightId(int flightId);
}
