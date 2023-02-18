package com.serviceworker.service;

import com.serviceworker.dto.JobApplyDto;
import com.serviceworker.exception.ResourceNotFoundException;
import com.serviceworker.model.JobApplication;

import java.util.List;

public interface JobApplyService {

    JobApplyDto jobApply(JobApplyDto jobApplyDto);

    JobApplication updateBid(JobApplication jobApplication, Long id) throws ResourceNotFoundException;

    List<JobApplication> getAll(String status);
}
