package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.LocationTmpDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LocationMapper {

    @Select("""
            SELECT *
            FROM location
            WHERE duration BETWEEN 1 AND 6
            ORDER BY id;
            """)
    List<LocationTmpDto> getUnder7Hours();
    @Select("""
            SELECT *
            FROM location
            WHERE duration >= 7
            ORDER BY id;
            """)
    List<LocationTmpDto> getOver7Hours();

}
