package com.serviceworker.repository;

import com.serviceworker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {

    @Query(value = "select j from JobApplication j where j.status = 'Accept' ")
    List<JobApplication> findAllByStatus(String status);


}
