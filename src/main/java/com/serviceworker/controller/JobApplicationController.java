package com.serviceworker.controller;

import com.serviceworker.dto.JobApplyDto;
import com.serviceworker.exception.ResourceNotFoundException;
import com.serviceworker.model.JobApplication;
import com.serviceworker.repository.JobApplicationRepository;
import com.serviceworker.service.JobApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {

    @Autowired
    private JobApplyService jobApplyService;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @PostMapping("/applyJob")
    public ResponseEntity<JobApplyDto> applyJob(@RequestBody JobApplyDto jobApplyDto){
          JobApplyDto applyDto =  jobApplyService.jobApply(jobApplyDto);
        return new ResponseEntity<JobApplyDto>(applyDto, HttpStatus.OK);
    }

    @PutMapping("/updateBid/{id}")
    public ResponseEntity<JobApplication> updateBid(@RequestBody JobApplication jobApplication, @PathVariable Long id) throws ResourceNotFoundException {
      JobApplication updateBid = jobApplyService.updateBid(jobApplication,id);
      return new ResponseEntity<JobApplication>(updateBid, HttpStatus.OK);
    }

    @PostMapping("/application/status")
    public JobApplication applicationStatus(@RequestParam Long id, @RequestParam String status ) throws ResourceNotFoundException {
        JobApplication application = jobApplicationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("job application not found for this id "));
        application.setStatus(status);
        JobApplication jobApplication = jobApplicationRepository.save(application);
        return jobApplication;
    }

    @GetMapping("getAllApplication")
    public ResponseEntity<List<JobApplication>>getAll(String status){
        List<JobApplication> all = this.jobApplyService.getAll(status);
        return new ResponseEntity<List<JobApplication>>(all,HttpStatus.OK);
    }



}
