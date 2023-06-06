package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Plane;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface PlaneMapper {

    @Insert("""
            INSERT INTO plane(name, isFlight)
                        VALUES(#{name}, #{isFlight})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Plane plane);

}
