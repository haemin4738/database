package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.LocationTmpDto;
import com.koreatech.byeongcheonairlineapi.dto.domain.Flight;
import com.koreatech.byeongcheonairlineapi.dto.domain.Plane;
import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import com.koreatech.byeongcheonairlineapi.mapper.FlightMapper;
import com.koreatech.byeongcheonairlineapi.mapper.LocationMapper;
import com.koreatech.byeongcheonairlineapi.mapper.PlaneMapper;
import com.koreatech.byeongcheonairlineapi.mapper.SeatMapper;
import com.koreatech.byeongcheonairlineapi.service.DataInputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
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
    public void insertLocation() {
        // Location 데이터를 넣기 위한 로직 작성.

    }

    @Override
    public void insertPlane() {
        for (int i = 0; i < 151; i ++) {
            Plane plane = new Plane();
            plane.setName("BC" + getNumber(i));
//            log.debug("{}", plane);
            planeMapper.insert(plane);
//            System.out.println(plane);

        }
    }

    String getNumber(int num) {
        if (num < 10) return "00" + num;
        else if (num < 100) return "0" + num;
        else return String.valueOf(num);
    }

    @Override
    public void insertSeat() {
        int seatId = 0;
        for (int pNum = 4; pNum < 155; pNum++) {
            for (int i = 0; i < 10; i ++) {
                Seat seat = new Seat();
                seatId ++;
                seat.setSeatClass("first");
                seat.setPlaneId(pNum);
//                log.debug("{}", seat);
                seatMapper.insert(seat);
//                System.out.println(seat + ", seatId : " + seatId);
            }
            for (int i = 0; i < 40; i ++) {
                seatId ++;
                Seat seat = new Seat();
                seat.setSeatClass("business");
                seat.setPlaneId(pNum);
//                System.out.println(seat + ", seatId : " + seatId);
//                log.debug("{}", seat);
                seatMapper.insert(seat);
            }
            for (int i = 0; i < 150; i ++) {
                seatId ++;
                Seat seat = new Seat();
                seat.setSeatClass("economy");
                seat.setPlaneId(pNum);
//                System.out.println(seat + ", seatId : " + seatId);
//                log.debug("{}", seat);
                seatMapper.insert(seat);
            }
        }
    }

    @Override
    public void insertFlight() {
        // { {7, 광저우, 2}, {11, 나고야, 2}, ... }
        List<LocationTmpDto> under7Hours = locationMapper.getUnder7Hours();
        // { {2, 두바이, 8}, {3, 런던, 10}, ... }
        List<LocationTmpDto> over7Hours = locationMapper.getOver7Hours();
        LocalDateTime under7hoursStart1 = LocalDateTime.of(2023, 06, 14, 02, 00, 00);
        LocalDateTime under7hoursStart2 = LocalDateTime.of(2023, 06, 14, 10, 00, 00);
        LocalDateTime over7hoursStart = LocalDateTime.of(2023, 06, 14, 00, 00, 00);

        for (int plusDay = 0; plusDay < 60; plusDay ++) {
            LocalDateTime under7hoursDateTime1 = under7hoursStart1.plusDays(plusDay);
            LocalDateTime under7hoursDateTime2 = under7hoursStart2.plusDays(plusDay);
            LocalDateTime over7hoursDateTime = over7hoursStart.plusDays(plusDay);

            int planeId = 1;
            for (LocationTmpDto locationTmpDto : under7Hours) {
                Flight goUnder1 = new Flight(
                        planeId,
                        1,
                        locationTmpDto.getId(),
                        Timestamp.valueOf(under7hoursDateTime1),
                        locationTmpDto.getDuration(),
                        locationTmpDto.getDuration() * 50_000
                );
                Flight backUnder1 = new Flight(
                        planeId,
                        locationTmpDto.getId(),
                        1,
                        Timestamp.valueOf(under7hoursDateTime1.plusHours(locationTmpDto.getDuration() + 1)),
                        locationTmpDto.getDuration(),
                        locationTmpDto.getDuration() * 50_000
                );

                Flight goUnder2 = new Flight(
                        planeId + under7Hours.size(),
                        1,
                        locationTmpDto.getId(),
                        Timestamp.valueOf(under7hoursDateTime2),
                        locationTmpDto.getDuration(),
                        locationTmpDto.getDuration() * 50_000
                );
                Flight backUnder2 = new Flight(
                        planeId + under7Hours.size(),
                        locationTmpDto.getId(),
                        1,
                        Timestamp.valueOf(under7hoursDateTime2.plusHours(locationTmpDto.getDuration() + 1)),
                        locationTmpDto.getDuration(),
                        locationTmpDto.getDuration() * 50_000
                );
                flightMapper.insert(goUnder1);
                flightMapper.insert(backUnder1);
                flightMapper.insert(goUnder2);
                flightMapper.insert(backUnder2);
                planeId++;
            }
            planeId += under7Hours.size();
            for (LocationTmpDto locationTmpDto : over7Hours) {
                Flight goOver = new Flight(
                        planeId,
                        1,
                        locationTmpDto.getId(),
                        Timestamp.valueOf(over7hoursDateTime),
                        locationTmpDto.getDuration(),
                        locationTmpDto.getDuration() * 50_000
                );
                Flight backOver = new Flight(
                        planeId,
                        locationTmpDto.getId(),
                        1,
                        Timestamp.valueOf(over7hoursDateTime.plusHours(locationTmpDto.getDuration() + 1)),
                        locationTmpDto.getDuration(),
                        locationTmpDto.getDuration() * 50_000
                );

                flightMapper.insert(goOver);
                flightMapper.insert(backOver);
                planeId++;
            }
        }
    }
}
