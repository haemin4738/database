package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

@Data
public class Seat {
    private int id;
    private String seatClass;
    private boolean reserved;
    private int planeId;
}
