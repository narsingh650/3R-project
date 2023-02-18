package com.serviceworker.service;

import com.serviceworker.dto.JobDto;
import com.serviceworker.dto.JobViewDto;

import java.util.List;

public interface JobService {
    JobDto createJob(JobDto jobDto);
    List<JobViewDto> getAll();
}
