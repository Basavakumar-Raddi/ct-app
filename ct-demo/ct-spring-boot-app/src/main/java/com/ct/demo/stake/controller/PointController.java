package com.ct.demo.stake.controller;

import javax.inject.Inject;

import com.ct.demo.stake.dto.PointDTO;
import com.ct.demo.stake.dto.SummaryDTO;
import com.ct.demo.stake.service.PointService;
import com.ct.demo.stake.util.ConversionUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/points")
@Slf4j
public class PointController {

    @Inject
    private PointService pointService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PointDTO>> addPoint(@RequestBody String pointRequest) {
        ApiResponse<PointDTO> response = new ApiResponse<>();
        try {
            PointDTO pointDto = ConversionUtil.convertJson(pointRequest, PointDTO.class);
            response.setResponse(pointService.addPoint(pointDto));
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Point added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("error occurred during add point", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while adding the point");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<SummaryDTO>> viewPoints() {
        ApiResponse<SummaryDTO> response = new ApiResponse<>();
        try {
            response.setResponse(pointService.viewPoint());
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Points fetched successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("error occurred while fetch", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while fetching the points");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PointDTO>> updatePoint(@RequestBody String pointRequest) {
        ApiResponse<PointDTO> response = new ApiResponse<>();
        try {
            PointDTO pointDto = ConversionUtil.convertJson(pointRequest, PointDTO.class);
            response.setResponse(pointService.updatePoint(pointDto));
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Point updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("error occurred during updating point", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while updating the point");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<PointDTO>> deletePoint(@RequestParam String id) {
        ApiResponse<PointDTO> response = new ApiResponse<>();
        try {
            pointService.deletePoint(id);
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Point deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("error occurred during delete point", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while deleting the point");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }
}