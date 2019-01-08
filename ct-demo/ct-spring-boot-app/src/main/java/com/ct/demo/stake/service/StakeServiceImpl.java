package com.ct.demo.stake.service;

import com.ct.demo.stake.dao.StakeRepository;
import com.ct.demo.stake.dto.StakeDTO;
import com.ct.demo.stake.entity.Stake;
import com.ct.demo.stake.util.DateUtil;
import com.ct.demo.stake.util.mapper.StakeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StakeServiceImpl implements StakeService {

    @Inject
    private StakeRepository stakeRepository;

    @Autowired
    private StakeMapper stakeMapper;

    @Override
    public StakeDTO addStake(StakeDTO stakeDTO) {
        Stake stakeSaved = null;
        Stake stake = stakeMapper.mapDtoToEntity(stakeDTO);
        stake.setCreatedBy("user");
        stake.setCreatedDate(new Date());
        stakeSaved = stakeRepository.save(stake);
        return stakeMapper.mapEntityToDto(stakeSaved);
    }

    @Override
    public StakeDTO updateStake(StakeDTO stakeDTO) {
        Stake stakeSaved = null;
        Stake stake = stakeRepository.getOne(stakeDTO.getId());
        stake.setLastModifiedBy("user");
        stake.setLastModifiedDate(new Date());
        stake.setInitiationDate(DateUtil.getMillisFromStringCTDemo(stakeDTO.getInitiationDate()));
        stake.setInvestmentRequired(stakeDTO.getInvestmentRequired());
        stake.setName(stakeDTO.getName());
        stake.setSector(stakeDTO.getSector());
        stake.setStakeOffered(stakeDTO.getStakeOffered());
        stakeSaved = stakeRepository.save(stake);
        return stakeMapper.mapEntityToDto(stakeSaved);
    }

    @Override
    public List<StakeDTO> viewStakes() {
        List<Stake> stakes = stakeRepository.findAll();
        return getStakeDtoList(stakes);
    }

    @Override
    public void deleteStake(String id) {
        stakeRepository.deleteById(id);
    }

    private List<StakeDTO> getStakeDtoList(List<Stake> stakes){
        List<StakeDTO> stakeDTOS = new ArrayList<>();
        for(Stake stake : stakes){
            stakeDTOS.add(stakeMapper.mapEntityToDto(stake));
        }
        return stakeDTOS;
    }
}
