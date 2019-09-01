package com.tkafol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkafol.model.ResearchType;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@Repository
public interface ResearchTypeRepository extends JpaRepository<ResearchType, Long> {

}
