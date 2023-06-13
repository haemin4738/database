package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.domain.Plane;

public interface PlaneService {

    Plane findByFlightId(int flightId);
}
