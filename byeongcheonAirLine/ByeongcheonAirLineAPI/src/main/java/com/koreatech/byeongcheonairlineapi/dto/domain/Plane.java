package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

@Data
public class Plane {
    private int id;
    private String name;
    private boolean isFlight;
}
