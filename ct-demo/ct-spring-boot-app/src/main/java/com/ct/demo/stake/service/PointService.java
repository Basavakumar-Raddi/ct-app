package com.ct.demo.stake.service;

import com.ct.demo.stake.dto.PointDTO;
import com.ct.demo.stake.dto.SummaryDTO;

public interface PointService {

    public PointDTO addPoint(PointDTO pointDto);

    public PointDTO updatePoint(PointDTO pointDto);

    public SummaryDTO viewPoint();

    public void deletePoint(String id);
}
