package com.tkafol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkafol.model.Job;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
