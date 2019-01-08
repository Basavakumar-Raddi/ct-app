package com.ct.demo.stake.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import javax.inject.Inject;

import com.ct.demo.stake.dto.PointDTO;
import com.ct.demo.stake.dto.SummaryDTO;
import com.ct.demo.stake.entity.Point;
import com.ct.demo.stake.dao.PointRepository;
import com.ct.demo.stake.util.DateUtil;
import com.ct.demo.stake.util.mapper.PointMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService{

    @Inject
    private PointRepository pointRepository;

    @Autowired
    private PointMapper pointMapper;

    @Override public PointDTO addPoint(PointDTO pointDto) {
        Point pointSaved = null;
        Point point = pointMapper.mapDtoToEntity(pointDto);

        Point existingPoint = pointRepository.findByMeasurementDayAndLocation(point.getMeasurementDay(), point.getLocation());

        if(existingPoint != null){
            existingPoint.setValue(existingPoint.getValue() + point.getValue());
            existingPoint.setLastModifiedBy("user");
            existingPoint.setLastModifiedDate(new Date());
            pointSaved = pointRepository.save(existingPoint);
        } else {
            point.setCreatedBy("user");
            point.setCreatedDate(new Date());
            pointSaved = pointRepository.save(point);
        }

        return pointMapper.mapEntityToDto(pointSaved);
    }

    @Override public SummaryDTO viewPoint() {
        List<Point> pointList = pointRepository.findAll();
        /*List<PointDTO> response = ConversionUtil.convertJson(ConversionUtil.convertToJsonString(pointList),
                new TypeReference<List<PointDTO>>(){});*/
        return getPointDtoList(pointList);
    }

    @Override public void deletePoint(String id) {
        pointRepository.deleteById(id);
    }

    @Override public PointDTO updatePoint(PointDTO pointDto) {
        Point pointSaved = null;
        Point point = pointRepository.getOne(pointDto.getId());
        point.setLastModifiedBy("user");
        point.setLastModifiedDate(new Date());
        point.setMeasurementDay(DateUtil.getMillisFromString(pointDto.getMeasurementDay()));
        point.setLocation(pointDto.getLocation());
        point.setValue(pointDto.getValue());
        pointSaved = pointRepository.save(point);
        return pointMapper.mapEntityToDto(pointSaved);
    }

    private SummaryDTO getPointDtoList(List<Point> pointList){
        SummaryDTO summaryDTO = new SummaryDTO();
        List<PointDTO> pointDTOS = new ArrayList<>();
        for(Point point : pointList){
            pointDTOS.add(pointMapper.mapEntityToDto(point));
        }

        DoubleSummaryStatistics stats = pointDTOS.stream()
                .mapToDouble((x) -> x.getValue())
                .summaryStatistics();

        summaryDTO.setPointDTOS(pointDTOS);
        summaryDTO.setAverageValue(stats.getAverage());
        summaryDTO.setMaxValue(stats.getMax());
        summaryDTO.setMinValue(stats.getMin());
        summaryDTO.setTotalValue(stats.getSum());
        return summaryDTO;
    }
}
