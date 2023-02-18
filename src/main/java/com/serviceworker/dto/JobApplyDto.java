package com.serviceworker.dto;

import com.serviceworker.dto.responsedto.UserDto;

public class JobApplyDto {

    private double myBid;

    private JobDto job;

    private UserDto user;

    public double getMyBid() {
        return myBid;
    }

    public void setMyBid(double myBid) {
        this.myBid = myBid;
    }

    public JobDto getJob() {
        return job;
    }

    public void setJob(JobDto job) {
        this.job = job;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
