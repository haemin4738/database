package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int id;
    private boolean isExchanged;
    private String memberAccount;
    private Date startDate;
    private Date endDate;
    private int memberId;
}
