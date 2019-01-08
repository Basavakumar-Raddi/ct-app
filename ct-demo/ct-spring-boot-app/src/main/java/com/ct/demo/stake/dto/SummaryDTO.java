package com.ct.demo.stake.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryDTO {
    private Double averageValue;
    private Double minValue;
    private Double maxValue;
    private Double totalValue;
    private List<PointDTO> pointDTOS;
}
