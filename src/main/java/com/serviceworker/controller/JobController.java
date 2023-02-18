package com.serviceworker.controller;

import com.serviceworker.dto.JobDetailsDto;
import com.serviceworker.dto.JobDto;
import com.serviceworker.dto.JobViewDto;
import com.serviceworker.exception.ResourceNotFoundException;
import com.serviceworker.model.Job;
import com.serviceworker.repository.JobApplicationRepository;
import com.serviceworker.repository.JobRepository;
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
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @PostMapping("/createJob")
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto){
     JobDto jobCreate = jobService.createJob(jobDto);
        return  new ResponseEntity<JobDto>(jobCreate, HttpStatus.CREATED);
    }

    @GetMapping("activeJob/getAll")
    public ResponseEntity<List<JobViewDto>>getAll(){
        List<JobViewDto> all = this.jobService.getAll();
        return new ResponseEntity<List<JobViewDto>>(all,HttpStatus.OK);
    }

    @GetMapping("serviceProvider/viewJob/active")
    public ResponseEntity<List<JobViewDto>>viewJobs(){
     List<JobViewDto> list = jobService.viewAllJobs();
        return new ResponseEntity<List<JobViewDto>>(list,HttpStatus.OK);
    }

    @GetMapping("viewJobDetails/{id}")
    public ResponseEntity<JobDetailsDto>viewJobIdWithDetails(@PathVariable Long id){
     JobDetailsDto jobView= jobService.viewJobById(id);
        return  new ResponseEntity<JobDetailsDto>(jobView,HttpStatus.OK);
    }


    @PostMapping("/application/status/{id}")
    public Job MarkOngoingStatus(@RequestParam Long id, @RequestParam String status ) throws ResourceNotFoundException {
        Job application = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("job application not found for this id "));
        application.setStatus(status);
        Job job= jobRepository.save(application);
        return job;

    }



}
