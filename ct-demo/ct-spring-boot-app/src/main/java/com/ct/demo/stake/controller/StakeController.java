package com.ct.demo.stake.controller;

import com.ct.demo.stake.dto.PointDTO;
import com.ct.demo.stake.dto.StakeDTO;
import com.ct.demo.stake.dto.SummaryDTO;
import com.ct.demo.stake.service.PointService;
import com.ct.demo.stake.service.StakeService;
import com.ct.demo.stake.util.ConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/stakes")
@Slf4j
public class StakeController {

    @Inject
    private StakeService stakeService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<StakeDTO>> addStake(@RequestBody String pointRequest) {
        ApiResponse<StakeDTO> response = new ApiResponse<>();
        try {
            StakeDTO stakeDTO = ConversionUtil.convertJson(pointRequest, StakeDTO.class);
            response.setResponse(stakeService.addStake(stakeDTO));
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Stake added successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("error occurred during add point", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while adding the stake");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<StakeDTO>>> viewStakes() {
        ApiResponse<List<StakeDTO>> response = new ApiResponse<>();
        try {
            response.setResponse(stakeService.viewStakes());
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Stakes fetched successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("error occurred while fetch", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while fetching the stakes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<StakeDTO>> updatePoint(@RequestBody String stakeRequest) {
        ApiResponse<StakeDTO> response = new ApiResponse<>();
        try {
            StakeDTO stakeDTO = ConversionUtil.convertJson(stakeRequest, StakeDTO.class);
            response.setResponse(stakeService.updateStake(stakeDTO));
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Stake updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("error occurred during updating stake", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while updating the stake");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<StakeDTO>> deletePoint(@RequestParam String id) {
        ApiResponse<StakeDTO> response = new ApiResponse<>();
        try {
            stakeService.deleteStake(id);
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Stake deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            log.error("error occurred during delete stake", e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("error occurred while deleting the stake");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
    }
}