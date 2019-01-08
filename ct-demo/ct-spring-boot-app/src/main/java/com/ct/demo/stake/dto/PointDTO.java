package com.ct.demo.stake.dto;

import com.ct.demo.stake.util.LocationEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointDTO {

    private String id;
    private String measurementDay;
    private LocationEnum location;
    private Double value;
}
