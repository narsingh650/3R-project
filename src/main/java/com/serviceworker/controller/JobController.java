package com.serviceworker.controller;

import com.serviceworker.dto.JobDto;
import com.serviceworker.dto.JobViewDto;
import com.serviceworker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/createJob")
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto){
     JobDto jobCreate = jobService.createJob(jobDto);
        return  new ResponseEntity<JobDto>(jobCreate, HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<JobViewDto>>getAll(){
        List<JobViewDto> all = this.jobService.getAll();
        return new ResponseEntity<List<JobViewDto>>(all,HttpStatus.OK);
    }

}
