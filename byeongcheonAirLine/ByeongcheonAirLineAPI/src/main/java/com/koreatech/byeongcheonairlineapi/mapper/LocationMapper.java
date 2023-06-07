package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Location;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LocationMapper {
    @Insert("""
               INSERT INTO location(name, nation)
               VALUES (#{name}, #{nation})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Location location);

    @Update("""
            UPDATE location 
            SET duration = #{duration}
            WHERE name = #{name}
            """)
    void update(Location location);
}
