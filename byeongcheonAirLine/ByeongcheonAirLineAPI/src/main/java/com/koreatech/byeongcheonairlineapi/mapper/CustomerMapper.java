package com.koreatech.byeongcheonairlineapi.mapper;

import com.koreatech.byeongcheonairlineapi.dto.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Insert("""
            INSERT INTO customer(email, phone, birthday, nation, sex, enFirstName, enLastName)
            VALUES (#{email}, #{phone}, #{birthday}, #{nation}, #{sex}, #{enFirstName}, #{enLastName})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Customer customer);

    @Select("""
            SELECT *
            FROM customer
            """)
    List<Customer> findAll();

    @Select("""
            SELECT *
            FROM customer
            WHERE id = #{id}
            """)
    Customer findById(int id);
}
