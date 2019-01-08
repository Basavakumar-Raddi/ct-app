package com.ct.demo.stake.service;

import com.ct.demo.stake.dto.StakeDTO;

import java.util.List;

public interface StakeService {

    public StakeDTO addStake(StakeDTO stakeDTO);

    public StakeDTO updateStake(StakeDTO stakeDTO);

    public List<StakeDTO> viewStakes();

    public void deleteStake(String id);
}
