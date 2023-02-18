package com.serviceworker.serviceImpl;

import com.serviceworker.dto.JobDto;
import com.serviceworker.dto.JobViewDto;
import com.serviceworker.model.Job;
import com.serviceworker.repository.JobRepository;
import com.serviceworker.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private JobRepository jobRepository;

    @Override
    public JobDto createJob(JobDto jobDto) {
        Job map = mapper.map(jobDto, Job.class);
        map.setCreatedAt(LocalDateTime.now());
        Job saveJob = jobRepository.save(map);
        return this.mapper.map(saveJob, JobDto.class);
    }

    @Override
    public List<JobViewDto> getAll() {
        List<Job> findAll = this.jobRepository.findAll();
        List<JobViewDto> jobview = findAll.stream().map(job -> mapper.map(job, JobViewDto.class)).collect(Collectors.toList());
        return jobview;
    }

//    public List<CategoryDto> getAll(){
//        List<Category> findAll = this.catRepo.findAll();
//        List<CategoryDto> allDto= findAll.stream().map(cat->this.mapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
//        return allDto;
//    }


}
