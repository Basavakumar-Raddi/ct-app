package com.ct.demo.stake.dao;

import com.ct.demo.stake.entity.Point;
import com.ct.demo.stake.util.LocationEnum;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, String> {

    public Point findByMeasurementDayAndLocation(Long measurementDay, LocationEnum location);

}
