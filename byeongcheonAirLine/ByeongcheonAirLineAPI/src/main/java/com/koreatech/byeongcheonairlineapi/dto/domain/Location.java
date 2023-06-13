package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

@Data
public class Location {
    private int id;
    private String name;
    private int riskLevel;
    private String nation;
}
