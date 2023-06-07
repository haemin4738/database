package com.koreatech.byeongcheonairlineapi.service;

import java.io.IOException;

public interface DataInputService {

    void insertLocation() throws IOException;

    void insertPlane();

    void insertSeat();

    void insertFlight();
}
