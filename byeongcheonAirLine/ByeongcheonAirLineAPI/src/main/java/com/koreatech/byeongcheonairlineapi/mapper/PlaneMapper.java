package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Plane;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PlaneMapper {

    @Insert("""
            INSERT INTO plane(name, isFlight)
                        VALUES(#{name}, #{isFlight})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Plane plane);

    @Select("""
            SELECT * FROM plane id = #{id}
            """)
    Plane findById(int id);

    @Select("""
            SELECT p.*
            FROM plane p, flight f
            WHERE p.id = f.planeId AND f.Id = #{flightId}
            """)
    Plane findByFlightId(int flightId);
}
