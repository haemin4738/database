package com.koreatech.byeongcheonairlineapi.service;


import com.koreatech.byeongcheonairlineapi.dto.FlightDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.FlightModel;

import java.util.List;

public interface FlightService {

    List<FlightDto> goTrip(FlightModel flightModel);

    List<FlightDto> comeHome(FlightModel flightModel);
}
