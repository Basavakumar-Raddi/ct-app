package com.ct.demo.stake.dto;

import com.ct.demo.stake.util.LocationEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StakeDTO {

    private String id;
    private String name;
    private String sector;
    private String initiationDate;
    private Double investmentRequired;
    private Double stakeOffered;
}
