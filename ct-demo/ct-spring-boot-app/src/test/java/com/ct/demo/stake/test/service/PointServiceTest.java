package com.ct.demo.stake.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ct.demo.stake.dao.PointRepository;
import com.ct.demo.stake.dto.PointDTO;
import com.ct.demo.stake.dto.SummaryDTO;
import com.ct.demo.stake.entity.Point;
import com.ct.demo.stake.service.PointService;
import com.ct.demo.stake.service.PointServiceImpl;
import com.ct.demo.stake.util.DateUtil;
import com.ct.demo.stake.util.LocationEnum;
import com.ct.demo.stake.util.mapper.PointMapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PointServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public PointService pointService() {
            return new PointServiceImpl();
        }
    }

    @Autowired
    private PointService pointService;

    @MockBean
    private PointRepository pointRepository;

    @MockBean
    private PointMapper pointMapper;

    PointDTO pointDTO = new PointDTO();

    @Before
    public void setUp() {
        Point point = new Point();
        point.setValue(200.35);
        point.setLocation(LocationEnum.EE);
        point.setMeasurementDay(1545004800000L);
        point.setCreatedBy("testUser");
        point.setCreatedDate(new Date());

        pointDTO.setMeasurementDay(DateUtil.getStringFromMillis(point.getMeasurementDay()));
        pointDTO.setValue(point.getValue());
        pointDTO.setLocation(point.getLocation());

        List<Point> pointList = new ArrayList<>();
        pointList.add(point);

        Mockito.when(pointRepository.findByMeasurementDayAndLocation(point.getMeasurementDay(),point.getLocation()))
                .thenReturn(point);
        Mockito.when(pointRepository.save(point)).thenReturn(point);
        Mockito.when(pointRepository.findAll()).thenReturn(pointList);
        Mockito.when(pointMapper.mapEntityToDto(point)).thenReturn(pointDTO);
        Mockito.when(pointMapper.mapDtoToEntity(pointDTO)).thenReturn(point);

    }

    @Test
    public void viewPointTest() {
        Double value = 200.35;
        SummaryDTO summaryDTO = pointService.viewPoint();

        Assert.assertEquals(summaryDTO.getMaxValue(),value);
        Assert.assertEquals(summaryDTO.getPointDTOS().get(0).getLocation(),LocationEnum.EE);
        Assert.assertEquals(summaryDTO.getPointDTOS().get(0).getMeasurementDay(),"2018-12-17");
    }

    @Test
    public void failViewPointTest() {
        Double value = 8.35;
        SummaryDTO summaryDTO = pointService.viewPoint();

        Assert.assertNotEquals(summaryDTO.getMaxValue(),value);
        Assert.assertNotEquals(summaryDTO.getPointDTOS().get(0).getLocation(),LocationEnum.FI);
        Assert.assertNotEquals(summaryDTO.getPointDTOS().get(0).getMeasurementDay(),"2018-11-18");
    }

    @Test
    public void addPointTest() {
        Double value = 200.35;
        PointDTO pointDTOResp = pointService.addPoint(pointDTO);
        Assert.assertEquals(pointDTOResp.getMeasurementDay(),"2018-12-17");
        Assert.assertEquals(pointDTOResp.getLocation(),LocationEnum.EE);
        Assert.assertEquals(pointDTOResp.getValue(),value);

    }

    @Test
    public void failAddPointTest() {
        Double value = 88.98;
        PointDTO pointDTOResp = pointService.addPoint(pointDTO);
        Assert.assertNotEquals(pointDTOResp.getMeasurementDay(),"2018-05-10");
        Assert.assertNotEquals(pointDTOResp.getLocation(),LocationEnum.FI);
        Assert.assertNotEquals(pointDTOResp.getValue(),value);

    }

}
