package com.ct.demo.stake.entity;

import com.ct.demo.stake.util.LocationEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "stake")
@Getter
@Setter
public class Stake extends AbstractAuditable{

    @Column(name = "NAME")
    private String name;

    @Column(name = "SECTOR")
    private String sector;

    @Column(name = "INITIATION_DATE")
    private Long initiationDate;

    @Column(name = "INVESTMENT_REQUIRED")
    private Double investmentRequired;

    @Column(name = "STAKE_OFFERED")
    private Double stakeOffered;

}
