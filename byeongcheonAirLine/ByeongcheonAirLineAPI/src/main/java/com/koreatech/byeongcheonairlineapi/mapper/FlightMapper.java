package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.FlightDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.FlightModel;
import com.koreatech.byeongcheonairlineapi.dto.domain.Flight;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FlightMapper {

    @Insert("""
            INSERT INTO flight(planeId, departureId, arrivalId, departureTime, duration, price)
                    VALUES(#{planeId}, #{departureId}, #{arrivalId}, #{departureTime}, #{duration}, #{price})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Flight flight);


    @Select("""
            SELECT info.departure, info.arrival, info.departureTime, info.arrivalTime, info.duration, info.planeName, seats.availableSeats
            FROM
            	(SELECT p.id planeId, f.id flightId, f.departureTime, date_add(f.departureTime, INTERVAL f.duration HOUR) AS arrivalTime,
            		   d.name departure, a.name arrival, p.name planeName, f.price economyPrice, f.duration
            	FROM flight f, location d, location a, plane p
            	WHERE f.planeId = p.id AND f.departureId = d.id AND f.arrivalId = a.id AND f.planeId = p.id
            			AND d.name = #{departure} AND a.name = #{arrival} AND DATE(f.departureTime) = DATE(#{departureDate})
                ) AS info
                ,
            	(SELECT p.id planeId, p.name planeName, COUNT(*) AS availableSeats
            	    FROM seat s
            		JOIN plane p ON s.planeId = p.id
            		LEFT JOIN ticket t ON s.id = t.seatId
            		WHERE p.id IN
            		
            		(SELECT p.id
            			 FROM flight f, location d, location a, plane p
            			 WHERE f.planeId = p.id AND f.departureId = d.id AND f.arrivalId = a.id AND f.planeId = p.id
            									AND d.name = #{departure} AND a.name = #{arrival} AND DATE(f.departureTime) = DATE(#{departureDate}))
            		                            AND s.seatClass = #{seatClass} AND (t.state IS NULL OR t.state != "reserved")
                GROUP BY planeId
                ) AS seats
            WHERE info.planeId = seats.planeId;
                        
            """)
    List<FlightDto> findByCondition(FlightModel flightModel);
}
