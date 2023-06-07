package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.domain.Flight;
import com.koreatech.byeongcheonairlineapi.dto.domain.Location;
import com.koreatech.byeongcheonairlineapi.mapper.FlightMapper;
import com.koreatech.byeongcheonairlineapi.mapper.LocationMapper;
import com.koreatech.byeongcheonairlineapi.mapper.PlaneMapper;
import com.koreatech.byeongcheonairlineapi.mapper.SeatMapper;
import com.koreatech.byeongcheonairlineapi.service.DataInputService;
import org.apache.ibatis.javassist.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.StringTokenizer;

@Service
public class DataInputServiceImpl implements DataInputService {
    private final LocationMapper locationMapper;
    private final SeatMapper seatMapper;
    private final PlaneMapper planeMapper;
    private final FlightMapper flightMapper;

    @Autowired
    public DataInputServiceImpl(LocationMapper locationMapper, SeatMapper seatMapper, PlaneMapper planeMapper, FlightMapper flightMapper) {
        this.locationMapper = locationMapper;
        this.seatMapper = seatMapper;
        this.planeMapper = planeMapper;
        this.flightMapper = flightMapper;
    }

    @Override
    public void insertLocation() throws IOException {
        // Location 데이터를 넣기 위한 로직 작성.
    }

    @Override
    public void insertPlane() {

    }

    @Override
    public void insertSeat() {

    }

    @Override
    public void insertFlight() {

    }
}
