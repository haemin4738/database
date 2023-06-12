package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.FlightDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.RequestFlight;
import com.koreatech.byeongcheonairlineapi.dto.domain.Flight;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlightMapper {

    @Insert("""
            INSERT INTO flight(planeId, departureId, arrivalId, departureTime, duration, price)
                    VALUES(#{planeId}, #{departureId}, #{arrivalId}, #{departureTime}, #{duration}, #{price})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Flight flight);



    List<FlightDto> findByCondition(RequestFlight requestFlight);

    @Select("SELECT * FROM flight WHERE id = #{id}")
    Flight findById(int id);


}
