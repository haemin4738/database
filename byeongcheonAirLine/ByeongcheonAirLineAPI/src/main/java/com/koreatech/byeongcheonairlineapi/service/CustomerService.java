package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.domain.Customer;

import java.util.List;

public interface CustomerService {
    void create(Customer customer);
    List<Customer> findAll();
    Customer findById(int id);
}
