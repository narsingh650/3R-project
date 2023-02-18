package com.serviceworker.dto;

public class CategoryDto {

    private Long id;

    private String name;

//    private List<JobDto>jobs;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<JobDto> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(List<JobDto> jobs) {
//        this.jobs = jobs;
//    }
}
