package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.domain.Plane;
import com.koreatech.byeongcheonairlineapi.mapper.PlaneMapper;
import com.koreatech.byeongcheonairlineapi.service.PlaneService;
import org.springframework.stereotype.Service;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneMapper planeMapper;

    public PlaneServiceImpl(PlaneMapper planeMapper) {
        this.planeMapper = planeMapper;
    }

    @Override
    public Plane findByFlightId(int flightId) {
        return planeMapper.findByFlightId(flightId);
    }
}
