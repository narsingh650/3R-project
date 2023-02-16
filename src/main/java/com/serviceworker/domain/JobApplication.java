package com.serviceworker.domain;

import com.serviceworker.model.User;

import javax.persistence.*;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double myBid;
    private String status;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private com.serviceworker.model.User user;

    public JobApplication() {
    }

    public JobApplication(Long id, double myBid, Job job, com.serviceworker.model.User user, String status) {
        this.id = id;
        this.myBid = myBid;
        this.job = job;
        this.user = user;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMyBid() {
        return myBid;
    }

    public void setMyBid(double myBid) {
        this.myBid = myBid;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public com.serviceworker.model.User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
