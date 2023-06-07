package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface SeatMapper {


    @Insert("""
            INSERT INTO seat(seatClass, planeId)
                VALUES(#{seatClass}, #{planeId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Seat seat);
}
