package com.ct.demo.stake.dao;

import com.ct.demo.stake.entity.Point;
import com.ct.demo.stake.entity.Stake;
import com.ct.demo.stake.util.LocationEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StakeRepository extends JpaRepository<Stake, String> {
}
