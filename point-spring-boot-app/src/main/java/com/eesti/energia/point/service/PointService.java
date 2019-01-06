package com.eesti.energia.point.service;

import com.eesti.energia.point.dto.PointDTO;
import com.eesti.energia.point.dto.SummaryDTO;

public interface PointService {

    public PointDTO addPoint(PointDTO pointDto);

    public PointDTO updatePoint(PointDTO pointDto);

    public SummaryDTO viewPoint();

    public void deletePoint(String id);
}
