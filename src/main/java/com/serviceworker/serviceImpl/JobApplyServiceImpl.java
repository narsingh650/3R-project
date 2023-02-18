package com.serviceworker.serviceImpl;

import com.serviceworker.dto.JobApplyDto;
import com.serviceworker.dto.JobDto;
import com.serviceworker.dto.responsedto.UserDto;
import com.serviceworker.exception.ResourceNotFoundException;
import com.serviceworker.model.Job;
import com.serviceworker.model.JobApplication;
import com.serviceworker.model.User;
import com.serviceworker.repository.JobApplicationRepository;
import com.serviceworker.repository.JobRepository;
import com.serviceworker.repository.UserRepository;
import com.serviceworker.service.JobApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplyServiceImpl implements JobApplyService {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    private JobServiceImpl service;

    @Override
    public JobApplyDto jobApply(JobApplyDto jobApplyDto) {
        JobApplication application = toEntity(jobApplyDto);
        Optional<Job> optionalJob = jobRepository.findById(jobApplyDto.getJob().getId());
        Optional<User> optionalUser = userRepository.findById(jobApplyDto.getUser().getId());
        application.setMyBid(jobApplyDto.getMyBid());
        application.setJob(optionalJob.get());
        application.setUser(optionalUser.get());
        JobApplication jobApplication = jobApplicationRepository.save(application);
        JobApplyDto applyDto = toDto(jobApplication);
        return applyDto;
    }

    @Override
    public JobApplication updateBid(JobApplication jobApplication, Long id) throws ResourceNotFoundException {
        JobApplication getBid = jobApplicationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("job application not found by id"));
            getBid.setMyBid(jobApplication.getMyBid());
            getBid = jobApplicationRepository.save(getBid);
            return getBid;
    }

    @Override
    public List<JobApplication> getAll(String status) {
     List<JobApplication>jobApplications = jobApplicationRepository.findAllByStatus(status);
        return jobApplications;
    }


    public JobApplyDto toDto(JobApplication entity) {
        JobDto jobDto = new JobDto();
        jobDto.setId(entity.getJob().getId());
        jobDto.setJobTitle(entity.getJob().getJobTitle());
        jobDto.setStatus(entity.getStatus());
        jobDto.setUsername(entity.getJob().getUsername());
        jobDto.setPrice(entity.getJob().getPrice());
        jobDto.setDescription(entity.getJob().getDescription());
        jobDto.setPinCode(entity.getJob().getPinCode());
        jobDto.setCreatedAt(entity.getJob().getCreatedAt());
        jobDto.setAddressLine1(entity.getJob().getAddressLine1());
        jobDto.setAddressLine2(entity.getJob().getAddressLine2());

        UserDto userDto = new UserDto();
        userDto.setId(entity.getUser().getId());
        JobApplyDto dto = new JobApplyDto();
        dto.setMyBid(entity.getMyBid());
        dto.setJob(jobDto);
        dto.setUser(userDto);
        return dto;
    }

    public JobApplication toEntity(JobApplyDto dto) {
        Job job =new Job();
        job.setId(dto.getJob().getId());
        User user = new User();
        user.setId(dto.getUser().getId());
        JobApplication entity = new JobApplication();
        entity.setMyBid(dto.getMyBid());
        entity.setJob(job);
        entity.setUser(user);
        return entity;
    }



}
